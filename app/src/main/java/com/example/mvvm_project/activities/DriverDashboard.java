package com.example.mvvm_project.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_project.R;
import com.example.mvvm_project.databinding.ActivityDriverDashboardBinding;
import com.example.mvvm_project.viewmodel.RegisterViewModel;

public class DriverDashboard extends AppCompatActivity {

    ActivityDriverDashboardBinding binding;
    RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_driver_dashboard);


        registerViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RegisterViewModel.class);
        binding.setItem(registerViewModel);
    }
}