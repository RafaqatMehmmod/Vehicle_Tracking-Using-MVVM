package com.example.mvvm_project.service;

import static com.example.mvvm_project.app.MyApp.manager;
import static com.example.mvvm_project.constant.Constant.LOCATION_CHANNEL;
import static com.example.mvvm_project.constant.Constant.MY_TAG;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.mvvm_project.R;
import com.example.mvvm_project.activities.DriverDashboard;

public class LocationService extends Service {
    Notification notification;

    public static Thread t;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startServiceForeground(intent);
        return START_STICKY;
    }


    private void startServiceForeground(Intent intent)
    {
        //Create Intent fow which class we are open
        Intent notificationIntent=new Intent(this, DriverDashboard.class);
        //Crete Pending Intent mean that click notication then jump 2nd app to own app
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Create Notification
            notification = new NotificationCompat.Builder(this, LOCATION_CHANNEL)
                    .setContentTitle("My Service Class Run")
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(pendingIntent)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
            manager.notify(12, notification);
       // }
//        else{
//            notification = new NotificationCompat.Builder(this, LOCATION_CHANNEL)
//                    .setContentTitle("My Service Class Run")
//                    .setSmallIcon(R.drawable.logo)
//                    .setContentIntent(pendingIntent)
//                    .setCategory(Notification.CATEGORY_SERVICE)
//                    .build();}
        //This line comment then the app service was close r destroy 1 mint
        startForeground(1,notification);
        t = new Thread(new RepeatingThread());
        t.start();
        Toast.makeText(getApplicationContext(), "Start Service ", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(MY_TAG, "onDestroy: ");
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
//        Intent restartServiceTask = new Intent(getApplicationContext(),LocationService.class);
//        restartServiceTask.setPackage(getPackageName());
//        PendingIntent restartPendingIntent =PendingIntent.getService(getApplicationContext(), 1,restartServiceTask, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager myAlarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        myAlarmService.set(
//                AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime() + 1000,
//                restartPendingIntent);
//        Log.i(MY_TAG, "onTaskRemoved: ");
        super.onTaskRemoved(rootIntent);
    }
}
