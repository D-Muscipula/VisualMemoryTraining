package com.example.visualmemorytraining.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.visualmemorytraining.data.Data;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainFragmentViewModel extends ViewModel {
    //Отвечает за очки и имейл текущего пользователя
    static int scores;
    static int email;
    private final static MutableLiveData<Integer> userScores = new MutableLiveData<>();
    private final static MutableLiveData<String> userEmail = new MutableLiveData<>();
    public void currentScores(int r){
        scores = r;
        userScores.setValue(r);
    }
    public void currentEmail(String s){
        userEmail.setValue(s);
    }
    public static int getScores(){
        if(scores != 0)
        {return userScores.getValue();}
        return 0;
    }
    public static String getEmail(){
        return userEmail.getValue();
    }
}
