package com.example.mvvm_project.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.mvvm_project.service.LocationService;

public class RestartReceiver extends BroadcastReceiver {
    private static final String TAG = "RestartServiceReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//
//             if (action.equals(Intent.ACTION_MY_PACKAGE_SUSPENDED))
//             {
//            if (!registerViewModel.isMyServiceRunning(LocationService.class)) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    context.startForegroundService(new Intent(context, LocationService.class));
//                } else {
//                    context.startService(new Intent(context, LocationService.class));
//                }
//                Toast.makeText(context, "kkkkkkkkk", Toast.LENGTH_SHORT).show();
//            }
//        }
//             else
//             {
//                 Toast.makeText(context, "mmmmmmmmm", Toast.LENGTH_SHORT).show();
//             }
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context.getApplicationContext(), LocationService.class));
        }


    }
}
