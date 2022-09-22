package com.example.mvvm_project.activities;

import static com.example.mvvm_project.constant.Constant.MY_TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_project.R;
import com.example.mvvm_project.databinding.ActivityRegisterBinding;
import com.example.mvvm_project.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    public static ActivityRegisterBinding binding;
    RegisterViewModel registerViewModel;
    public  static ActivityResultLauncher<String> picImage;
    public static Bitmap bitmap;
    public static Uri imgUri=null;
    public static String loginUserName;
    public static ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);

        loginUserName=getIntent().getStringExtra("key");
        Toast.makeText(this, ""+loginUserName, Toast.LENGTH_SHORT).show();

        registerViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RegisterViewModel.class);

        SharedPreferences sharedPreferences=getSharedPreferences("db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("k",loginUserName);
        editor.apply();
        editor.commit();

        dialog=new ProgressDialog(this);
        Log.i(MY_TAG, "onCreate: "+loginUserName);
        binding.setItem(registerViewModel);
        picImage =registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        // Handle the returned Uri
                        imgUri=uri;
                        if (imgUri==null)
                        {

                        }
                        else {

                                ///bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                                binding.selectedImage.setImageBitmap(bitmap);
                                binding.selectedImage.setImageURI(imgUri);
                                Log.i(MY_TAG, "onActivityResult: " + imgUri);
                        }

                    }
                });
    }
}