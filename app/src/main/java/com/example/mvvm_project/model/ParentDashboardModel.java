package com.example.mvvm_project.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ParentDashboardModel {
    int title;

    public ParentDashboardModel(int title, int logo) {
        this.title = title;
        this.logo = logo;
    }

    public ParentDashboardModel() {
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

    int logo;


    //NWImgNS
    @BindingAdapter({"android:parentDashboard"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
