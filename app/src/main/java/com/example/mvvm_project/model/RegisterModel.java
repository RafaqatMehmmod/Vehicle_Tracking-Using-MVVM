package com.example.mvvm_project.model;

import static com.example.mvvm_project.constant.Constant.MY_TAG;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 16/09/2022
 */
public class RegisterModel {

    private int title;
    private String userProfile;
    private String userName;
    private String userSchoolName;
    private String vehicleNo;
    private String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    private String longitude;


    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    private String uniqueName;

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    private int logo;

    public RegisterModel(int title, int logo, String userProfile, String userName, String userSchoolName, String vehicleNo,String uniqueName,String latitude,String longitude) {
        this.title = title;
        this.logo = logo;
        this.userProfile = userProfile;
        this.userName = userName;
        this.userSchoolName = userSchoolName;
        this.vehicleNo = vehicleNo;
        this.uniqueName=uniqueName;
        this.latitude=latitude;
        this.longitude=longitude;
    }


    public RegisterModel() {
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSchoolName() {
        return userSchoolName;
    }

    public void setUserSchoolName(String userSchoolName) {
        this.userSchoolName = userSchoolName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }


    @BindingAdapter({"android:img"})
    public static void setImage(ImageView imageView, int b) {
        imageView.setImageResource(b);
        Log.i(MY_TAG, "setImage: ");

    }

}
