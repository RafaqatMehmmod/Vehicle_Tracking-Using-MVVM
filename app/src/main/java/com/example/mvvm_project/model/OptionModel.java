package com.example.mvvm_project.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class OptionModel {

    private int driver;
    private int student;
    private int title;
    private int logo;




    public OptionModel(int driver, int student,int title,int logo) {
        this.driver = driver;
        this.student = student;
        this.title = title;
        this.logo=logo;
    }

    public int getDriver() {
        return driver;
    }

    public void setDriver(int driver) {
        this.driver = driver;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }



    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }


    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    @BindingAdapter({"android:optionSrc"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
