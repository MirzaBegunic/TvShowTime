package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @TypeConverters(LinkTypeConverter.class)
    @ColumnInfo(name = Show.SHOWLINKSELF)
    private Link self;

    @SerializedName("previousepisode")
    @TypeConverters(LinkTypeConverter.class)
    @ColumnInfo(name = Show.SHOWLINKPREVEP)
    private Link previousEpisode;

    @SerializedName("nextepisode")
    @TypeConverters(LinkTypeConverter.class)
    @ColumnInfo(name = Show.SHOWLINKNEXTEP)
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
