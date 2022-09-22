package com.example.mvvm_project.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.activities.RegisterActivity;
import com.example.mvvm_project.model.OptionModel;
import com.example.mvvm_project.model.Student;

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
        optionModel=new OptionModel("As a Driver ","As a Student");
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
        commonFun("Student");
    }

    private void commonFun(String s)
    {
        Intent intent=new Intent(context, RegisterActivity.class);
        intent.putExtra("key",s);
        context.startActivity(intent);
    }
}
