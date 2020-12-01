package com.example.tugas_listview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.tugas_listview.adapter.BeritaAdapter;
import com.example.tugas_listview.model.BeritaModel;
import com.example.tugas_listview.viewmodel.BeritaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<BeritaModel> arrTemp =new ArrayList<>();
    private FloatingActionButton fab;
    private RecyclerView list_berita;
    private static int CODE_MAIN_ACTIVITY=72;
    private BeritaAdapter beritaAdapter;
    private BeritaViewModel beritaViewModel;
    private List<BeritaModel> beritaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        fab=findViewById(R.id.fab);
        list_berita=findViewById(R.id.list_berita);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        parse(data.getStringExtra("data"));

    }

    private void parse( String data){
        Gson gson = new Gson();
        BeritaModel model = gson.fromJson(data,BeritaModel.class);
        Log.v("IsiModel: ",model.getTitle());
        arrTemp.add(model);
        beritaAdapter.notifyDataSetChanged();
    }

    private void initData(){
        if (beritaAdapter==null){
            beritaAdapter=new BeritaAdapter(MainActivity.this,arrTemp);
            list_berita.setLayoutManager(new LinearLayoutManager(this));
            list_berita.setAdapter(beritaAdapter);
            list_berita.setItemAnimator(new DefaultItemAnimator());
            list_berita.setNestedScrollingEnabled(true);
        } else {
            beritaAdapter.notifyDataSetChanged();
        }
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);

        beritaViewModel.init();
        beritaViewModel.getBerita().observe(this,beritaResponse -> {
            beritaList=beritaResponse.getData();
            arrTemp.clear();
            arrTemp.addAll(beritaList);
            beritaAdapter.notifyDataSetChanged();
        });
    }
}