package com.example.StudyGoals;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static SharedPreferences preferences;
    TextView displayStudyTime;
    TextView displayUserSelectedQuestions;
    SeekBar numberOfQuestionsSeekbar;
    Switch studyReminderSwitch;
    TextView displayReminderTimeBtn;
    Button getStartedBtn;
    TextView displayExamDate;
    boolean saveSwitchState;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberOfQuestionsSeekbar =findViewById(R.id.numberOfQuestionsSeekbar);
        numberOfQuestionsSeekbar.incrementProgressBy(10);
        numberOfQuestionsSeekbar.setMax(270);
        studyReminderSwitch =findViewById(R.id.enableNotificationSwitch);
        displayExamDate = findViewById(R.id.setDateTV);
        displayReminderTimeBtn = findViewById(R.id.setTimeTV);
        displayStudyTime =findViewById(R.id.setDurationTV);
        displayUserSelectedQuestions =findViewById(R.id.numberOfQuestionsSeekBar);
        StudyGoal studyGoal=StudyGoal.getStudyGoalobject();
        displayExamDate.setText(studyGoal.getExamDate());
        displayStudyTime.setText(String.valueOf((studyGoal.getStudyDuration())));
        displayReminderTimeBtn.setText(studyGoal.getNotificationReminderTime());
        displayUserSelectedQuestions.setText(String.valueOf(studyGoal.getNumberOfQuestions()));
        numberOfQuestionsSeekbar.setProgress((studyGoal.getNumberOfQuestions()));
        studyReminderSwitch.setChecked(studyGoal.getReminder());
        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
        displayReminderTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(studyReminderSwitch.isChecked()) {
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "Time");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enable Switch!",Toast.LENGTH_LONG).show();
                }
            }
        });
        //Logic for making the "Set Time" button dependent on the switch "Enable Daily Notifications"
        studyReminderSwitch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(studyReminderSwitch.isChecked())
                {
                    displayReminderTimeBtn.setEnabled(true);
                    saveSwitchState = true;
                }
                else
                {
                    displayReminderTimeBtn.setEnabled(false);
                    saveSwitchState = false;
                }
            }
        });
        final int[] hourDuration = new int[1];
        final int[] minuteDuration = new int[1];
        displayStudyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog durationPicker=new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hourDuration[0] =i;
                                minuteDuration[0] =i1;
                            Calendar c1=Calendar.getInstance();
                            c1.set(0,0,0, hourDuration[0], minuteDuration[0]);
                            displayStudyTime.setText(android.text.format.DateFormat.format("hh:mm",c1));
                                studyGoal.studyDuration = hourDuration[0]*60 + minuteDuration[0];
                                displayStudyTime.setText(String.valueOf(studyGoal.studyDuration));
                            }
                        },24,0,true
                );
                durationPicker.updateTime(hourDuration[0], minuteDuration[0]);
                durationPicker.show();
            }
        });
        //Implementation for the textview beside the seekbar
        numberOfQuestionsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i / 10;
                i = i * 10;
                displayUserSelectedQuestions.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //"Set Date" implementation
        displayExamDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date");
            }
        });
        getStartedBtn =findViewById(R.id.getStartedButton);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                studyGoal.numberOfQuestions= Integer.parseInt(displayUserSelectedQuestions.getText().toString());
                studyGoal.setNumberOfQuestions(Integer.parseInt(displayUserSelectedQuestions.getText().toString()));
//                studyGoal.examDate= (String) displayExamDate.getText();
                studyGoal.setExamDate((String) displayExamDate.getText());
//                studyGoal.isReminder=saveSwitchState;
                studyGoal.setReminder(saveSwitchState);
//                studyGoal.notificationReminderTime= (String) displayReminderTimeBtn.getText();
                studyGoal.setNotificationReminderTime((String) displayReminderTimeBtn.getText());
//                studyGoal.studyDuration= Integer.parseInt((displayStudyTime.getText().toString()));
                studyGoal.setStudyDuration(Integer.parseInt((displayStudyTime.getText().toString())));
                Log.d("Duration set as", String.valueOf(studyGoal.studyDuration));
                studyGoal.toString();
            }
        });
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
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        displayReminderTimeBtn = findViewById(R.id.setTimeTV);
        displayReminderTimeBtn.setText(hour+":"+minute);
    }
}