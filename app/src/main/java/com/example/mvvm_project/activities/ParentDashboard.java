package com.example.mvvm_project.activities;

import static com.example.mvvm_project.constant.Constant.DRIVER;
import static com.example.mvvm_project.constant.Constant.MY_TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_project.Firebase_Initialize;
import com.example.mvvm_project.R;
import com.example.mvvm_project.databinding.ActivityParentDashboradBinding;
import com.example.mvvm_project.model.RegisterModel;
import com.example.mvvm_project.viewmodel.ParentDashboardViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ParentDashboard extends AppCompatActivity {

    public static ActivityParentDashboradBinding binding;
    ParentDashboardViewModel parentDashboardViewModel;
    Handler handler = new Handler();
    String userCurrentLocation;

    String driverLocation;
    String a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_dashborad);


        parentDashboardViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ParentDashboardViewModel.class);
        binding.setItem(parentDashboardViewModel);

        double p_latitude = ParentTrackActivity.p_latitude;
        double p_lon = ParentTrackActivity.p_longitude;
        userCurrentLocation=getIntent().getStringExtra("lat")+","+getIntent().getStringExtra("lang");

        Log.i(MY_TAG, "onCreate: "+userCurrentLocation);

        Firebase_Initialize obj=Firebase_Initialize.getInstance();

        obj.getFirebaseDatabase().getReference().child(DRIVER).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    RegisterModel model=dataSnapshot.getValue(RegisterModel.class);
                    driverLocation=model.getLatitude()+","+model.getLongitude();
                    binding.webview.loadUrl("http://maps.google.com/maps?" + "saddr=" + p_latitude+","+p_lon+ "" + "&daddr=" + driverLocation);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.getSettings().setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        binding.webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null || url.startsWith("http://") || url.startsWith("https://"))
                    return false;

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    view.getContext().startActivity(intent);
                    return true;
                } catch (Exception e) {
                    Log.i(MY_TAG, "shouldOverrideUrlLoading Exception:" + e);
                    return true;
                }
            }
        });




//        final int TIME_BETWEEN_RELOAD = 5000;
//        final Handler myHandler = new Handler();
//
//
//        final Runnable reloadWebViewRunnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d("run", "running the runnable now");
//                // Continue the reload every 5 seconds
//                a=k;
//                b=kk;
//                Toast.makeText(ParentDashboard.this, "ll", Toast.LENGTH_SHORT).show();
//                Log.i(MY_TAG, "run: "+a);
//                Log.i(MY_TAG, "run: "+b);
//                myHandler.postDelayed(this, TIME_BETWEEN_RELOAD);
//
//            }
//        };
//// start the initial reload
//        myHandler.postDelayed(reloadWebViewRunnable, TIME_BETWEEN_RELOAD);








    }


}