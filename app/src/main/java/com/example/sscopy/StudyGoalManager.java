package com.example.sscopy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;

import static android.content.Context.MODE_PRIVATE;
import static android.net.wifi.rtt.CivicLocationKeys.STATE;

public class StudyGoalManager extends MainActivity {
    String ExamDate, Duration, NumberOfQuestions, ReminderTime;
    Boolean switchState;
    //WHAT IS THIS??
    final public static String SET_DURATION = "setduration";
    final public static String SET_TIME = "settime";
    final public static String SET_DATE = "setdate";
    final public static String NO_OF_QUESTIONS = "noofquestions";
    final public static Boolean SWITCH_STATE= Boolean.valueOf("switchstate");

    public StudyGoalManager() {
    }

    public StudyGoalManager(String examDate, String duration, String numberOfQuestions, String reminderTime, Boolean switchState) {
        this.ExamDate = examDate;
        this.Duration = duration;
        this.NumberOfQuestions = numberOfQuestions;
        this.ReminderTime = reminderTime;
        this.switchState = switchState;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void commitData() {
        MainActivity.preferences.edit().putString(SET_DURATION, Duration).commit();
        MainActivity.preferences.edit().putString(SET_TIME, ReminderTime).commit();
        MainActivity.preferences.edit().putString(SET_DATE, ExamDate).commit();
        MainActivity.preferences.edit().putString(NO_OF_QUESTIONS, NumberOfQuestions).commit();
        MainActivity.preferences.edit().putBoolean(String.valueOf(SWITCH_STATE), switchState).commit();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void SMfetchData() {
//        SharedPreferences getShared;
        MainActivity.preferences.getString(SET_DURATION,"Set Duration");
        MainActivity.preferences.getString(SET_TIME,"Set Time");
        MainActivity.preferences.getString(SET_DATE,"Set Date");
        MainActivity.preferences.getString(NO_OF_QUESTIONS ,"30");
        MainActivity.preferences.getBoolean(String.valueOf(SWITCH_STATE),false);
        setDate.setText(SET_DURATION);
        setTime.setText(SET_TIME);
        setDate.setText(SET_DATE);
        sliderValue.setText(NO_OF_QUESTIONS);
        int seekbarvalue= Integer.parseInt(NO_OF_QUESTIONS);
        seekbar.setProgress(seekbarvalue);
        StudyGoal studyGoal=new StudyGoal();
        studyGoal.ExamDate=SET_DATE;
        studyGoal.Duration=SET_DURATION;
        studyGoal.NumberOfQuestions=NO_OF_QUESTIONS;
        studyGoal.ReminderTime=SET_TIME;
        studyGoal.switchState = SWITCH_STATE;
    }
}