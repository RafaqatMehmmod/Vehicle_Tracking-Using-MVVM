package com.example.mvvm_project.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.R;
import com.example.mvvm_project.activities.RegisterActivity;
import com.example.mvvm_project.model.OptionModel;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 16/09/2022
 */
public class MyViewModel extends AndroidViewModel {


    OptionModel optionModel;
    Context context;
    public MyViewModel(Application application) {
        super(application);
        optionModel=new OptionModel(R.string.driver_title,R.string.parent_title, R.string.app_title,R.drawable.logo);
        this.context=application;
    }


    public OptionModel getStudent() {
        return this.optionModel;
    }

    public void aDriver()
    {
        commonFun("Driver");
    }
    public void aStudent()
    {
        commonFun("Parent");
    }

    private void commonFun(String s)
    {
        Intent intent=new Intent(context, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("key",s);
        context.startActivity(intent);
    }
}
