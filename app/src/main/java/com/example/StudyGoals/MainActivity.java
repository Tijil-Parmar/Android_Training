package com.example.StudyGoals;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button displayStudyTime;
    TextView displayUserSelectedQuestions;
    SeekBar numberOfQuestionsSeekbar;
    Switch studyReminderSwitch;
    Button displayReminderTimeBtn;//Convert buttons to textView
    Button getStartedBtn;
    Button displayExamDate;
    boolean saveSwitchState;

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public static SharedPreferences preferences;
//    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
        setTitle("Study Goals");
        setContentView(R.layout.activity_main);
        numberOfQuestionsSeekbar =findViewById(R.id.numberOfQuestionsSeekbar);
        numberOfQuestionsSeekbar.incrementProgressBy(10);
        numberOfQuestionsSeekbar.setMax(270);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            numberOfQuestionsSeekbar.setMin(20);
        }
        //setting the state of the switch a
        studyReminderSwitch =findViewById(R.id.enableNotificationSwitch);
        displayExamDate = (Button) findViewById(R.id.setDateButton);
        displayReminderTimeBtn = (Button) findViewById(R.id.setTimeButton);
        displayStudyTime =findViewById(R.id.setDurationButton);
        displayUserSelectedQuestions =findViewById(R.id.numberOfQuestionsSeekBar);

        StudyGoal studyGoal=new StudyGoal(displayExamDate.getText().toString(), displayStudyTime.getText().toString(), displayUserSelectedQuestions.getText().toString(), displayReminderTimeBtn.getText().toString(), studyReminderSwitch.isChecked());

        displayExamDate.setText(studyGoal.getExamDate());
        displayReminderTimeBtn.setText(studyGoal.getReminderTime());
//        sliderValue.setText(studyGoal.getNumberOfQuestions());
        displayStudyTime.setText(studyGoal.getDuration());
        studyReminderSwitch.setChecked(studyGoal.getSwitchState());
        displayExamDate.setText(studyGoal.getExamDate());
//        studyGoal.SGfetchdata();
        //Set Time button on click implementation
        displayReminderTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time");
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
        //Below we have the implementation for Setting number of Minutes per Day by the user
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
                            }
                        },24,0,true
                );
                durationPicker.updateTime(hourDuration[0], minuteDuration[0]);
                durationPicker.show();
            }
        });
        //Implementation for the textview beside the seekbar
        SeekBar slider=findViewById(R.id.numberOfQuestionsSeekbar);
        displayUserSelectedQuestions =(TextView) findViewById(R.id.numberOfQuestionsSeekBar);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                studyGoal.setDuration(displayStudyTime.getText().toString());
                studyGoal.setExamDate(displayExamDate.getText().toString());
                studyGoal.setNumberOfQuestions(displayUserSelectedQuestions.getText().toString());
                studyGoal.setReminderTime(displayReminderTimeBtn.getText().toString());
                studyGoal.setSwitchState(saveSwitchState);
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
        Button setDate=(Button)findViewById(R.id.setDateButton);
        setDate.setText(examDateString);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        displayReminderTimeBtn = findViewById(R.id.setTimeButton);
        displayReminderTimeBtn.setText(hour+":"+minute);
    }
}