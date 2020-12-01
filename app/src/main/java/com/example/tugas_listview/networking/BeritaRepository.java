package com.example.tugas_listview.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tugas_listview.model.BeritaModel;
import com.example.tugas_listview.model.BeritaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaRepository {
    private static BeritaRepository beritaRepository;

    public static BeritaRepository getInstance(){
        if (beritaRepository==null){
            beritaRepository=new BeritaRepository();
        }
        return beritaRepository;
    }

    private BeritaApi beritaApi;

    public BeritaRepository(){
        beritaApi=RetrofitService.createService(BeritaApi.class);
    }

    public MutableLiveData<BeritaResponse> getBerita(String page,String limit){
        MutableLiveData<BeritaResponse> beritaData = new MutableLiveData<>();
        beritaApi.getBerita(page,limit)
                .enqueue(new Callback<BeritaResponse>() {
                    @Override
                    public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("LogGetBerita: ",response.body().toString());
                            beritaData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<BeritaResponse> call, Throwable t) {
                        Log.v("LogErrorFetch: ",t.getMessage());
                        beritaData.setValue(null);
                    }
                });
        return beritaData;
    }

    public MutableLiveData<BeritaResponse> addBerita(BeritaModel model){
        MutableLiveData<BeritaResponse> beritaData=new MutableLiveData<>();
        beritaApi.addBerita(model)
                .enqueue(new Callback<BeritaResponse>() {
                    @Override
                    public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("PostData: ",response.body().toString());
                            beritaData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<BeritaResponse> call, Throwable t) {
                        Log.v("ErrorPostData: ",t.getMessage());
                        beritaData.setValue(null);
                    }
                });
        return  beritaData;
    }
}
