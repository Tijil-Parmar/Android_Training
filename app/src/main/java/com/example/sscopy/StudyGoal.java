package com.example.sscopy;

import android.os.Build;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

public class StudyGoal {
    String ExamDate, Duration, NumberOfQuestions, ReminderTime;
    Boolean switchState;

    StudyGoal(){

    }
    public StudyGoal(String examDate, String duration, String numberOfQuestions, String reminderTime, Boolean switchState) {
        ExamDate = examDate;
        Duration = duration;
        NumberOfQuestions = numberOfQuestions;
        ReminderTime = reminderTime;
        this.switchState = switchState;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void run()
    {
        StudyGoalManager studyGoalManager = new StudyGoalManager(ExamDate, Duration, NumberOfQuestions, ReminderTime, switchState);
        studyGoalManager.commitData();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void fetchdata(){
        StudyGoalManager study= new StudyGoalManager();
        study.SMfetchData();
    }
}
