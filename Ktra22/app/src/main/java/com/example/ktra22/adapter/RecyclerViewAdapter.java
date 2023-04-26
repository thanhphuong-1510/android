package com.example.ktra22.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ktra22.R;
import com.example.ktra22.model.NhanVien;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<NhanVien> list;

    private ViewHolderListener viewHolderListener;

    public void setViewHolderListener(ViewHolderListener viewHolderListener) {
        this.viewHolderListener = viewHolderListener;
    }

    public void setList(List<NhanVien> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    public NhanVien getCV(int pos){
        return list.get(pos);
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        NhanVien nhanVien = this.list.get(position);
        holder.tenNV.setText(nhanVien.getTen());
        holder.sdt.setText(nhanVien.getSdt());
        holder.dob.setText(String.valueOf(nhanVien.getDob()));

        if(nhanVien.getGt()== true){
            holder.gt.setText("Nam");
        }else{
            holder.gt.setText("Nữ");
        }
        holder.kynang.setText(nhanVien.getKynang());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tenNV, sdt, dob, gt, kynang;

        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            tenNV = view.findViewById(R.id.tenNV);
            sdt = view.findViewById(R.id.sdt);
            dob = view.findViewById(R.id.dob);
            gt = view.findViewById(R.id.gt);
            kynang = view.findViewById(R.id.kynang);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            viewHolderListener.onClick(view, getAdapterPosition());
        }
    }
}
