package com.example.StudyGoals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.StudyGoals.StudyGoal.studyGoal;
import static com.example.StudyGoals.StudyGoalNotification.CHANNEL_1_ID;
import static com.example.StudyGoals.StudyGoalNotification.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private NotificationManagerCompat notificationManager;
    TextView displayStudyTime;
    TextView displayUserSelectedQuestions;
    SeekBar numberOfQuestionsSeekbar;
    Switch studyReminderSwitch;
    TextView displayReminderTimeBtn;
    Button getStartedBtn;
    TextView displayExamDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudyGoalManager.init(this);

        numberOfQuestionsSeekbar =findViewById(R.id.numberOfQuestionsSeekbar);
        numberOfQuestionsSeekbar.incrementProgressBy(10);
        numberOfQuestionsSeekbar.setMax(270);
        studyReminderSwitch =findViewById(R.id.enableNotificationSwitch);
        displayExamDate = findViewById(R.id.setDateTV);
        displayReminderTimeBtn = findViewById(R.id.setTimeTV);
        displayStudyTime =findViewById(R.id.setDurationTV);
        displayUserSelectedQuestions =findViewById(R.id.numberOfQuestionsSeekBar);

        StudyGoal studyGoal=StudyGoal.getStudyGoalObject();
        displayExamDate.setText(studyGoal.getExamDate());
        displayStudyTime.setText(String.valueOf((studyGoal.getStudyDuration())));
        displayReminderTimeBtn.setText(studyGoal.getNotificationReminderTime());
        displayUserSelectedQuestions.setText(String.valueOf(studyGoal.getNumberOfQuestions()));
        numberOfQuestionsSeekbar.setProgress((studyGoal.getNumberOfQuestions()));
        studyReminderSwitch.setChecked(studyGoal.enableReminder());
        displayReminderTimeBtn.setEnabled(studyGoal.enableReminder());

        notificationManager = NotificationManagerCompat.from(this);

        studyReminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                studyGoal.setReminder(isChecked);
                Log.d("SWITCH STATE", String.valueOf(isChecked));
            }
        });

        displayReminderTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(studyGoal.isReminder==true) {
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "Time");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enable Switch!",Toast.LENGTH_LONG).show();
                    Log.d("ELSE STATUS", "WORKING");
                }
            }
        });
        //Logic for making the "Set Time" button dependent on the switch "Enable Daily Notifications"
        studyReminderSwitch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                    displayReminderTimeBtn.setEnabled(studyGoal.enableReminder());
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
                                studyGoal.setStudyDuration(Integer.parseInt((displayStudyTime.getText().toString())));
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
                studyGoal.setNumberOfQuestions(Integer.parseInt(displayUserSelectedQuestions.getText().toString()));
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
                Toast.makeText(MainActivity.this, "Reminder Set", Toast.LENGTH_SHORT).show();
//                studyGoal.setReminder(studyReminderSwitch.isChecked());
                String s = (String) displayReminderTimeBtn.getText();
//                int currentHour = Integer.parseInt(currentTime[0]) * 60 * 60 *1000;
//                int currentMinute = Integer.parseInt(reminderTime[1]) *  60 *1000;
//                int timeRemaining = (reminderHour+reminderMinute)-(currentHour+currentMinute);
//                if(timeRemaining>0)
//                {
//                    setReminder(timeRemaining);
//                }
//                else
//                {
//
//                }
                setReminder(5000);
                Log.d("SWITCH STATE", String.valueOf(studyGoal.isReminder));
            }
        });
    }

    public void setReminder(long timeLeft){

        Intent intent =new Intent(MainActivity.this, NotificationBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0, intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        Log.d("system time",String.valueOf(timeAtButtonClick));
        Log.d("Milliseconds Left ::::", String.valueOf(timeLeft));
        timeAtButtonClick = 0;
//        timeLeft=timeAtButtonClick;
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick+timeLeft,pendingIntent);
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
    }
}