package com.example.sscopy.Model;

import android.content.SharedPreferences;


public class StudyGoal implements iModel
{
    private String Date,Duration,Time;
    public StudyGoal(String date, String duration, String time) {
        Date = date;
        Duration = duration;
        Time = time;
    }



    public void setDate(String date) {
        Date = date;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public void setTime(String time) {
        Time = time;
    }



    @Override
    public String getDate() {
        return Date.toString();
    }

    @Override
    public String getDuration() {
        return Duration;
    }

    @Override
    public String getTime() {
        return Time;
    }
}
