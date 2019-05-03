package com.example.tvshowtime.database;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "seasons_table", primaryKeys = {"showId", "seasonId"})
public class Seasons {

    private Integer showId;

    @SerializedName("id")
    private Integer seasonId;

    @SerializedName("number")
    private Integer seasonNumber;

    @SerializedName("episodeOrder")
    private Integer episodeCount;

    @SerializedName("premiereDate")
    private String premiereDate;

    @SerializedName("endDate")
    private String endDate;

    @SerializedName("image")
    private ImageLinks imageUrl;

    private Integer watchedCount;

    private Boolean seenStatus;

    public Seasons(Integer showId, Integer seasonId, Integer seasonNumber, Integer episodeCount, String premiereDate, String endDate, ImageLinks imageUrl) {
        this.showId = showId;
        this.seasonId = seasonId;
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
        this.premiereDate = premiereDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.seenStatus = false;
        this.watchedCount = 0;
    }

    public Integer getShowId() {
        return showId;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public String getPremiereDate() {
        return premiereDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public ImageLinks getImageUrl() {
        return imageUrl;
    }

    public Integer getWatchedCount() {
        return watchedCount;
    }

    public Boolean getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(Boolean seenStatus){
        this.seenStatus = seenStatus;
    }

    public void setWatchedCount(Integer count){
        this.watchedCount = count;
    }
}
