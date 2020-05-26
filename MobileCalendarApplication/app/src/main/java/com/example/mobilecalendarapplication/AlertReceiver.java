package com.example.mobilecalendarapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    Activity activity;
    int reminder;
    int channelno;

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences("0", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();


        Bundle bundle = intent.getBundleExtra("bundle");
        activity = (Activity) bundle.getSerializable("activity");
        reminder = bundle.getInt("reminder", -1);
        channelno = sharedpreferences.getInt("sound",1);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(activity, reminder,channelno);
        notificationHelper.getManager().notify(reminder, nb.build());



        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(500);
        }




    }

}
