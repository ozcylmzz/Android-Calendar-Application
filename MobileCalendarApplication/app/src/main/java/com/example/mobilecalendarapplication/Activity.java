package com.example.mobilecalendarapplication;

import android.os.Parcel;

import java.io.Serializable;

//Github'dan çekildiğinde sınıf isminde hata olabiliyor. Çözüm olarak Rename yapılmalı...
public class Activity implements Serializable {

    private int activityID;
    private String name;
    private String description;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String address;
    private String remeinderStartDate1;
    private String remeinderStartTime1;
    private String remeinderStartDate2;
    private String remeinderStartTime2;


    public Activity(int activityID, String name, String description, String startDate, String startTime, String endDate, String endTime, String address, String remeinderStartDate1, String remeinderStartTime1, String remeinderStartDate2, String remeinderStartTime2) {
        this.activityID = activityID;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.address = address;
        this.remeinderStartDate1 = remeinderStartDate1;
        this.remeinderStartTime1 = remeinderStartTime1;
        this.remeinderStartDate2 = remeinderStartDate2;
        this.remeinderStartTime2 = remeinderStartTime2;
    }

    protected Activity(Parcel in) {
        activityID = in.readInt();
        name = in.readString();
        description = in.readString();
        startDate = in.readString();
        startTime = in.readString();
        endDate = in.readString();
        endTime = in.readString();
        address = in.readString();
        remeinderStartDate1 = in.readString();
        remeinderStartTime1 = in.readString();
        remeinderStartDate2 = in.readString();
        remeinderStartTime2 = in.readString();
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemeinderStartDate1() {
        return remeinderStartDate1;
    }

    public void setRemeinderStartDate1(String remeinderStartDate1) {
        this.remeinderStartDate1 = remeinderStartDate1;
    }

    public String getRemeinderStartTime1() {
        return remeinderStartTime1;
    }

    public void setRemeinderStartTime1(String remeinderStartTime1) {
        this.remeinderStartTime1 = remeinderStartTime1;
    }

    public String getRemeinderStartDate2() {
        return remeinderStartDate2;
    }

    public void setRemeinderStartDate2(String remeinderStartDate2) {
        this.remeinderStartDate2 = remeinderStartDate2;
    }

    public String getRemeinderStartTime2() {
        return remeinderStartTime2;
    }

    public void setRemeinderStartTime2(String remeinderStartTime2) {
        this.remeinderStartTime2 = remeinderStartTime2;
    }



    public static String toString(Activity activity) {

        String text = "Aktivite Detayları:" +
                "\nAdı: " + activity.getName() +
                "\nTanımı: " + activity.getDescription() +
                "\nBaşlangıç Zamanı: " + activity.getStartDate() + " " + activity.getStartTime() +
                "\nAdresi: " + activity.getAddress();

        return text;
    }



}
