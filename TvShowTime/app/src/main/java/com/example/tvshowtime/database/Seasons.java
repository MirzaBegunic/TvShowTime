package com.example.tvshowtime.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "seasons_table", primaryKeys = {"showId", "seasonId"}, foreignKeys = @ForeignKey(entity = Shows.class, parentColumns = "showId",childColumns = "showId", onDelete = CASCADE ))
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

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public void setPremiereDate(String premiereDate) {
        this.premiereDate = premiereDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setImageUrl(ImageLinks imageUrl) {
        this.imageUrl = imageUrl;
    }
}
