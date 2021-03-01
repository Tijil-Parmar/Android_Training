package com.example.StudyGoals.DailyNotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.StudyGoals.R;
import com.example.StudyGoals.StudyModel.StudyActivity;

import static com.example.StudyGoals.DailyNotification.StudyGoalNotification.CHANNEL_1_ID;

public class NotificationBroadcast extends BroadcastReceiver {
    NotificationManagerCompat notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentToRepeat = new Intent(context, StudyActivity.class);
        //set flag to restart/relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Pending intent to handle launch of Activity in intent above
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, AlarmManager.RTC_WAKEUP, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);

        int celeEmoji = 0x1F389;
        int clockEmoji = 0x23F0;
        String celebrationemoji = new String(Character.toChars(celeEmoji));
        String clockEmojiString = new String(Character.toChars(clockEmoji));
        String notificationContent = "It's time to study.Let's get started "+celebrationemoji;
        String notificationTitle = "Trin Trin! " + clockEmojiString;
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_CALL);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, notification.build());
    }
}
