package com.example.StudyGoals;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class StudyGoalManager {
    String examDate="Set Date";
    String notificationReminderTime="Set Time";
    int numberOfQuestions=20;
    int studyDuration=30;
    Boolean isReminder=false;

    static StudyGoalManager studyGoalManager;
    private StudyGoalManager() {
    }
    public static StudyGoalManager getSGMobject() {
        if (studyGoalManager == null) {
            studyGoalManager = new StudyGoalManager();
        }
        return studyGoalManager;
    }

    public String getExamDate() {
        MainActivity.preferences.getString("examDate","");
        return examDate;
    }

    public void setExamDate(String examDate) {
        MainActivity.preferences.edit().putString("examDate", examDate).apply();
        this.examDate = examDate;
    }

    public String getNotificationReminderTime() {
        MainActivity.preferences.getString("dailyReminder","12:47 PM");
        return notificationReminderTime;
    }

    public void setNotificationReminderTime(String notificationReminderTime) {
        MainActivity.preferences.edit().putString("dailyReminder", "12:47 PM").apply();
        this.notificationReminderTime = notificationReminderTime;
    }

    public int getNumberOfQuestions() {
        MainActivity.preferences.getString("questionsGoal","20");
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        MainActivity.preferences.edit().putString("questionsGoal", examDate).apply();
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getStudyDuration() {
        MainActivity.preferences.getString("timeGoal","30");
        return studyDuration;
    }

    public void setStudyDuration(int studyDuration) {
        MainActivity.preferences.edit().putString("timeGoal", examDate).apply();
        this.studyDuration = studyDuration;
    }

    public Boolean getReminder() {
        MainActivity.preferences.getString("isReminder","false");
        return isReminder;
    }

    public void setReminder(Boolean reminder) {
        MainActivity.preferences.edit().putString("isReminder", "false").apply();
        isReminder = reminder;
    }
}