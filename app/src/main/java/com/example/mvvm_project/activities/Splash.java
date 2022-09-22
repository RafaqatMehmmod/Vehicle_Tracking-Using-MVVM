package com.example.mvvm_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;

import com.example.mvvm_project.R;
import com.example.mvvm_project.databinding.ActivitySplashBinding;
import com.example.mvvm_project.viewmodel.SplashViewModel;

public class Splash extends AppCompatActivity {

    ActivitySplashBinding binding;
    SplashViewModel splashViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_splash);

        splashViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SplashViewModel.class);
        binding.setItem(splashViewModel);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             binding.getItem().nextActivity();
             finish();

            }
        },2000);
    }
}