
package com.example.StudyGoals.Components;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyGoals.Pickers.StudyGoalDatePickerFragment;
import com.example.StudyGoals.R;

public class StudyGoalsExamDateViewHolder extends RecyclerView.ViewHolder{
    private TextView examDate;

    public StudyGoalsExamDateViewHolder(@NonNull View itemView) {
        super(itemView);
        examDate=itemView.findViewById(R.id.setDateTV);
    }
    public void setExamDateData(String examDateValue, Context context){
        examDate.setText(examDateValue);
        examDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new StudyGoalDatePickerFragment();
//                datePicker.show(context.getSupportFragmentManager(),"Date");
                try {
                    FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                    datePicker.show(fragmentManager,"Date");
                } catch (ClassCastException e) {
//                    Log.e(TAG, "Can't get fragment manager");
                }

            }
        });
    }
}