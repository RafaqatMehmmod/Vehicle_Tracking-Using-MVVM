package com.example.mvvm_project.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_project.R;
import com.example.mvvm_project.databinding.ActivityParentDashboradBinding;
import com.example.mvvm_project.viewmodel.ParentDashboardViewModel;

public class ParentDashboard extends AppCompatActivity {

    ActivityParentDashboradBinding binding;
    ParentDashboardViewModel parentDashboardViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_parent_dashborad);


        parentDashboardViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ParentDashboardViewModel.class);
        binding.setItem(parentDashboardViewModel);
    }
}