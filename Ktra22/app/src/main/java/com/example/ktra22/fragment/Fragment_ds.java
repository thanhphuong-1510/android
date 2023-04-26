package com.example.ktra22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra22.EditActivity;
import com.example.ktra22.R;
import com.example.ktra22.adapter.RecyclerViewAdapter;
import com.example.ktra22.adapter.ViewHolderListener;
import com.example.ktra22.database.SQLite;
import com.example.ktra22.model.NhanVien;

import java.util.List;

public class Fragment_ds extends Fragment implements ViewHolderListener {

    RecyclerView recyclerView;
    SQLite sqLite;
    RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_ds,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewAdapter = new RecyclerViewAdapter();
        sqLite = new SQLite(this.getContext());
        recyclerViewAdapter.setViewHolderListener(this);
        List<NhanVien> list = sqLite.getAll();
        recyclerViewAdapter.setList(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<NhanVien> list = sqLite.getAll();
        recyclerViewAdapter.setList(list);

    }

    @Override
    public void onClick(View view, int pos) {
        NhanVien nhanVien = recyclerViewAdapter.getCV(pos);
        Intent intent = new Intent(this.getContext(), EditActivity.class);
        intent.putExtra("nhanvien", nhanVien);
        startActivity(intent);
    }
}
