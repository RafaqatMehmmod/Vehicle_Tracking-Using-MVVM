package com.example.mvvm_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvm_project.R;
import com.example.mvvm_project.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);

        Toast.makeText(this, ""+getIntent().getStringExtra("key"), Toast.LENGTH_SHORT).show();
    }
}