package com.example.StudyGoals;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class StudyGoal {
    String examDate, duration, numberOfQuestions, reminderTime;
    Boolean switchState;
    public static final String SET_DURATION = "";
    public static final String SET_TIME = "";
    public static final String SET_DATE = "";
    public static final String NO_OF_QUESTIONS = "";
    public static final Boolean SWITCH_STATE= Boolean.valueOf("switchstate");
    StudyGoal(){

    }
    public StudyGoal(String examDate, String duration, String numberOfQuestions, String reminderTime, Boolean switchState) {
        this.examDate = examDate;
        Log.d("examDateConstructor",examDate);
        this.duration = duration;
        this.numberOfQuestions = numberOfQuestions;
        this.reminderTime = reminderTime;
        this.switchState = switchState;
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void run()
//    {
////        StudyGoalManager studyGoalManager = new StudyGoalManager(examDate, duration, numberOfQuestions, reminderTime, switchState);
////        studyGoalManager.commitData();
//
//    }
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void SGfetchdata() {
//        StudyGoalManager study = new StudyGoalManager();
//        examDate = study.getExamDate();
//        duration = study.getDuration();
//        numberOfQuestions = study.getNumberOfQuestions();
//        reminderTime = study.getReminderTime();
//        switchState = study.getSwitchState();
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getExamDate() {
        MainActivity.preferences.getString(SET_DATE,"Set Date");
        Log.d("examDate in Getter",SET_DATE);
        return SET_DATE;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setExamDate(String examDate) {
        MainActivity.preferences.edit().putString(SET_DATE, examDate).commit();
        Log.d("examDate in Setter",SET_DATE);
        this.examDate = SET_DATE;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getDuration() {
        MainActivity.preferences.getString(SET_DURATION,"Set Duration");
        return SET_DURATION;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setDuration(String duration) {
        MainActivity.preferences.edit().putString(SET_DURATION, duration).commit();
        this.duration = SET_DURATION;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getNumberOfQuestions() {
        MainActivity.preferences.getString( NO_OF_QUESTIONS ,"30");
        return NO_OF_QUESTIONS;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setNumberOfQuestions(String numberOfQuestions) {
        MainActivity.preferences.edit().putString(NO_OF_QUESTIONS, numberOfQuestions).commit();
        this.numberOfQuestions = NO_OF_QUESTIONS;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getReminderTime() {
        MainActivity.preferences.getString(SET_TIME,"Set Time");
        return SET_TIME;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setReminderTime(String reminderTime) {
        MainActivity.preferences.edit().putString(SET_TIME, reminderTime).commit();
        this.reminderTime = SET_TIME;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Boolean getSwitchState() {
        MainActivity.preferences.getBoolean(String.valueOf(SWITCH_STATE),false);
        return SWITCH_STATE;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setSwitchState(Boolean switchState) {
        MainActivity.preferences.edit().putBoolean(String.valueOf(SWITCH_STATE), switchState).commit();
        this.switchState = SWITCH_STATE;
    }
}
