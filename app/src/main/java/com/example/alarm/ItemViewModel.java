package com.example.alarm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {

    private final MutableLiveData<String> alarm = new MutableLiveData<>();

    public void setAlarm(String item){
        alarm.setValue(item);
    }
//    public void addAlsrm(String item){alarm.add}
    public LiveData<String> getselecteditem(){
        return alarm;
    }
}
