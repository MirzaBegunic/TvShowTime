package com.example.tvshowtime.database;

import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    private Link self;
    @SerializedName("previousepisode")
    private Link previousEpisode;
    @SerializedName("nextepisode")
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
