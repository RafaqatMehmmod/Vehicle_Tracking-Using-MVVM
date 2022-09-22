package com.example.mvvm_project.viewmodel;

import static com.example.mvvm_project.activities.RegisterActivity.binding;
import static com.example.mvvm_project.activities.RegisterActivity.dialog;
import static com.example.mvvm_project.activities.RegisterActivity.imgUri;
import static com.example.mvvm_project.activities.RegisterActivity.picImage;
import static com.example.mvvm_project.constant.Constant.DRIVER;
import static com.example.mvvm_project.constant.Constant.MY_TAG;
import static com.example.mvvm_project.constant.Constant.REGISTER_DRIVER;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvm_project.Firebase_Initialize;
import com.example.mvvm_project.R;
import com.example.mvvm_project.activities.DriverDashboard;
import com.example.mvvm_project.model.RegisterModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterViewModel extends AndroidViewModel {


    RegisterModel model;
    Context context;
    Firebase_Initialize obj;



    public RegisterViewModel(@NonNull Application application) {
        super(application);
        model = new RegisterModel(R.string.app_title, R.drawable.logo, "bitmap.toString()", "Mehmood", "Awan", "dn38");
        this.context = application;
        obj = Firebase_Initialize.getInstance();


    }

    public RegisterModel getModel() {
        return model;
    }

    public void selectImage() {
        picImage.launch("image/*");

    }


    public void registerFun() {

        String name = binding.editName.getText().toString();
        String schoolName = binding.editSchoolName.getText().toString();
        String veh = binding.editVehicleNo.getText().toString();

        if (imgUri == null) {
            Toast.makeText(context, "Select Img", Toast.LENGTH_SHORT).show();
        } else if (name.isEmpty()) {
            Toast.makeText(context, "Name", Toast.LENGTH_SHORT).show();
        } else if (schoolName.isEmpty()) {
            Toast.makeText(context, "School Name", Toast.LENGTH_SHORT).show();
        } else if (veh.isEmpty()) {
            Toast.makeText(context, "Vehicle No", Toast.LENGTH_SHORT).show();
        } else {
//            dialog=new ProgressDialog(context.getApplicationContext());
            dialog.show();
            dialog.setTitle("Upload Record");
            StorageReference ref=obj.getStorageReference().getReference().child(REGISTER_DRIVER).child(name+"-"+veh);
            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            model.setUserProfile(uri.toString());
                            model.setUserName(name);
                            model.setUserSchoolName(schoolName);
                            model.setVehicleNo(veh);
                            obj.getFirebaseDatabase().getReference().child(DRIVER).child(name + "-" + veh).setValue(model);
                            dialog.dismiss();
                            nextActivity();
                            Log.i(MY_TAG, "registerFun: " + imgUri);
                            Log.i(MY_TAG, "registerFun: " + name);
                            Log.i(MY_TAG, "registerFun: " + schoolName);
                            Log.i(MY_TAG, "registerFun: " + veh);
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

    private void nextActivity()
    {
        Intent intent=new Intent(context, DriverDashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
