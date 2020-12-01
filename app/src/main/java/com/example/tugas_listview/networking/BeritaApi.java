package com.example.tugas_listview.networking;

import com.example.tugas_listview.model.BeritaModel;
import com.example.tugas_listview.model.BeritaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BeritaApi {

    @GET("berita")
    Call<BeritaResponse> getBerita(@Query("page") String page,
                                   @Query("limit") String limit);

    @POST("berita")
    Call<BeritaResponse> addBerita(@Body BeritaModel body);

}
