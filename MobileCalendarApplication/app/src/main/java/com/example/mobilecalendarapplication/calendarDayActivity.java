package com.example.mobilecalendarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class calendarDayActivity extends AppCompatActivity {

    private CalendarView calendarView;

    private RecyclerView recyclerView;
    private static List<Activity> activityList;
    private DatabaseHelper databaseHelper;
    private static ActivityAdapter activityAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_day);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);



        activityList = new ArrayList<Activity>();

        databaseHelper = new DatabaseHelper(calendarDayActivity.this);

        recyclerView = findViewById(R.id.calendarrecyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                month++;
                String date = String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
                activityList = databaseHelper.getDailyActivities(date);
                activityAdapter = new ActivityAdapter(activityList);
                recyclerView.setAdapter(activityAdapter);
                activityAdapter.notifyDataSetChanged();
            }
        });





    }

    public static void onDelete(int position) {
        activityList.remove(position);
        activityAdapter.notifyDataSetChanged();
    }

    public static void onUpdate(int position, Activity activity) {
        activityList.set(position, activity);
        activityAdapter.notifyDataSetChanged();
    }












}
