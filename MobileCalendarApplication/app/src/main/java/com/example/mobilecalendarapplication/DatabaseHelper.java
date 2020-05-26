package com.example.mobilecalendarapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "ActivityManager.db";

    private static final String TABLE_ACTIVITY = "ACTIVITY";

    private static final String COLUMN_ACTIVITY_ID = "ACTIVITY_ID";
    private static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    private static final String COLUMN_ACTIVITY_DESCRIPTION = "ACTIVITY_DESCRIPTION";
    private static final String COLUMN_ACTIVITY_START_DATE = "ACTIVITY_START_DATE";
    private static final String COLUMN_ACTIVITY_START_TIME = "ACTIVITY_START_TIME";
    private static final String COLUMN_ACTIVITY_END_DATE = "ACTIVITY_END_DATE";
    private static final String COLUMN_ACTIVITY_END_TIME = "ACTIVITY_END_TIME";
    private static final String COLUMN_ACTIVITY_ADDRESS = "ACTIVITY_ADDRESS";
    private static final String COLUMN_ACTIVITY_RDATES1 = "ACTIVITY_RDATES1";
    private static final String COLUMN_ACTIVITY_RHOURS1 = "ACTIVITY_RHOURS1";
    private static final String COLUMN_ACTIVITY_RDATES2 = "ACTIVITY_RDATES2";
    private static final String COLUMN_ACTIVITY_RHOURS2 = "ACTIVITY_RHOURS2";

    private static final String CREATE_ACTIVITY_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ACTIVITY + " (" +
                    COLUMN_ACTIVITY_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_ACTIVITY_NAME + " TEXT," +
                    COLUMN_ACTIVITY_DESCRIPTION + " TEXT," +
                    COLUMN_ACTIVITY_START_DATE + " TEXT," +
                    COLUMN_ACTIVITY_START_TIME  + " TEXT," +
                    COLUMN_ACTIVITY_END_DATE + " TEXT," +
                    COLUMN_ACTIVITY_END_TIME + " TEXT," +
                    COLUMN_ACTIVITY_ADDRESS + " TEXT," +
                    COLUMN_ACTIVITY_RDATES1 + " TEXT," +
                    COLUMN_ACTIVITY_RHOURS1 + " TEXT," +
                    COLUMN_ACTIVITY_RDATES2 + " TEXT," +
                    COLUMN_ACTIVITY_RHOURS2 + " TEXT" +
                    ")";

    private static final String DROP_ACTIVITY_TABLE = "DROP TABLE IF EXISTS " + TABLE_ACTIVITY;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        onCreate(this.getWritableDatabase());
    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_ACTIVITY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_ACTIVITY_TABLE);
        onCreate(db);

    }

    public void deleteUser(String name, String descp) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("yozdz"+descp);
        db.delete(TABLE_ACTIVITY,
                COLUMN_ACTIVITY_NAME + " = ? AND " + COLUMN_ACTIVITY_DESCRIPTION + " = ?",
                new String[] {name, descp});
        db.close();
    }

    public int addActivity (Activity activity) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ACTIVITY_NAME, activity.getName());
        contentValues.put(COLUMN_ACTIVITY_DESCRIPTION, activity.getDescription());
        contentValues.put(COLUMN_ACTIVITY_START_DATE, activity.getStartDate());
        contentValues.put(COLUMN_ACTIVITY_START_TIME, activity.getStartTime());
        contentValues.put(COLUMN_ACTIVITY_END_DATE, activity.getEndDate());
        contentValues.put(COLUMN_ACTIVITY_END_TIME, activity.getEndTime());
        contentValues.put(COLUMN_ACTIVITY_ADDRESS, activity.getAddress());
        contentValues.put(COLUMN_ACTIVITY_RDATES1, activity.getRemeinderStartDate1());
        contentValues.put(COLUMN_ACTIVITY_RHOURS1, activity.getRemeinderStartTime1());
        contentValues.put(COLUMN_ACTIVITY_RDATES2, activity.getRemeinderStartDate2());
        contentValues.put(COLUMN_ACTIVITY_RHOURS2, activity.getRemeinderStartTime2());

        sqLiteDatabase.insert(TABLE_ACTIVITY, null, contentValues);

        sqLiteDatabase.close();


        String selectQuery =
                COLUMN_ACTIVITY_NAME + " = '" + activity.getName() + "'" + " AND " +
                COLUMN_ACTIVITY_DESCRIPTION + " = '" + activity.getDescription() + "'";

        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = null;
        int data = -1;

        String[] columns = {
                COLUMN_ACTIVITY_ID
        };

        cursor = db.query(
                TABLE_ACTIVITY,
                columns,
                selectQuery,
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            do {
                data = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_ID)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        db.close();

        return data;

    }

    public void updateActivity(Activity activity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACTIVITY_NAME, activity.getName());
        contentValues.put(COLUMN_ACTIVITY_DESCRIPTION, activity.getDescription());
        contentValues.put(COLUMN_ACTIVITY_START_DATE, activity.getStartDate());
        contentValues.put(COLUMN_ACTIVITY_START_TIME, activity.getStartTime());
        contentValues.put(COLUMN_ACTIVITY_END_DATE, activity.getEndDate());
        contentValues.put(COLUMN_ACTIVITY_END_TIME, activity.getEndTime());
        contentValues.put(COLUMN_ACTIVITY_ADDRESS, activity.getAddress());
        contentValues.put(COLUMN_ACTIVITY_RDATES1, activity.getRemeinderStartDate1());
        contentValues.put(COLUMN_ACTIVITY_RHOURS1, activity.getRemeinderStartTime1());
        contentValues.put(COLUMN_ACTIVITY_RDATES2, activity.getRemeinderStartDate2());
        contentValues.put(COLUMN_ACTIVITY_RHOURS2, activity.getRemeinderStartTime2());

        db.update(TABLE_ACTIVITY,
                contentValues,
                COLUMN_ACTIVITY_ID + " = " + activity.getActivityID(),
                null
//                new String[]{String.valueOf(activity.getActivityID())}
                );
        db.close();
    }

    public List<Activity> getAllActivities() {

        List<Activity> activityList = new ArrayList<Activity>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;

        String[] columns = {
                COLUMN_ACTIVITY_ID,
                COLUMN_ACTIVITY_NAME,
                COLUMN_ACTIVITY_DESCRIPTION,
                COLUMN_ACTIVITY_START_DATE,
                COLUMN_ACTIVITY_START_TIME,
                COLUMN_ACTIVITY_END_DATE ,
                COLUMN_ACTIVITY_END_TIME ,
                COLUMN_ACTIVITY_ADDRESS ,
                COLUMN_ACTIVITY_RDATES1 ,
                COLUMN_ACTIVITY_RHOURS1 ,
                COLUMN_ACTIVITY_RDATES2 ,
                COLUMN_ACTIVITY_RHOURS2
        };

        String sortOrder = COLUMN_ACTIVITY_START_DATE + " ASC";

        cursor = sqLiteDatabase.query( TABLE_ACTIVITY,
                columns,
                null,
                null,
                null,
                null,
                sortOrder
        );


        if (cursor.moveToFirst()) {
            do {
                Activity activity = new Activity(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_ID))),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_START_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_START_TIME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_END_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_END_TIME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RDATES1)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RHOURS1)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RDATES2)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RHOURS2))
                );

                activityList.add(activity);

            }
            while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        cursor.close();

        return activityList;
    }

    public List<Activity> getDailyActivities(String date) {

        List<Activity> activityList = new ArrayList<Activity>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;

        String[] columns = {
                COLUMN_ACTIVITY_ID,
                COLUMN_ACTIVITY_NAME,
                COLUMN_ACTIVITY_DESCRIPTION,
                COLUMN_ACTIVITY_START_DATE,
                COLUMN_ACTIVITY_START_TIME,
                COLUMN_ACTIVITY_END_DATE ,
                COLUMN_ACTIVITY_END_TIME ,
                COLUMN_ACTIVITY_ADDRESS ,
                COLUMN_ACTIVITY_RDATES1 ,
                COLUMN_ACTIVITY_RHOURS1 ,
                COLUMN_ACTIVITY_RDATES2 ,
                COLUMN_ACTIVITY_RHOURS2
        };

        String selection = COLUMN_ACTIVITY_START_DATE + " = ?";

        String[] selectionArgs = {date};
        cursor = sqLiteDatabase.query( TABLE_ACTIVITY,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        if (cursor.moveToFirst()) {
            do {
                Activity activity = new Activity(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_ID))),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_START_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_START_TIME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_END_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_END_TIME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RDATES1)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RHOURS1)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RDATES2)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_RHOURS2))
                );

                activityList.add(activity);

            }
            while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        cursor.close();

        return activityList;
    }



}
