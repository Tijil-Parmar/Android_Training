package com.example.sscopy;

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
    Button setDuration;
    TextView sliderValue;
    SeekBar seekbar;
    Switch switch1;
    Button setTime,getStarted,setDate;
    boolean saveSwitchState;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static SharedPreferences preferences;
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
        setTitle("Study Goals");
        setContentView(R.layout.activity_main);
        seekbar=findViewById(R.id.numberOfQuestionsSeekbar);
        seekbar.incrementProgressBy(10);
        seekbar.setMax(270);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekbar.setMin(20);
        }
        //setting the state of the switch a
        switch1=findViewById(R.id.enableNotificationSwitch);
        setDate = (Button) findViewById(R.id.setDateButton);
        setTime = (Button) findViewById(R.id.setTimeButton);
        setDuration=findViewById(R.id.setDurationButton);
        //Setting up all the attributes in the activity according to the Shared Preference
        StudyGoal studyGoal=new StudyGoal();
        studyGoal.fetchdata();
        //Set Time button on click implementation
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time");
            }
        });
        //Logic for making the "Set Time" button dependent on the switch "Enable Daily Notifications"
        switch1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(switch1.isChecked())
                {
                    setTime.setEnabled(true);
                    saveSwitchState = true;
                }
                else
                {
                    setTime.setEnabled(false);
                    saveSwitchState = false;
                }
            }
        });
        //Below we have the implementation for Setting number of Minutes per Day by the user
        final int[] hourDuration = new int[1];
        final int[] minuteDuration = new int[1];
        setDuration.setOnClickListener(new View.OnClickListener() {
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
                            setDuration.setText(android.text.format.DateFormat.format("hh:mm",c1));
                            }
                        },24,0,true
                );
                durationPicker.updateTime(hourDuration[0], minuteDuration[0]);
                durationPicker.show();
            }
        });
//Implementation for the textview beside the seekbar
        SeekBar slider=findViewById(R.id.numberOfQuestionsSeekbar);
        sliderValue=(TextView) findViewById(R.id.numberOfQuestionsSeekBar);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i / 10;
                i = i * 10;
                sliderValue.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//"Set Date" implementation
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date");
            }
        });
        getStarted=findViewById(R.id.getStartedButton);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studyGoal.run();
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
        setTime= findViewById(R.id.setTimeButton);
        setTime.setText(hour+":"+minute);
    }
}