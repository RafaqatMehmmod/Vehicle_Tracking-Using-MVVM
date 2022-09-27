package com.example.mvvm_project.service;

import static com.example.mvvm_project.activities.DriverDashboard.latitude;
import static com.example.mvvm_project.activities.DriverDashboard.longitude;
import static com.example.mvvm_project.activities.DriverDashboard.uniqueName;
import static com.example.mvvm_project.constant.Constant.DRIVER;
import static com.example.mvvm_project.constant.Constant.MY_TAG;
import static com.example.mvvm_project.viewmodel.RegisterViewModel.mHandler;
import static com.example.mvvm_project.viewmodel.RegisterViewModel.obj;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvvm_project.model.RegisterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class RepeatingThread implements Runnable {

    public static boolean keepRunning = true;

    double lat,lng;
    public RepeatingThread() {

    }

    @Override
    public void run() {
        if (keepRunning) {
            lat = latitude;
            lng = longitude;
            Log.i(MY_TAG, "startServiceForeground: " + lat + "-" + lng);
            obj.getFirebaseDatabase().getReference().child(DRIVER).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        RegisterModel model=dataSnapshot.getValue(RegisterModel.class);
                        if (model.getUniqueName().equals(uniqueName))
                        {
                            Log.i(MY_TAG, "startServiceForeground: "+model.getVehicleNo());

                            obj.getFirebaseDatabase().getReference().child(DRIVER).child(uniqueName).child("latitude").setValue(String.valueOf(lat));
                            obj.getFirebaseDatabase().getReference().child(DRIVER).child(uniqueName).child("longitude").setValue(String.valueOf(lng));

//                            Map<String,Object > map=new HashMap<>();
//                            map.put("latitude", String.valueOf(lat));
//                            map.put("longitude ", String.valueOf(lng));
//                            obj.getFirebaseDatabase().getReference().child(DRIVER).child(uniqueName).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    Log.i(MY_TAG, "Update Location onSuccess: ");
//                                }
//                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            mHandler.postDelayed(this, 3000);
        }
        else {
            Log.i(MY_TAG, "run...: ");
        }

    }
   public static void stopThread() {
        keepRunning = false;
    }
}
