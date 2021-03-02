package com.example.StudyGoals.Components;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyGoals.R;

public class StudyGoalButtonsViewHolder extends RecyclerView.ViewHolder {
    private Button getStartedBtn;
    private Button reviewProgressBtn;

    public StudyGoalButtonsViewHolder(@NonNull View itemView) {
        super(itemView);
        getStartedBtn = itemView.findViewById(R.id.getStartedButton);
        reviewProgressBtn = itemView.findViewById(R.id.reviewProgressButton);
    }
    public void studyGoalButtonsSetData(String studyGoalGetStartedButtonValue,String studyGoalReviewProgressButtonValue){
        getStartedBtn.setText(studyGoalGetStartedButtonValue);
        reviewProgressBtn.setText(studyGoalReviewProgressButtonValue);
    }
}
