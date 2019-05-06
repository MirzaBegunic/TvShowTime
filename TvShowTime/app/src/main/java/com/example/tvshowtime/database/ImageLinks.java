package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class ImageLinks {
    public static final String IMAGESMALL = "imageSmall";
    public static final String IMAGELARGE = "imageLarge";

    @SerializedName("medium")
    @ColumnInfo(name = IMAGESMALL)
    private String urlSmall;

    @SerializedName("original")
    @ColumnInfo(name = IMAGELARGE)
    private String urlLarge;

    public ImageLinks(String urlSmall, String urlLarge) {
        this.urlSmall = urlSmall;
        this.urlLarge = urlLarge;
    }

    public String getUrlSmall() {
        return urlSmall;
    }

    public String getUrlLarge() {
        return urlLarge;
    }
}
