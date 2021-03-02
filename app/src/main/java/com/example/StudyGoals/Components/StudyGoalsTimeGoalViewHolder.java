package com.example.StudyGoals.Components;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyGoals.R;

public class StudyGoalsTimeGoalViewHolder extends RecyclerView.ViewHolder {
    private TextView timeGoal;

    public StudyGoalsTimeGoalViewHolder(@NonNull View itemView) {
        super(itemView);
        timeGoal = itemView.findViewById(R.id.setDurationTV);
    }
    public void timeGoalSetData(int timeGoalValue){
        timeGoal.setText(String.valueOf(timeGoalValue));
    }
}
