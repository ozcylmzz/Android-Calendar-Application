package com.example.mobilecalendarapplication;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Calendar;


public class addActivity extends AppCompatActivity {

    private TextView dateStart;
    private TextView timeStart;
    private TextView dateEnd;
    private TextView timeEnd;
    private EditText name;
    private EditText descp;
    private EditText adress;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private Button addActivity;
    private TextView remeinderDateStart1;
    private TextView remeinderTimeStart1;
    private TextView remeinderDateStart2;
    private TextView remeinderTimeStart2;
    private DatabaseHelper databaseHelper;

    int startHour, startMinutes;
    int startDay, startMonth, startYear;
    int endHour, endMinutes;
    int endDay, endMonth, endYear;
    String activityName, activityDescp, activityAdress;
    String repeatTime;
    int RstartHour1, RstartMinutes1, RstartHour2, RstartMinutes2;
    int RstartDay1, RstartMonth1, RstartYear1, RstartDay2, RstartMonth2, RstartYear2;

    public static Context addActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        addActivityContext = this.getApplicationContext();



        dateStart = findViewById(R.id.dateStart);
        timeStart = findViewById(R.id.timeStart);
        dateEnd = findViewById(R.id.dateEnd);
        timeEnd = findViewById(R.id.timeEnd);
        name = findViewById(R.id.name);
        descp = findViewById(R.id.descp);
        adress = findViewById(R.id.adress);
        addActivity = findViewById(R.id.addbutton);
        remeinderDateStart1 = findViewById(R.id.remeinderDateStart1);
        remeinderTimeStart1 = findViewById(R.id.remeinderTimeStart1);
        remeinderDateStart2 = findViewById(R.id.remeinderDateStart2);
        remeinderTimeStart2 = findViewById(R.id.remeinderTimeStart2);

        String[] mode = { " " ,"gün", "hafta", "ay", "yıl"};
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mode);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        timeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                startHour = cldr.get(Calendar.HOUR_OF_DAY);
                startMinutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(addActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                                timeStart.setText(sHour + ":" + sMinute);
                                startHour = sHour;
                                startMinutes = sMinute;

                            }
                        }, startHour, startMinutes, true);
                timePickerDialog.show();
            }
        });


        dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                startDay = cldr.get(Calendar.DAY_OF_MONTH);
                startMonth = cldr.get(Calendar.MONTH);
                startYear = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(addActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                dateStart.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                startDay = dayOfMonth;
                                startMonth = (monthOfYear + 1);
                                startYear = year;

                            }
                        }, startYear, startMonth, startDay);
                datePickerDialog.show();

            }
        });

        timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                endHour = cldr.get(Calendar.HOUR_OF_DAY);
                endMinutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(addActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                                timeEnd.setText(sHour + ":" + sMinute);
                                endHour = sHour;
                                endMinutes = sMinute;

                            }
                        }, endHour, endMinutes, true);
                timePickerDialog.show();
            }
        });

        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                endDay = cldr.get(Calendar.DAY_OF_MONTH);
                endMonth = cldr.get(Calendar.MONTH);
                endYear = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(addActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                dateEnd.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                endDay = dayOfMonth;
                                endMonth = (monthOfYear + 1);
                                endYear = year;

                            }
                        }, endYear, endMonth, endDay);
                datePickerDialog.show();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                System.out.println("kalem"+selectedItem);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        remeinderTimeStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                RstartHour1 = cldr.get(Calendar.HOUR_OF_DAY);
                RstartMinutes1 = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(addActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                                remeinderTimeStart1.setText(sHour + ":" + sMinute);
                                RstartHour1 = sHour;
                                RstartMinutes1 = sMinute;

                            }
                        }, RstartHour1, RstartMinutes1, true);
                timePickerDialog.show();
            }
        });


        remeinderDateStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                RstartDay1 = cldr.get(Calendar.DAY_OF_MONTH);
                RstartMonth1 = cldr.get(Calendar.MONTH);
                RstartYear1 = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(addActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                remeinderDateStart1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                RstartDay1 = dayOfMonth;
                                RstartMonth1 = (monthOfYear + 1);
                                RstartYear1 = year;

                            }
                        }, RstartYear1, RstartMonth1, RstartDay1);
                datePickerDialog.show();

            }
        });

        remeinderTimeStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                RstartHour2 = cldr.get(Calendar.HOUR_OF_DAY);
                RstartMinutes2 = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(addActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                                remeinderTimeStart2.setText(sHour + ":" + sMinute);
                                RstartHour2 = sHour;
                                RstartMinutes2 = sMinute;

                            }
                        }, RstartHour2, RstartMinutes2, true);
                timePickerDialog.show();
            }
        });

        remeinderDateStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                RstartDay2 = cldr.get(Calendar.DAY_OF_MONTH);
                RstartMonth2 = cldr.get(Calendar.MONTH);
                RstartYear2 = cldr.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(addActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                remeinderDateStart2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                RstartDay2 = dayOfMonth;
                                RstartMonth2 = (monthOfYear + 1);
                                RstartYear2 = year;

                            }
                        }, RstartYear2, RstartMonth2, RstartDay2);
                datePickerDialog.show();

            }
        });

        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                activityName = name.getText().toString();
                activityDescp = descp.getText().toString();
                activityAdress = adress.getText().toString();

                String dateS = startDay + "/" + startMonth + "/" + startYear;
                String dateE = endDay + "/" + endMonth + "/" + endYear;

                String RdateS1 = RstartDay1 + "/" + RstartMonth1 + "/" + RstartYear1;
                String RdateS2 = RstartDay2 + "/" + RstartMonth2 + "/" + RstartYear2;

                String startTime = startHour + ":" + startMinutes;
                String endTime = endHour + ":" + endMinutes;

                String RstartTime1 = RstartHour1 + ":" + RstartMinutes1;
                String RstartTime2 = RstartHour2 + ":" + RstartMinutes2;


                Log.d("activity", activityName);
                Log.d("activity", activityDescp);
                Log.d("activity", dateS);
                Log.d("activity", startTime);
                Log.d("activity", dateE);
                Log.d("activity", endTime);
                Log.d("activity", activityAdress);

                Calendar calendar = Calendar.getInstance();
                Log.d("Calendar", "Calendar => " + calendar.toString());
                Log.d("Calendar", "Calendar => " + calendar.get(Calendar.YEAR));
                Log.d("Calendar", "Calendar => " + calendar.get(Calendar.MONTH));
                Log.d("Calendar", "Calendar => " + calendar.get(Calendar.DAY_OF_MONTH));
                Log.d("Calendar", "Calendar => " + calendar.get(Calendar.HOUR_OF_DAY));
                Log.d("Calendar", "Calendar => " + calendar.get(Calendar.MINUTE));
                Log.d("Calendar", "Calendar => " + calendar.get(Calendar.SECOND));
                Log.d("Calendar", "Calendar => " + calendar.getTimeInMillis());
                Log.d("Calendar", "Calendar => " + calendar.getTime());

                Activity activity = new Activity(-1, activityName, activityDescp, dateS,startTime, dateE, endTime, activityAdress, RdateS1, RstartTime1, RdateS2, RstartTime2 );

                int activityID;
                databaseHelper = new DatabaseHelper(addActivity.this);
                activityID = databaseHelper.addActivity(activity);
                activity.setActivityID(activityID);


                Calendar calendar0 = Calendar.getInstance();
                calendar0.set(Calendar.YEAR, RstartYear1);
                calendar0.set(Calendar.MONTH, (RstartMonth1 - 1)); // şu anki ay değerinden -1 olmalı / mantığı 4 ay 18 gün geçmiş olması gibi anlatılabilir
                calendar0.set(Calendar.DAY_OF_MONTH, RstartDay1);
                calendar0.set(Calendar.HOUR_OF_DAY, RstartHour1);
                calendar0.set(Calendar.MINUTE, RstartMinutes1);
                calendar0.set(Calendar.SECOND, 0);
                startAlarm(calendar0, activity, activityID, 0);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, RstartYear2);
                calendar1.set(Calendar.MONTH, (RstartMonth2 - 1)); // şu anki ay değerinden -1 olmalı / mantığı 4 ay 18 gün geçmiş olması gibi anlatılabilir
                calendar1.set(Calendar.DAY_OF_MONTH, RstartDay2);
                calendar1.set(Calendar.HOUR_OF_DAY, RstartHour2);
                calendar1.set(Calendar.MINUTE, RstartMinutes2);
                calendar1.set(Calendar.SECOND, 0);
                startAlarm(calendar1, activity, activityID, 1);

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Takvime Güncelleme Yapıldı.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                finish();

            }
        });










    }




    private void startAlarm(Calendar c, Activity activity, int activityID, int reminder) {


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("activity", (Serializable) activity);
        bundle.putInt("reminder", reminder); // ilk hatırlatıcı için 0, diğeri 1
        //put the Bundle inside the intent
        intent.putExtra("bundle",bundle);



        int id = activityID * 10 + reminder;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, 0);
        Log.d("pendingIntent 0", pendingIntent.toString());


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);



    }


    public static void startAlarm2(Calendar c, Activity activity, int activityID, int reminder) {

        AlarmManager alarmManager = (AlarmManager) addActivityContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(addActivityContext, AlertReceiver.class);


        Bundle bundle = new Bundle();
        bundle.putSerializable("activity", (Serializable) activity);
        bundle.putInt("reminder", reminder); // ilk hatırlatıcı için 0, diğeri 1
        //put the Bundle inside the intent
        intent.putExtra("bundle",bundle);


        int id = activityID * 10 + reminder;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(addActivityContext, id, intent, 0);
        Log.d("pendingIntent 0", pendingIntent.toString());


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);



    }


}
