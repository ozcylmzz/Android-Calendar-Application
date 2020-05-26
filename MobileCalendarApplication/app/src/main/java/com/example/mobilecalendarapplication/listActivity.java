package com.example.mobilecalendarapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class listActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static List<Activity> activityList;
    private DatabaseHelper databaseHelper;
    private static ActivityAdapter activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);





        activityList = new ArrayList<com.example.mobilecalendarapplication.Activity>();

        databaseHelper = new DatabaseHelper(listActivity.this);
        activityList = databaseHelper.getAllActivities();


        // update, add, delete işlemleri sonrası liste güncellenmeli


        recyclerView = findViewById(R.id.recylerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


//        ActivityAdapter activityAdapter = new ActivityAdapter(activityList);
        activityAdapter = new ActivityAdapter(activityList);
        recyclerView.setAdapter(activityAdapter);
        activityAdapter.notifyDataSetChanged();

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
