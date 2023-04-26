package com.example.ktra21.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra21.R;
import com.example.ktra21.RecyclerViewAdapter;
import com.example.ktra21.database.SQLite;
import com.example.ktra21.model.CongViec;

import java.util.List;

public class Fragment_tk extends Fragment {

    SearchView searchView;
    RecyclerView recyclerView;
    SQLite sqLite;
    RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tk, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recyclerView);

        sqLite = new SQLite(view.getContext());
        recyclerViewAdapter = new RecyclerViewAdapter();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<CongViec> congViecList = sqLite.search(s);
                recyclerViewAdapter.setList(congViecList);
                recyclerView.setAdapter(recyclerViewAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                return true;
            }
        });
    }
}
