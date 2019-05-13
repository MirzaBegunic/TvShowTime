package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = Shows.tab_ShowsTable, primaryKeys = Shows.col_ShowId)
public class Shows {

    public static final String tab_ShowsTable = "shows_table";
    public static final String col_ShowId = "showId";
    public static final String col_ShowName = "showName";

    @ColumnInfo(name = col_ShowId)
    private int showId;

    @ColumnInfo(name = col_ShowName)
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
