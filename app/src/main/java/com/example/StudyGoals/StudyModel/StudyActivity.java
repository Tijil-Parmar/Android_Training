package com.example.StudyGoals.StudyModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.StudyGoals.DailyNotification.StudyGoalNotification;
import com.example.StudyGoals.Pickers.StudyGoalDatePickerFragment;
import com.example.StudyGoals.Pickers.StudyGoalTimePickerFragment;
import com.example.StudyGoals.R;
import com.example.StudyGoals.StudyGoalAdapter.StudyGoalAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.StudyGoals.StudyModel.StudyGoal.studyGoal;

public class StudyActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private NotificationManagerCompat notificationManager;
    TextView displayStudyTime;
    TextView displayUserSelectedQuestions;
    SeekBar numberOfQuestionsSeekbar;
    Switch studyReminderSwitch;
    TextView displayReminderTimeBtn;
    Button getStartedBtn;
    Button reviewProgressBtn;
    TextView displayExamDate;

    private RecyclerView recyclerView;
    private StudyGoalAdapter studyGoalRecyclerViewAdapter;
    private ArrayList<StudyGoal> studyGoalArrayList;
    private ArrayAdapter<String> arrayAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studygoal_recycler_view);
        StudyGoalManager.init(this);

        recyclerView = findViewById(R.id.recyclerViewStudyGoal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String EXAM_DATE="1";
        String NUMBER_OF_QUESTIONS="2";
        String TIME_GOAL="3";
        String REMINDER_SWITCH="4";
        String REMINDER_TIME="5";
        String arr[]={EXAM_DATE,NUMBER_OF_QUESTIONS,TIME_GOAL,REMINDER_SWITCH,REMINDER_TIME};

//        numberOfQuestionsSeekbar =findViewById(R.id.numberOfQuestionsSeekbar);
//        numberOfQuestionsSeekbar.incrementProgressBy(10);
//        numberOfQuestionsSeekbar.setMax(250);
//        studyReminderSwitch =findViewById(R.id.enableNotificationSwitch);
//        displayExamDate = findViewById(R.id.setDateTV);
//        displayReminderTimeBtn = findViewById(R.id.setTimeTV);
//        displayStudyTime =findViewById(R.id.setDurationTV);
//        displayUserSelectedQuestions =findViewById(R.id.numberOfQuestionsSeekBar);
//        getStartedBtn = findViewById(R.id.getStartedButton);

        StudyGoal studyGoal=StudyGoal.getStudyGoalObject();
//        displayExamDate.setText(studyGoal.getExamDate());
//        displayStudyTime.setText(String.valueOf((studyGoal.getStudyDuration())));
//        displayReminderTimeBtn.setText(studyGoal.getNotificationReminderTime());
//        displayUserSelectedQuestions.setText(String.valueOf(studyGoal.getNumberOfQuestions()));
//        numberOfQuestionsSeekbar.setProgress((studyGoal.getNumberOfQuestions()));
//        studyReminderSwitch.setChecked(studyGoal.enableReminder());
//        displayReminderTimeBtn.setEnabled(studyGoal.enableReminder());

        String values[]={studyGoal.getExamDate(), String.valueOf(studyGoal.getNumberOfQuestions()), String.valueOf(studyGoal.getNumberOfQuestions()), String.valueOf(studyGoal.getStudyDuration()), String.valueOf(studyGoal.enableReminder()),"12:19"};
        recyclerView.setAdapter(new StudyGoalAdapter(arr,values,getApplicationContext()));

        notificationManager = NotificationManagerCompat.from(this);

//        studyReminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                StudyGoalNotification studyGoalNotificationObject = new StudyGoalNotification();
//                studyGoal.setReminder(isChecked);
//                Log.d("SWITCH STATE", String.valueOf(isChecked));
//                if(!studyGoal.isReminder)
//                {
//                    Toast.makeText(getApplicationContext(),"Reminder Turned OFF",Toast.LENGTH_LONG).show();
//                    studyGoalNotificationObject.cancelReminder(getApplicationContext());
//                }
//                if(studyGoal.isReminder)
//                {
//                    Toast.makeText(getApplicationContext(),"Reminder Turned ON",Toast.LENGTH_LONG).show();
//                    String s = (String) studyGoal.notificationReminderTime;
//                    String namepass[] = s.split(":");
//                    long reminderHourValue = Long.parseLong(namepass[0]);
//                    long reminderMinuteValue = Long.parseLong(namepass[1]);
//                    studyGoalNotificationObject.setReminder(getApplicationContext(), reminderHourValue, reminderMinuteValue);
//                }
//            }
//        });

//        displayReminderTimeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(studyGoal.isReminder) {
//                    DialogFragment timePicker = new StudyGoalTimePickerFragment();
//                    timePicker.show(getSupportFragmentManager(), "Time");
//                }
//            }
//        });

        //Logic for making the "Set Time" button dependent on the switch "Enable Daily Notifications"
//        studyReminderSwitch.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view) {
//                    displayReminderTimeBtn.setEnabled(studyGoal.enableReminder());
//            }
//        });
//        final int[] hourDuration = new int[1];
//        final int[] minuteDuration = new int[1];
//        displayStudyTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TimePickerDialog durationPicker=new TimePickerDialog(
//                        StudyActivity.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                                hourDuration[0] =i;
//                                minuteDuration[0] =i1;
//                                Calendar c1=Calendar.getInstance();
//                                c1.set(0,0,0, hourDuration[0], minuteDuration[0]);
//                                displayStudyTime.setText(android.text.format.DateFormat.format("hh:mm",c1));
//                                studyGoal.studyDuration = hourDuration[0]*60 + minuteDuration[0];
//                                displayStudyTime.setText(String.valueOf(studyGoal.studyDuration));
//                                studyGoal.setStudyDuration(Integer.parseInt((displayStudyTime.getText().toString())));
//                            }
//                        },24,0,true
//                );
//                durationPicker.updateTime(hourDuration[0], minuteDuration[0]);
//                durationPicker.show();
//            }
//        });
        //Implementation for the textview beside the seekbar
//        numberOfQuestionsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                i = i / 10;
//                i = i * 10;
//                displayUserSelectedQuestions.setText(String.valueOf(i));
//                studyGoal.setNumberOfQuestions(Integer.parseInt(displayUserSelectedQuestions.getText().toString()));
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
        //"Set Date" implementation
//        displayExamDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment datePicker = new StudyGoalDatePickerFragment();
//                datePicker.show(getSupportFragmentManager(),"Date");
//            }
//        });
//        getStartedBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(StudyActivity.this, "Soon :)", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String examDateString = DateFormat.getDateInstance().format(c.getTime());
        TextView setDate=findViewById(R.id.setDateTV);
        setDate.setText(examDateString);
        studyGoal.setExamDate( displayExamDate.getText().toString());
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        displayReminderTimeBtn = findViewById(R.id.setTimeTV);
        if(minute<10){
            displayReminderTimeBtn.setText(hour+":0"+minute);
        }
        else {
            displayReminderTimeBtn.setText(hour + ":" + minute);
        }
        studyGoal.setNotificationReminderTime( displayReminderTimeBtn.getText().toString());

        if(studyGoal.enableReminder())
        {
            Toast.makeText(StudyActivity.this, "Reminder Set", Toast.LENGTH_SHORT).show();
            String s = (String) studyGoal.notificationReminderTime;
            String namepass[] = s.split(":");
            long reminderHourValue = Long.parseLong(namepass[0]);
            long reminderMinuteValue = Long.parseLong(namepass[1]);
            StudyGoalNotification studyGoalNotificationObject = new StudyGoalNotification();
            studyGoalNotificationObject.setReminder(getApplicationContext(), reminderHourValue, reminderMinuteValue);
        }
        else
        {
            Toast.makeText(StudyActivity.this, "Enable switch to set!", Toast.LENGTH_SHORT).show();
        }

    }
}