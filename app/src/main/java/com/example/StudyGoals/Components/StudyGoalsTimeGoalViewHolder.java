package com.example.StudyGoals.Components;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyGoals.R;
import com.example.StudyGoals.StudyModel.StudyActivity;
import com.example.StudyGoals.StudyModel.StudyGoal;

import java.util.Calendar;

public class StudyGoalsTimeGoalViewHolder extends RecyclerView.ViewHolder {
    private TextView timeGoal;

    StudyGoal studyGoal=StudyGoal.getStudyGoal();

    public StudyGoalsTimeGoalViewHolder(@NonNull View itemView) {
        super(itemView);
        timeGoal = itemView.findViewById(R.id.setDurationTV);
    }
    public void timeGoalSetData(int timeGoalValue, Context context){
        timeGoal.setText(String.valueOf(timeGoalValue));
        final int[] hourDuration = new int[1];
        final int[] minuteDuration = new int[1];
        timeGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog durationPicker=new TimePickerDialog(
                        context,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hourDuration[0] =i;
                                minuteDuration[0] =i1;
                                Calendar c1= Calendar.getInstance();
                                c1.set(0,0,0, hourDuration[0], minuteDuration[0]);
                                timeGoal.setText(android.text.format.DateFormat.format("hh:mm",c1));
                                studyGoal.studyDuration = hourDuration[0]*60 + minuteDuration[0];
                                timeGoal.setText(String.valueOf(studyGoal.studyDuration));
                                studyGoal.setStudyDuration(Integer.parseInt((timeGoal.getText().toString())));
                            }

                        },24,0,true
                );
                durationPicker.updateTime(hourDuration[0], minuteDuration[0]);
                durationPicker.show();
            }
        });
    }
}
