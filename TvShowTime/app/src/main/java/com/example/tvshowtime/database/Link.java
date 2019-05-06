package com.example.tvshowtime.database;

import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("href")
    public String href;

    public Link (String href){
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
