package com.omshanti.workout.other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class PushNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                //icon
                .setTicker("ticker")
                .setContentTitle("Notification")
                .setAutoCancel(true);
    }
}
