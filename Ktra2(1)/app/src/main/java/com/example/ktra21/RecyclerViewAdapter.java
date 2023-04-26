package com.example.ktra21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra21.model.CongViec;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<CongViec> list;

    private ViewHolderListener viewHolderListener;

    public void setViewHolderListener(ViewHolderListener viewHolderListener) {
        this.viewHolderListener = viewHolderListener;
    }

    public void setList(List<CongViec> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    public CongViec getCV(int pos){
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
        CongViec congViec = this.list.get(position);
        holder.tenCV.setText(congViec.getTen());
        holder.noidung.setText(congViec.getNoidung());
        holder.ngayHT.setText(congViec.getNgayHT());
        holder.tinhtrang.setText(congViec.getTinhtrang());
        if(congViec.getCongtac()== true){
            holder.congtac.setText("Làm chung");
        }else{
            holder.congtac.setText("Một mình");
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tenCV, noidung, ngayHT, tinhtrang, congtac;

        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            tenCV = view.findViewById(R.id.tenCV);
            noidung = view.findViewById(R.id.noidung);
            ngayHT = view.findViewById(R.id.ngayHT);
            tinhtrang = view.findViewById(R.id.tinhtrang);
            congtac = view.findViewById(R.id.congtac);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            viewHolderListener.onClick(view, getAdapterPosition());
        }
    }
}
