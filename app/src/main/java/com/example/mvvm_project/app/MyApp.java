package com.example.mvvm_project.app;

import static com.example.mvvm_project.constant.Constant.LOCATION_CHANNEL;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApp extends Application {

    public static NotificationManager manager;
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {

        //check oreo r Higher
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel
                    (LOCATION_CHANNEL,"Location Service", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is Location Service");

            manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
