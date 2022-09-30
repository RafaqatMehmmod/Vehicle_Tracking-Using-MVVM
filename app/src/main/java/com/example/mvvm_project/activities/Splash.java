package com.example.mvvm_project.activities;

import static com.example.mvvm_project.constant.Constant.MY_TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_project.MainActivity;
import com.example.mvvm_project.R;
import com.example.mvvm_project.databinding.ActivitySplashBinding;
import com.example.mvvm_project.viewmodel.SplashViewModel;

public class Splash extends AppCompatActivity {

    ActivitySplashBinding binding;
    SplashViewModel splashViewModel;
    public static String k,driverUniqueName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        splashViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SplashViewModel.class);
        binding.setItem(splashViewModel);

        SharedPreferences sharedPreferences = getSharedPreferences("db", Context.MODE_PRIVATE);
        k = sharedPreferences.getString("k", "null");
        driverUniqueName = sharedPreferences.getString("uniqueName", "null");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.i(MY_TAG, "run: "+k+"---"+driverUniqueName);
                if (k.equals("Driver")) {

                    Intent intent = new Intent(Splash.this, DriverDashboard.class);
                    intent.putExtra("uniqueName",driverUniqueName);
                    startActivity(intent);
                    finish();
                    Log.i("mehmood", "onCreate++++++++++: " + k);
                } else if (k.equals("Parent")) {

                    Intent intent = new Intent(Splash.this, ParentTrackActivity.class);
                    intent.putExtra("uniqueName",driverUniqueName);
                    startActivity(intent);
                    finish();
                    Log.i("mehmood", "onCreate++++++++++: " + k);
                } else if (k.equals("null")) {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, 2000);


    }
}