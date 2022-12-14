package com.example.mvvm_project.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class SplashModel {

    private int title;
    private int logo;

    public SplashModel(int title, int logo) {
        this.title = title;
        this.logo = logo;
    }

    public SplashModel() {
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


    //NWImgNS
    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
