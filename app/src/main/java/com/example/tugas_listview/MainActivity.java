package com.example.tugas_listview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.tugas_listview.adapter.BeritaAdapter;
import com.example.tugas_listview.model.BeritaModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<BeritaModel> arrTemp =new ArrayList<>();
    private FloatingActionButton fab;
    private ListView list_berita;
    private static int CODE_MAIN_ACTIVITY=72;
    private BeritaAdapter beritaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                i.putExtra("id", "CEK");
                startActivityForResult(i, CODE_MAIN_ACTIVITY);
            }
        });
    }

    private void initView(){
        fab=findViewById(R.id.fab);
        list_berita=findViewById(R.id.list_berita);
        beritaAdapter = new BeritaAdapter(getApplicationContext(),arrTemp);
        list_berita.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.v("request Code: ",String.valueOf(requestCode));
//        Log.v("resutl: ","isinya: "+data.getStringExtra("data"));
        parse(data.getStringExtra("data"));

    }

    private void parse( String data){
        Gson gson = new Gson();
        BeritaModel model = gson.fromJson(data,BeritaModel.class);
        Log.v("IsiModel: ",model.getTitle());
        arrTemp.add(model);
        beritaAdapter.notifyDataSetChanged();
    }
}