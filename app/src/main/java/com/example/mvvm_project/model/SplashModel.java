package com.example.mvvm_project.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class SplashModel {
    public SplashModel(String title,int logo) {
        this.title = title;
        this.logo=logo;
    }

    public SplashModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    String title;

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    int logo;

    //NWImgNS
    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
