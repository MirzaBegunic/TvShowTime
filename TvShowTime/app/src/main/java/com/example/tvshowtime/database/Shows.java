package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Shows.SHOWSTABLENAME, primaryKeys = Shows.SHOWID)
public class Shows {

    public static final String SHOWSTABLENAME = "shows_table";
    public static final String SHOWID = "showId";
    public static final String SHOWNAME = "showName";

    @ColumnInfo(name = SHOWID)
    private int showId;

    @ColumnInfo(name = SHOWNAME)
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
