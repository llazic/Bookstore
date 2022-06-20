package com.example.pki_projekat.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pki_projekat.model.Data;

public class DataViewModel extends ViewModel {

    private MutableLiveData<Data> dataLiveData;

    public DataViewModel(){
        dataLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Data> getDataLiveData() {
        return dataLiveData;
    }

    public void setDataLiveData(Data data){
        dataLiveData.setValue(data);
    }

    public void postDataLiveData(Data data){
        dataLiveData.postValue(data);
    }
}
