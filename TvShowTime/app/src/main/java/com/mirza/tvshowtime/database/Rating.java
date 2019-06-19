package com.mirza.tvshowtime.database;

import androidx.room.ColumnInfo;

public class Rating {
    @ColumnInfo(name = Show.col_ShowRating)
    private double average;

    public Rating(double average) {
        this.average = average;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
