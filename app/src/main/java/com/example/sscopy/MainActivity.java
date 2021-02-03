package com.example.sscopy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Study Goals");
//        getSupportActionBar().setTitle("Study Goals");
        setContentView(R.layout.activity_main);
        Button button4 = (Button) findViewById(R.id.setTime);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time");
            }
        });
        Switch switch1= findViewById(R.id.switch1);


        Button button = (Button) findViewById(R.id.setDate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date");
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
        Button button=(Button)findViewById(R.id.setDate);
        button.setText(examDateString);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Button button4 = findViewById(R.id.setTime);
        button4.setText(hour+":"+minute);
    }

    @Override
    public void onClick(View view) {

    }
}