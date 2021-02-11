package com.example.StudyGoals;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class StudyGoalManager {
    public static String SET_DURATION = "setduration";
    public static String SET_TIME = "settime";
    public static String SET_DATE = "setdate";
    public static String NO_OF_QUESTIONS = "noofquestions";
    public static Boolean SWITCH_STATE= Boolean.valueOf("switchstate");
    public StudyGoalManager() {
    }

    public StudyGoalManager(String SET_DATE, String SET_DURATION, String NO_OF_QUESTIONS, String SET_TIME, Boolean SWITCH_STATE) {
        this.SET_DATE = SET_DATE;
        this.SET_DURATION = SET_DURATION;
        this.NO_OF_QUESTIONS = NO_OF_QUESTIONS;
        this.SET_TIME = SET_TIME;
        this.SWITCH_STATE = SWITCH_STATE;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void commitData() {
        MainActivity.preferences.edit().putString(SET_DURATION, SET_DURATION).commit();
        MainActivity.preferences.edit().putString(SET_TIME, SET_TIME).commit();
        MainActivity.preferences.edit().putString(SET_DATE, SET_DATE).commit();
        MainActivity.preferences.edit().putString(NO_OF_QUESTIONS, NO_OF_QUESTIONS).commit();
        MainActivity.preferences.edit().putBoolean(String.valueOf(SWITCH_STATE), SWITCH_STATE).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getExamDate() {
        MainActivity.preferences.getString(SET_DATE,"Set Date");
        return SET_DATE;
    }

    public void setExamDate(String examDate) {
        SET_DATE=examDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getDuration() {
        MainActivity.preferences.getString(SET_DURATION,"Set Duration");
        return SET_DURATION;
    }

    public void setDuration(String duration) {
        SET_DURATION = duration;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getNumberOfQuestions() {
        MainActivity.preferences.getString(NO_OF_QUESTIONS ,"30");
        return NO_OF_QUESTIONS;
    }

    public void setNumberOfQuestions(String numberOfQuestions) {
        NO_OF_QUESTIONS = numberOfQuestions;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getReminderTime() {
        MainActivity.preferences.getString(SET_TIME,"Set Time");
        return SET_TIME;
    }

    public void setReminderTime(String reminderTime) {
        SET_TIME = reminderTime;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Boolean getSwitchState() {
        MainActivity.preferences.getBoolean(String.valueOf(SWITCH_STATE),false);
        return SWITCH_STATE;
    }

    public void setSwitchState(Boolean switchState) {
        this.SWITCH_STATE = switchState;
    }
}