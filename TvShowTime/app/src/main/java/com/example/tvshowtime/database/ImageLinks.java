package com.example.tvshowtime.database;

import com.google.gson.annotations.SerializedName;

public class ImageLinks {

    @SerializedName("medium")
    private String urlSmall;

    @SerializedName("original")
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
