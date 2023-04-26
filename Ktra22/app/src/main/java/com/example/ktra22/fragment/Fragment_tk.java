package com.example.ktra22.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra22.R;
import com.example.ktra22.adapter.RecyclerViewAdapter;
import com.example.ktra22.database.SQLite;
import com.example.ktra22.model.NhanVien;

import java.util.List;

public class Fragment_tk extends Fragment implements View.OnClickListener {

    CheckBox addKynang1, addKynang2, addKynang3;
    Button btnSearch;
    RecyclerView recyclerView;
    SQLite sqLite;
    RecyclerViewAdapter recyclerViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tk, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addKynang1 = view.findViewById(R.id.addKynang1);
        addKynang2 = view.findViewById(R.id.addKynang2);
        addKynang3 = view.findViewById(R.id.addKynang3);
        btnSearch = view.findViewById(R.id.btnSearch);
        recyclerView = view.findViewById(R.id.recyclerView);
        btnSearch.setOnClickListener(this);
        sqLite = new SQLite(view.getContext());
        recyclerViewAdapter = new RecyclerViewAdapter();
    }

    @Override
    public void onClick(View view) {
        if(view== btnSearch){
            String kyNang = "";
            if (addKynang1.isChecked()) {
                kyNang += "web;";
            }
            if (addKynang2.isChecked()) {
                kyNang += "android;";
            }
            if (addKynang3.isChecked()) {
                kyNang += "ios;";
            }
            if (kyNang.endsWith(";")) {
                kyNang = kyNang.substring(0, kyNang.length() - 1);
            }
            List<NhanVien>list = sqLite.search(kyNang);
            recyclerViewAdapter.setList(list);
            recyclerView.setAdapter(recyclerViewAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);

        }

    }
}
