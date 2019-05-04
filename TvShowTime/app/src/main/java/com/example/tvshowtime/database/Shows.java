package com.example.tvshowtime.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shows_table")
public class Shows {
    @PrimaryKey
    private int showId;

    private String showName;

    public Shows(int showId, String showName){
        this.showId = showId;
        this.showName = showName;
    }

    public int getShowId() {
        return showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
