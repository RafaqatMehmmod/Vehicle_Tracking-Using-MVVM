package com.example.mvvm_project.viewmodel;

import static com.example.mvvm_project.activities.DriverDashboard.driverDashboardBinding;
import static com.example.mvvm_project.activities.DriverDashboard.latitude;
import static com.example.mvvm_project.activities.DriverDashboard.longitude;
import static com.example.mvvm_project.activities.RegisterActivity.binding;
import static com.example.mvvm_project.activities.RegisterActivity.dialog;
import static com.example.mvvm_project.activities.RegisterActivity.imgUri;
import static com.example.mvvm_project.activities.RegisterActivity.loginUserName;
import static com.example.mvvm_project.activities.RegisterActivity.picImage;
import static com.example.mvvm_project.constant.Constant.DRIVER;
import static com.example.mvvm_project.constant.Constant.MY_TAG;
import static com.example.mvvm_project.constant.Constant.PARENT;
import static com.example.mvvm_project.service.RepeatingThread.keepRunning;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.Firebase_Initialize;
import com.example.mvvm_project.R;
import com.example.mvvm_project.activities.DriverDashboard;
import com.example.mvvm_project.activities.ParentTrackActivity;
import com.example.mvvm_project.model.RegisterModel;
import com.example.mvvm_project.service.LocationService;
import com.example.mvvm_project.service.RepeatingThread;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterViewModel extends AndroidViewModel {
    RegisterModel model;
    Context context;
    public static Firebase_Initialize obj;
    String name,schoolName,vehicleNo;
    public static Runnable runnable;
    public static Handler mHandler = new Handler();


    public RegisterViewModel(@NonNull Application application) {
        super(application);
        model = new RegisterModel(R.string.app_title, R.drawable.logo, "No Image", "No Name", "No Name", "No Name","No Name","No Name","Name");
        this.context = application;
        obj = Firebase_Initialize.getInstance();
        Log.i(MY_TAG, "RegisterViewModel: ");


    }


    public RegisterModel getModel() {
        return model;
    }

    public void selectImage() {
        picImage.launch("image/*");

    }

    public void registerFun() {

        name = binding.editName.getText().toString();
        schoolName = binding.editSchoolName.getText().toString();
        vehicleNo = binding.editVehicleNo.getText().toString();

        if (imgUri == null) {
            Toast.makeText(context, "Select Img", Toast.LENGTH_SHORT).show();
        } else if (name.isEmpty()) {
            Toast.makeText(context, "Name", Toast.LENGTH_SHORT).show();
        } else if (schoolName.isEmpty()) {
            Toast.makeText(context, "School Name", Toast.LENGTH_SHORT).show();
        } else if (vehicleNo.isEmpty()) {
            Toast.makeText(context, "Vehicle No", Toast.LENGTH_SHORT).show();
        } else {

            if (loginUserName.equals(DRIVER)) {
                driverDatabase(DRIVER, DriverDashboard.class);
            }
            else
            {
                driverDatabase(PARENT, ParentTrackActivity.class);
            }

        }
    }

    private void nextActivity(Class c) {
        Intent intent = new Intent(context, c);
        intent.putExtra("uniqueName",name+"-"+vehicleNo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void switchFun() {

        if (driverDashboardBinding.mySwitch.isChecked()) {
            Log.i(MY_TAG, "RegisterViewModel..........ON.......: ");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                keepRunning = true;
                ContextCompat.startForegroundService(context, new Intent(context, LocationService.class));
                Log.i(MY_TAG, "switchFun: " + latitude + "-" + longitude);
            } else {
                keepRunning = true;
                context.startService(new Intent(context, LocationService.class));
            }

        } else {

            context.stopService(new Intent(context, LocationService.class));
            RepeatingThread.stopThread();


        }

    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    private void driverDatabase(String s,Class c)
    {

            dialog.show();
            dialog.setTitle("Upload Record");
            StorageReference ref = obj.getStorageReference().getReference().child(s).child(name + "-" + vehicleNo);
            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            model.setUserProfile(uri.toString());
                            model.setUserName(name);
                            model.setUserSchoolName(schoolName);
                            model.setVehicleNo(vehicleNo);
                            model.setUniqueName(name + "-" + vehicleNo);
                            obj.getFirebaseDatabase().getReference().child(s).child(name + "-" + vehicleNo).setValue(model);
                            dialog.dismiss();
                            nextActivity(c);
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    float percent = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    dialog.setMessage("Uploaded :" + (int) percent + "%");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                }
            });


    }

}
