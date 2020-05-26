package com.example.mobilecalendarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button settingsButton;
    private Button listButton;
    private Button addActivityButton;
    private Button calendarDay;
    private Button calendarWeek;
    private Button calendarMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsButton = findViewById(R.id.settings);
        listButton = findViewById(R.id.list);
        addActivityButton = findViewById(R.id.addActivity);
        calendarDay = findViewById(R.id.calendarDay);
        calendarWeek = findViewById(R.id.calendarWeek);
        calendarMonth = findViewById(R.id.calendarMonth);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, settingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(MainActivity.this, listActivity.class);
                startActivity(listIntent);
            }
        });

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addActivityIntent = new Intent(MainActivity.this, addActivity.class);
                startActivity(addActivityIntent);
            }
        });

        calendarDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendarDayIntent = new Intent(MainActivity.this, calendarDayActivity.class);
                startActivity(calendarDayIntent);
            }
        });

        calendarWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendarWeekIntent = new Intent(MainActivity.this, calendarWeekActivity.class);
                startActivity(calendarWeekIntent);
            }
        });

        calendarMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendarMonthIntent = new Intent(MainActivity.this, calendarMonthActivity.class);
                startActivity(calendarMonthIntent);
            }
        });









    }
}
