package com.example.mobilecalendarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class calendarWeekActivity extends AppCompatActivity {

    private CalendarView calendarView;

    private RecyclerView recyclerView;
    private static List<Activity> activityList, activityListnew;
    private DatabaseHelper databaseHelper;
    private static ActivityAdapter activityAdapter;
    int i;
    int activityDay, activityMonth, activityYear, calendarDay, size;
    String startDate, tokens[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_month);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);



        activityList = new ArrayList<Activity>();
        activityListnew = new ArrayList<Activity>();

        databaseHelper = new DatabaseHelper(calendarWeekActivity.this);
        activityList = databaseHelper.getAllActivities();
        recyclerView = findViewById(R.id.calendarrecyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                if ( activityListnew.isEmpty() == false ) {
                    activityListnew.clear();

                }

                if(dayOfMonth+6 > 31){
                    calendarDay = 31;
                }else{
                    calendarDay = dayOfMonth + 6;
                }

                activityList = databaseHelper.getAllActivities();
                size = activityList.size();
                month++;
                System.out.println("size"+activityList.size());
                Iterator iter = activityList.iterator();
                i = 0;
                while (iter.hasNext() && i<size) {
                    startDate = activityList.get(i).getStartDate();
                    tokens = startDate.split("/");
                    System.out.println("hgf"+String.valueOf(month+1)+" "+tokens[1]);
                    activityDay = Integer.valueOf(tokens[0]);
                    activityMonth = Integer.valueOf(tokens[1]);
                    activityYear = Integer.valueOf(tokens[2]);
                    if(year == activityYear && month == activityMonth){
                        if(dayOfMonth <= activityDay && activityDay <= calendarDay){
                            activityListnew.add(activityList.get(i));
                        }

                    }
                    i++;
                }

                activityAdapter = new ActivityAdapter(activityListnew);
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
