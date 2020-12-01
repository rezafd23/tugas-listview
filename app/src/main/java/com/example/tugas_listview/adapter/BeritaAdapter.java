package com.example.tugas_listview.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugas_listview.R;
import com.example.tugas_listview.model.BeritaModel;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    Context context;
    private ArrayList<BeritaModel> list;

    public BeritaAdapter(Context context, ArrayList<BeritaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_category.setText(list.get(position).getCategory());
        Glide.with(context).load(list.get(position).getUrl()).into(holder.img_list);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tv_title,tv_category;
        AppCompatImageView img_list;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_category=itemView.findViewById(R.id.tv_category);
            img_list=itemView.findViewById(R.id.img_list);
        }
    }
}
