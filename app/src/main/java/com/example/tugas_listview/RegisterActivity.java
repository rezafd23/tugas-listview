package com.example.tugas_listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tugas_listview.model.BeritaModel;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(arrayList);
                Log.v("Isi Array: ",jsonArray.toString());
                Intent i = getIntent();
                i.putExtra("data", json);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    private void initView(){
        btn_save=findViewById(R.id.btn_save);
        et_title=findViewById(R.id.et_title);
        et_category=findViewById(R.id.et_category);
        et_url=findViewById(R.id.et_url);
    }

    private void saveData(){
        String title = et_title.getText().toString();
        String category = et_category.getText().toString();
        String url = et_url.getText().toString();

        BeritaModel model = new BeritaModel();
        model.setTitle(title);
        model.setCategory(category);
        model.setImage(url);
        Gson gson = new Gson();
        json=gson.toJson(model);

    }

}