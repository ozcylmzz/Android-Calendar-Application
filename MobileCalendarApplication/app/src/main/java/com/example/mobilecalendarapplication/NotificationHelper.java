package com.example.mobilecalendarapplication;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    public static final String channel2ID = "channel2ID";
    public static final String channel2Name = "Channel2 Name";
    public static final String channel3ID = "channel3ID";
    public static final String channel3Name = "Channel3 Name";
    public static final String channel4ID = "channel4ID";
    public static final String channel4Name = "Channel4 Name";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        NotificationChannel channel2 = new NotificationChannel(channel2ID, channel2Name, NotificationManager.IMPORTANCE_HIGH);
        NotificationChannel channel3 = new NotificationChannel(channel3ID, channel3Name, NotificationManager.IMPORTANCE_HIGH);
        NotificationChannel channel4 = new NotificationChannel(channel4ID, channel4Name, NotificationManager.IMPORTANCE_HIGH);

        Uri alarmSound = Uri.parse("android.resource://"
                + this.getPackageName() + "/" + R.raw.juntos);

        channel2.setSound(alarmSound, null);

        alarmSound = Uri.parse("android.resource://"
                + this.getPackageName() + "/" + R.raw.doneforyou);
        channel3.setSound(alarmSound, null);

        alarmSound = Uri.parse("android.resource://"
                + this.getPackageName() + "/" + R.raw.inflicted);
        channel4.setSound(alarmSound, null);

        getManager().createNotificationChannel(channel);
        getManager().createNotificationChannel(channel2);
        getManager().createNotificationChannel(channel3);
        getManager().createNotificationChannel(channel4);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(Activity activity, int reminder, int channelno) {

        Log.d("Notification => ", activity.toString());
        Log.d("Notification => ", activity.getName());
        Log.d("Notification => ", activity.getDescription());
        Log.d("Notification => ", activity.getStartDate());
        Log.d("Notification => ", activity.getStartTime());
        Log.d("Notification => ", activity.getEndDate());
        Log.d("Notification => ", activity.getEndTime());
        Log.d("Notification => ", activity.getRemeinderStartDate1());
        Log.d("Notification => ", activity.getRemeinderStartTime1());
        Log.d("Notification => ", activity.getRemeinderStartDate2());
        Log.d("Notification => ", activity.getRemeinderStartTime2());
        Log.d("Notification => ", activity.getAddress());

        Log.d("Notification => ", String.valueOf(reminder));

        String name = activity.getName();
        String title = (reminder + 1) + ". hatırlatma zamanı!";
        String text = "Aktivite Detayları:" +
                "\nTanımı: " + activity.getDescription() +
                "\nBaşlangıç Zamanı: " + activity.getStartDate() + " " + activity.getStartTime() +
                "\nAdresi: " + activity.getAddress();

        Uri alarmSound = Uri.parse("android.resource://"
                + this.getPackageName() + "/" + R.raw.juntos);

        Uri alarmSound2 = Uri.parse("android.resource://"
                + this.getPackageName() + "/" + R.raw.doneforyou);

        Uri alarmSound3 = Uri.parse("android.resource://"
                + this.getPackageName() + "/" + R.raw.inflicted);

        if (channelno == 1) {
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(title)
                    .setContentText(text)
                    // burda notification resmi seçilmeli
                    .setSmallIcon(R.drawable.mobil)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(text).setSummaryText(name))
//                kullanılabilir fonksiyn
//                .setVibrate()
//                .setSubText()
//                .setSound()
//                .setFullScreenIntent()
//                .set
                    ;
        } else if (channelno == 2) {
            return new NotificationCompat.Builder(getApplicationContext(), channel2ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    // burda notification resmi seçilmeli
                    .setSmallIcon(R.drawable.mobil)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(text).setSummaryText(name))
                    .setSound(alarmSound)
//                kullanılabilir fonksiyn
//                .setVibrate()
//                .setSubText()
//                .setSound()
//                .setFullScreenIntent()
//                .set
                    ;
        } else if (channelno == 3) {
            return new NotificationCompat.Builder(getApplicationContext(), channel3ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    // burda notification resmi seçilmeli
                    .setSmallIcon(R.drawable.mobil)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(text).setSummaryText(name))
                    .setSound(alarmSound2)
//                kullanılabilir fonksiyn
//                .setVibrate()
//                .setSubText()
//                .setSound()
//                .setFullScreenIntent()
//                .set
                    ;
        } else {
            return new NotificationCompat.Builder(getApplicationContext(), channel4ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    // burda notification resmi seçilmeli
                    .setSmallIcon(R.drawable.mobil)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(text).setSummaryText(name))
                    .setSound(alarmSound3)
//                kullanılabilir fonksiyn
//                .setVibrate()
//                .setSubText()
//                .setSound()
//                .setFullScreenIntent()
//                .set
                    ;
        }
    }

}