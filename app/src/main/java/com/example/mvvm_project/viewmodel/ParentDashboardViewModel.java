package com.example.mvvm_project.viewmodel;

import static com.example.mvvm_project.activities.ParentTrackActivity.p_latitude;
import static com.example.mvvm_project.activities.ParentTrackActivity.p_longitude;
import static com.example.mvvm_project.activities.ParentTrackActivity.parentTrackBinding;
import static com.example.mvvm_project.constant.Constant.DRIVER;
import static com.example.mvvm_project.constant.Constant.MY_TAG;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.Firebase_Initialize;
import com.example.mvvm_project.R;
import com.example.mvvm_project.activities.ParentDashboard;
import com.example.mvvm_project.model.RegisterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 16/09/2022
 */
public class ParentDashboardViewModel extends AndroidViewModel {


    RegisterModel parentDashboardModel;
    Context context;
    public static Firebase_Initialize obj;
    String name,vehicleNo;
    double lat;
    double lan;

    public ParentDashboardViewModel(Application application) {
        super(application);
        parentDashboardModel = new RegisterModel(R.string.app_title, R.drawable.logo, "No Image", "No Name", "No Name", "No Name","No Name","No Name","Name");
        this.context=application;
        obj = Firebase_Initialize.getInstance();
        lat=p_latitude;
        lan=p_longitude;
        Log.i(MY_TAG, "ParentDashboardViewModel: "+lat+","+lan);
    }


    public RegisterModel getParentDashboardModel() {
        return parentDashboardModel;
    }


    public void trackFun()
    {
        name =parentTrackBinding.editName.getText().toString();
        vehicleNo = parentTrackBinding.editVehicleNo.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(context, "Name", Toast.LENGTH_SHORT).show();
        }
        else if (vehicleNo.isEmpty()) {
            Toast.makeText(context, "Vehicle No", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(MY_TAG, "ParentDashboardViewModel   In: "+lat+","+lan);
            obj.getFirebaseDatabase().getReference().child(DRIVER).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        RegisterModel model=dataSnapshot.getValue(RegisterModel.class);
                        if (model.getUniqueName().equals(name+"-"+vehicleNo))
                        {
                            Intent intent=new Intent(context, ParentDashboard.class);
                            intent.putExtra("lat",lat);
                            intent.putExtra("lang",lan);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            ParentTrackActivity parentTrackActivity=new ParentTrackActivity();
//                            parentTrackActivity.finish();
                            context.startActivity(intent);


                        }
                        else
                        {
                            Toast.makeText(context, "Wrong Information", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}
