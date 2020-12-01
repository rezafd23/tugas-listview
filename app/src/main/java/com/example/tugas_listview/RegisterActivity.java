package com.example.tugas_listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tugas_listview.model.BeritaModel;
import com.example.tugas_listview.viewmodel.BeritaViewModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private AppCompatImageView btn_save;
    private ArrayList<BeritaModel> arrayList = new ArrayList<>();
    private AppCompatEditText et_title,et_category,et_url;
    private static String json="";
    private BeritaViewModel beritaViewModel;
    private BeritaModel beritaModel;
    private static String message="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initView(){
        beritaModel = new BeritaModel();
        btn_save=findViewById(R.id.btn_save);
        et_title=findViewById(R.id.et_title);
        et_category=findViewById(R.id.et_category);
        et_url=findViewById(R.id.et_url);
        beritaViewModel= ViewModelProviders.of(this).get(BeritaViewModel.class);
    }

    private void saveData(){
        String title = et_title.getText().toString();
        String category = et_category.getText().toString();
        String url = et_url.getText().toString();

        BeritaModel model = new BeritaModel();
        model.setTitle(title);
        model.setCategory(category);
        model.setUrl(url);

        beritaViewModel.addBerita(model).observe(this,beritaResponse -> {
            message=beritaResponse.getMessage();
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            finish();
        });

    }

}