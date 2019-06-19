package com.mirza.tvshowtime.database;

import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("time")
    private String time;

    @SerializedName("days")
    private String [] days;

    public Schedule(String time, String [] days){
        this.time = time;
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public String[] getDays() {
        return days;
    }
}
