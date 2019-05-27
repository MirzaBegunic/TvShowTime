package com.example.tvshowtime.database;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class SeasonAndEpisodes extends ExpandableGroup<Episodes> {
    private String title;
    public SeasonAndEpisodes(String title, List<Episodes> items) {
        super(title, items);
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
