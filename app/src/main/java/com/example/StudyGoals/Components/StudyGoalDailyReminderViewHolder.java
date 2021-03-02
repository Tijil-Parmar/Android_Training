package com.example.StudyGoals.Components;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyGoals.DailyNotification.StudyGoalNotification;
import com.example.StudyGoals.R;
import com.example.StudyGoals.StudyModel.StudyActivity;
import com.example.StudyGoals.StudyModel.StudyGoal;

public class StudyGoalDailyReminderViewHolder extends RecyclerView.ViewHolder {
    private Switch reminderSwitch;
    private TextView reminderTime;
    StudyGoal studyGoal= StudyGoal.getStudyGoal();

    public StudyGoalDailyReminderViewHolder(@NonNull View itemView) {
        super(itemView);
        reminderSwitch = itemView.findViewById(R.id.enableNotificationSwitch);
        reminderTime = itemView.findViewById(R.id.setTimeTV);
    }
    public void dailyReminderSetData(String dailyReminderValue, Context context){
        reminderTime.setText(dailyReminderValue);
        reminderSwitch.setChecked(studyGoal.enableReminder());

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StudyGoalNotification studyGoalNotificationObject = new StudyGoalNotification();
                studyGoal.setReminder(isChecked);
                StudyActivity studyActivity= new StudyActivity();
                Log.d("SWITCH STATE", String.valueOf(isChecked));
                if(!studyGoal.isReminder)
                {
                    Toast.makeText(context.getApplicationContext(),"Reminder Turned OFF",Toast.LENGTH_LONG).show();
                    studyGoalNotificationObject.cancelReminder(context.getApplicationContext());
                }
                if(studyGoal.isReminder)
                {
                    Toast.makeText(context.getApplicationContext(),"Reminder Turned ON",Toast.LENGTH_LONG).show();
                    String s = (String) studyGoal.notificationReminderTime;
                    String namepass[] = s.split(":");
                    long reminderHourValue = Long.parseLong(namepass[0]);
                    long reminderMinuteValue = Long.parseLong(namepass[1]);
                    studyGoalNotificationObject.setReminder(context.getApplicationContext(), reminderHourValue, reminderMinuteValue);
                }
            }
        });
    }
}
