package com.mirza.tvshowtime.database;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @TypeConverters(LinkTypeConverter.class)
    @ColumnInfo(name = Show.col_ShowSelfLink)
    private Link self;

    @SerializedName("previousepisode")
    @TypeConverters(LinkTypeConverter.class)
    @ColumnInfo(name = Show.col_ShowLinkPreviousEpisode)
    private Link previousEpisode;

    @SerializedName("nextepisode")
    @TypeConverters(LinkTypeConverter.class)
    @ColumnInfo(name = Show.col_ShowLinkNextEpisode)
    private Link nextEpisode;

    public Links(Link self, Link previousEpisode, Link nextEpisode){
        this.self = self;
        this.previousEpisode = previousEpisode;
        this.nextEpisode = nextEpisode;
    }

    public Link getSelf() {
        return self;
    }

    public Link getPreviousEpisode() {
        return previousEpisode;
    }

    public Link getNextEpisode() {
        return nextEpisode;
    }
}
