package com.example.StudyGoals.Components;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyGoals.R;
import com.example.StudyGoals.StudyModel.StudyGoal;

public class QuestionGoalViewHolder extends RecyclerView.ViewHolder{
    private TextView numberOfQuestions;
    private SeekBar numberOfQuestionsSeekbar;
    StudyGoal studyGoal = StudyGoal.getStudyGoal();

    public QuestionGoalViewHolder(@NonNull View itemView) {
        super(itemView);
        numberOfQuestions = itemView.findViewById(R.id.numberOfQuestionsSeekBar);
        numberOfQuestionsSeekbar = itemView.findViewById(R.id.numberOfQuestionsSeekbar);
    }
    public void numberOfQuestionsSetData(int numberOfQuestionsValue){
        numberOfQuestions.setText(String.valueOf(numberOfQuestionsValue));
        numberOfQuestionsSeekbar.setMax(250);
        numberOfQuestionsSeekbar.setProgress(numberOfQuestionsValue);

        numberOfQuestionsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i / 10;
                i = i * 10;
                numberOfQuestions.setText(String.valueOf(i));
                studyGoal.setNumberOfQuestions(Integer.parseInt(numberOfQuestions.getText().toString()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
