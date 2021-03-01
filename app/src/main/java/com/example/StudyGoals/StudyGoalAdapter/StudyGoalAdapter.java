package com.example.StudyGoals.StudyGoalAdapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.StudyGoals.DailyNotification.StudyGoalNotification;
import com.example.StudyGoals.Pickers.StudyGoalDatePickerFragment;
import com.example.StudyGoals.Pickers.StudyGoalTimePickerFragment;
import com.example.StudyGoals.R;
import com.example.StudyGoals.StudyModel.StudyGoal;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import static android.app.PendingIntent.getActivity;

//import static com.example.StudyGoals.StudyModel.StudyGoal.studyGoal;

public class StudyGoalAdapter extends RecyclerView.Adapter<StudyGoalAdapter.ViewHolder> implements  DatePickerDialog.OnDateSetListener{

    public static Context context;
    private List<StudyGoal> studyGoalList;
    private String arr[];
    private String values[];

    public StudyGoalAdapter(String arr[], String values[], Context context) {
        this.arr = arr;
        this.values = values;
        this.context = context;
    }


    @NonNull
    @Override
    public StudyGoalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.examdate_studygoal,parent,false);
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.examdate_studygoal,parent,false);
        View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.examdate_studygoal,parent,false);
        View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.examdate_studygoal,parent,false);
        View view5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.examdate_studygoal,parent,false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull StudyGoalAdapter.ViewHolder holder, int position) {


//        StudyGoal studyGoal = StudyGoal.getStudyGoal();
//        holder.examDate.setText(values[0]);
//        holder.numberOfQuestions.setText(values[1]);
//        holder.numberOfQuestionsSeekbar.setProgress(Integer.parseInt(values[2]));
//        holder.timeGoal.setText(values[3]);
//        holder.reminder.setChecked(Boolean.parseBoolean(values[4]));
//        holder.reminderTime.setText(values[5]);
//        holder.numberOfQuestionsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                i = i / 10;
//                i = i * 10;
//                holder.numberOfQuestions.setText(String.valueOf(i));
//                studyGoal.setNumberOfQuestions(Integer.parseInt(holder.numberOfQuestions.getText().toString()));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });
//        holder.reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                StudyGoalNotification studyGoalNotificationObject = new StudyGoalNotification();
//                studyGoal.setReminder(isChecked);
//                Log.d("SWITCH STATE", String.valueOf(isChecked));
//                if(!studyGoal.isReminder)
//                {
//                    Toast.makeText(context,"Reminder Turned OFF",Toast.LENGTH_LONG).show();
//                    studyGoalNotificationObject.cancelReminder(context);
//                }
//                if(studyGoal.isReminder)
//                {
//                    Toast.makeText(context,"Reminder Turned ON",Toast.LENGTH_LONG).show();
//                    String s = (String) studyGoal.notificationReminderTime;
//                    String namepass[] = s.split(":");
//                    long reminderHourValue = Long.parseLong(namepass[0]);
//                    long reminderMinuteValue = Long.parseLong(namepass[1]);
//                    studyGoalNotificationObject.setReminder(context, reminderHourValue, reminderMinuteValue);
//                }
//            }
//        });
//        holder.reminderTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(studyGoal.isReminder) {
//                    DialogFragment timePicker = new StudyGoalTimePickerFragment();
//                    AppCompatActivity appCompatActivity=new AppCompatActivity();
//                    timePicker.show(appCompatActivity.getSupportFragmentManager(), "Time");
//                }
//            }
//        });
//        holder.reminder.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view) {
//                holder.reminderTime.setEnabled(studyGoal.enableReminder());
//            }
//        });
//        final int[] hourDuration = new int[1];
//        final int[] minuteDuration = new int[1];
//        holder.timeGoal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TimePickerDialog durationPicker=new TimePickerDialog(
//                        context,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                                hourDuration[0] =i;
//                                minuteDuration[0] =i1;
//                                Calendar c1=Calendar.getInstance();
//                                c1.set(0,0,0, hourDuration[0], minuteDuration[0]);
//                                holder.timeGoal.setText(android.text.format.DateFormat.format("hh:mm",c1));
//                                studyGoal.studyDuration = hourDuration[0]*60 + minuteDuration[0];
//                                holder.timeGoal.setText(String.valueOf(studyGoal.studyDuration));
//                                studyGoal.setStudyDuration(Integer.parseInt((holder.timeGoal.getText().toString())));
//                            }
//                        },24,0,true
//                );
//                durationPicker.updateTime(hourDuration[0], minuteDuration[0]);
//                durationPicker.show();
//            }
//        });
//        holder.examDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment datePicker = new StudyGoalDatePickerFragment();
//                StudyGoalDatePickerFragment studyGoalDatePickerFragment = new StudyGoalDatePickerFragment();
////                datePicker.show(getActivity(),"Date");
//            }
//        });
//        holder.getStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Soon :)", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String examDateString = DateFormat.getDateInstance().format(c.getTime());
        StudyGoalAdapter.ViewHolder holder = null;
        holder.examDate.setText(examDateString);
        StudyGoal studyGoal = StudyGoal.getStudyGoal();
        studyGoal.setExamDate( holder.examDate.getText().toString());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView examDate;
        public TextView timeGoal;
        public TextView reminderTime;
        public TextView numberOfQuestions;
        public SeekBar numberOfQuestionsSeekbar;
        Switch reminder;
        Button getStarted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            examDate = itemView.findViewById(R.id.setDateTV);
//            numberOfQuestions = itemView.findViewById(R.id.numberOfQuestions);
            numberOfQuestionsSeekbar = itemView.findViewById(R.id.numberOfQuestionsSeekbar);
            numberOfQuestionsSeekbar.incrementProgressBy(10);
            numberOfQuestionsSeekbar.setMax(250);
            timeGoal = itemView.findViewById(R.id.setDurationTV);
            reminder = itemView.findViewById(R.id.enableNotificationSwitch);;
            reminderTime = itemView.findViewById(R.id.setTimeTV);
            getStarted = itemView.findViewById(R.id.getStartedButton);
        }

        @Override
        public void onClick(View view) {

        }
    }
}