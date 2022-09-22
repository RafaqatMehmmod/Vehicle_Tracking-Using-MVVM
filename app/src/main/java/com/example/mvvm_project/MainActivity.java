package com.example.mvvm_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mvvm_project.databinding.ActivityMainBinding;
import com.example.mvvm_project.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);


        myViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel.class);
//        binding.setItem(myViewModel.getStudent());
        binding.setItem(myViewModel);



    }
}