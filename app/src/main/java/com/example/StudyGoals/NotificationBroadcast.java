package com.example.StudyGoals;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.StudyGoals.StudyGoalNotification.CHANNEL_1_ID;

public class NotificationBroadcast extends BroadcastReceiver {
    NotificationManagerCompat notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("Trin Trin!")
                .setContentText("It's time to study.Let's get started :)")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, notification.build());

    }
}
