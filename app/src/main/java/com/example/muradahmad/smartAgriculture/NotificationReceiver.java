package com.example.muradahmad.smartAgriculture;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static com.example.muradahmad.smartAgriculture.Settings.CHANNEL_1_ID;

/**
 * Created by muradahmad on 12/11/2018.
 */

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Log.d("receiver", "received request to send notification");

        //
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent contentIntent = PendingIntent.getActivity(context,100,activityIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        createNotificationChannels(context);


        String temperature = intent.getStringExtra("temperature");
        // Build notification
        // Actions
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_notification_24dp)
                //.addAction(R.drawable.ic_notification_24dp, "Call", pendingIntent)
                .setContentTitle("Plants feedback")
                .setContentText("Temperature:" + temperature)
                .setShowWhen(true)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        // hide the notification after its selected
        // notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(100, mBuilder.build());


    }

    private void createNotificationChannels(Context c) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is Channel 1");


            NotificationManager notificationManager = c.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);


        }
    }
}