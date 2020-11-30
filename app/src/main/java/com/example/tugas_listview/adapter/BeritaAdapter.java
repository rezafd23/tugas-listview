package com.example.tugas_listview.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.example.tugas_listview.R;
import com.example.tugas_listview.model.BeritaModel;

import java.util.ArrayList;

public class BeritaAdapter extends BaseAdapter {

    Context context;
    private ArrayList<BeritaModel> list;

    public BeritaAdapter(Context context, ArrayList<BeritaModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item_view, null);
        }
        BeritaModel model = list.get(position);
        AppCompatTextView title = convertView.findViewById(R.id.tv_title);
        AppCompatTextView category = convertView.findViewById(R.id.tv_category);
        AppCompatImageView imageView = convertView.findViewById(R.id.img_list);

        title.setText(model.getTitle());
        category.setText(model.getCategory());
        Glide.with(context).load(model.getImage()).into(imageView);

        return convertView;
    }
}
