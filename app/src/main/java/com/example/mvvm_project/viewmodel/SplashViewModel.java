package com.example.mvvm_project.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_project.MainActivity;
import com.example.mvvm_project.R;
import com.example.mvvm_project.model.SplashModel;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SplashViewModel extends AndroidViewModel {



    SplashModel model;
    Handler handler;
    Context context;
    public SplashViewModel(@NonNull Application application) {
        super(application);
        model=new SplashModel("School Vehicle Tracking",R.drawable.logo);
        this.context=application;

    }

    public SplashModel getModel() {
        return model;
    }


    public void nextActivity()
    {
        context.startActivity(new Intent(context, MainActivity.class));
    }







}
