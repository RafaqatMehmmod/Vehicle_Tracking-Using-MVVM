package com.example.mvvm_project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.model.Student;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 16/09/2022
 */
public class MyViewModel extends AndroidViewModel {


    Student student;
    public MyViewModel(@NonNull Application application) {
        super(application);
        student=new Student("Rafaqat Mehmood","Arshad Mehmood");
    }


    public Student getStudent() {
        return this.student;
    }
}
