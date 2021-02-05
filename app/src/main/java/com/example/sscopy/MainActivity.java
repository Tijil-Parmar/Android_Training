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

import com.example.sscopy.Model.StudyGoal;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button setDuration;
    TextView sliderValue;
    SeekBar seekbar;
    Switch switch1;
    Button setTime;
    Button getStarted;
    Button setDate;
    boolean saveSwitchState;
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Study Goals");
        setContentView(R.layout.activity_main);
        seekbar=findViewById(R.id.slider);
        seekbar.incrementProgressBy(100);
        seekbar.setMax(270);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekbar.setMin(20);
        }
        switch1=findViewById(R.id.enableNotificationSwitch);
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
        setDate = (Button) findViewById(R.id.setDate);
        setTime = (Button) findViewById(R.id.setTime);
        setDuration=findViewById(R.id.setDuration);
        SharedPreferences getShared = getSharedPreferences("info", MODE_PRIVATE);
        String time= getShared.getString("SetTime", "Set Time");
        String date= getShared.getString("SetDate", "Set Date");
        String duration= getShared.getString("SetDuration", "Set Duration");
        String switchState= getShared.getString("SwitchState","false");
        boolean bool = Boolean.parseBoolean(String.valueOf(switchState));
        switch1.setChecked(bool);
        setTime.setText(time);
        setDate.setText(date);
        setDuration.setText(duration);
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time");
            }
        });
//        SharedPreferences info = getSharedPreferences()
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

        SeekBar slider=findViewById(R.id.slider);
        sliderValue=(TextView) findViewById(R.id.textView);
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

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date");
            }
        });
        getStarted=findViewById(R.id.getStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Model Logic:
//                StudyGoal studyGoal = new StudyGoal(setTime.getText().toString(), setDate.getText().toString(), setDuration.getText().toString());
//                String a = studyGoal.getDate();


                // Shared Preferences Logic:
                SharedPreferences info = getSharedPreferences("info", MODE_PRIVATE);
                SharedPreferences.Editor editor = info.edit();
                editor.putString("SetTime", (String) setTime.getText());
                editor.putString("SetDate", (String) setDate.getText());
                editor.putString("SetDuration", (String) setDuration.getText());
                editor.putString("noOfQuestions", (String) sliderValue.getText());
                editor.putString("SwitchState", String.valueOf(saveSwitchState));
                editor.commit();
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
        Button setDate=(Button)findViewById(R.id.setDate);
        setDate.setText(examDateString);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        setTime= findViewById(R.id.setTime);
        setTime.setText(hour+":"+minute);
    }
}