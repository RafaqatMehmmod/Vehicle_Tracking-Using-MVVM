package com.example.mvvm_project.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.MainActivity;
import com.example.mvvm_project.R;
import com.example.mvvm_project.model.SplashModel;

public class SplashViewModel extends AndroidViewModel {



    SplashModel model;
    Context context;
    public SplashViewModel(@NonNull Application application) {
        super(application);
        model=new SplashModel(R.string.app_title,R.drawable.logo);
        this.context=application;

    }

    public SplashModel getModel() {
        return model;
    }


    public void nextActivity()
    {
        Intent intent=new Intent(context,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }










}
