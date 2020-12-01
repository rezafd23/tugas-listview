package com.example.tugas_listview.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas_listview.model.BeritaModel;
import com.example.tugas_listview.model.BeritaResponse;
import com.example.tugas_listview.networking.BeritaRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritaResponse> mutableLiveData;
    private BeritaRepository beritaRepository;

    public void init(){
        if (mutableLiveData!=null){
            return;
        }
        beritaRepository = BeritaRepository.getInstance();
        mutableLiveData=beritaRepository.getBerita("1","10");
    }

    public LiveData<BeritaResponse> getBerita(){
        return mutableLiveData;
    }

    public LiveData<BeritaResponse> addBerita(BeritaModel model){
        if (mutableLiveData==null){
            beritaRepository =BeritaRepository.getInstance();
        }
        mutableLiveData=beritaRepository.addBerita(model);
        return mutableLiveData;
    }
    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = beritaRepository.getBerita(page,limit);
            return;
        }
        beritaRepository = BeritaRepository.getInstance();
        mutableLiveData = beritaRepository.getBerita("1","10");
    }
}
