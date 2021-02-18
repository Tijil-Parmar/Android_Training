package com.example.StudyGoals;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;

public class StudyGoalNotification extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }

    public void setReminder(Context context, long reminderHour, long reminderMinute){

        Intent intent =new Intent(context,NotificationBroadcast.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent,0);
        Log.d("setReminder", "WORKING");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Calendar datetimetoalarm = Calendar.getInstance(Locale.getDefault());
        datetimetoalarm.setTimeInMillis(System.currentTimeMillis());
        StudyGoal studyGoal= StudyGoal.getStudyGoalObject();
        datetimetoalarm.set(Calendar.HOUR_OF_DAY, (int) reminderHour);
        datetimetoalarm.set(Calendar.MINUTE, (int) reminderMinute);
        datetimetoalarm.set(Calendar.SECOND, 0);
        datetimetoalarm.set(Calendar.MILLISECOND, 0);
        Calendar today = Calendar.getInstance(Locale.getDefault());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, datetimetoalarm.getTimeInMillis(), (1000 * 60 * 60 * 24), pendingIntent);
    }

    public void cancelReminder(Context context){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent,0);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        try {
            alarmManager.cancel(pendingIntent);
            Log.e("REMINDER", "Cancelling all pending intents");
        } catch (Exception e) {
            Log.e("REMINDER", "AlarmManager update was not canceled. " + e.toString());
        }
    }
}
