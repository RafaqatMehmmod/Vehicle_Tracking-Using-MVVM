package com.example.mvvm_project.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.R;
import com.example.mvvm_project.model.ParentDashboardModel;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 16/09/2022
 */
public class ParentDashboardViewModel extends AndroidViewModel {


    ParentDashboardModel parentDashboardModel;
    Context context;
    public ParentDashboardViewModel(Application application) {
        super(application);
        parentDashboardModel=new ParentDashboardModel(R.string.app_title,R.drawable.logo);
        this.context=application;
    }


    public ParentDashboardModel getParentDashboardModel() {
        return parentDashboardModel;
    }


}
