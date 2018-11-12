package com.example.muradahmad.smartAgriculture;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by muradahmad on 12/11/2018.
 */

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        //
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(context,100,activityIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        // Actions
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,Settings.CHANNEL_1_ID)
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_notification_24dp)
                //.addAction(R.drawable.ic_notification_24dp, "Call", pendingIntent)
                .setContentTitle("Plants feedback")
                .setContentText("Temperature is not suitable for plant")
                .setShowWhen(true)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        // hide the notification after its selected
        // notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(100, mBuilder.build());


    }

}