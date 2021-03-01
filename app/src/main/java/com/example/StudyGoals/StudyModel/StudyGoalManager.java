package com.example.StudyGoals.StudyModel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class StudyGoalManager {

    public static SharedPreferences studyGoalSharedPreferences;
    public static final String STUDY_GOAL_MANAGER_NAME = "Study Goal";
    public static final String EXAM_DATE = "examDate";
    public static final String DAILY_REMINDER_TIME = "dailyReminder";
    public static final String USER_QUESTIONS_GOAL = "questionsGoal";
    public static final String USER_TIME_GOAL = "timeGoal";
    public static final String IS_REMINDER = "isReminder";
    static StudyGoalManager studyGoalManager;
    private StudyGoalManager() {
    }
    public static StudyGoalManager getStudyGoalManagerObject() {
        if (studyGoalManager == null) {
            studyGoalManager = new StudyGoalManager();
        }
        return studyGoalManager;
    }

    public static void init(Context context)
    {
        if(studyGoalSharedPreferences == null)
            studyGoalSharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public String getExamDate() {
        return (studyGoalSharedPreferences.getString(EXAM_DATE, "Set Date"));
    }

    public String getDailyReminderTime() {
        return (studyGoalSharedPreferences.getString(DAILY_REMINDER_TIME, "12:47"));
    }

    public String getUserQuestionsGoal() {
        return (studyGoalSharedPreferences.getString(USER_QUESTIONS_GOAL, "20"));
    }

    public String getUserTimeGoal() {
        return (studyGoalSharedPreferences.getString(USER_TIME_GOAL, "30"));
    }

    public String getIsReminder() {
        return (studyGoalSharedPreferences.getString(IS_REMINDER, String.valueOf(false)));
    }

//    public String fetchSharedPreferencevalue(String tag) {
//        return (studyGoalSharedPreferences.getString(tag, ""));
//    }

    public void saveOrUpdateStudyGoalData(String tag, String value) {
        studyGoalSharedPreferences.edit().putString(tag, String.valueOf(value)).apply();
    }
}