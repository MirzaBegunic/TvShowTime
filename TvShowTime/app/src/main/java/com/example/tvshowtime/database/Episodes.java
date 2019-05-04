package com.example.tvshowtime.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "episodes_table", primaryKeys = {"showId", "seasonId", "episodeId"}, foreignKeys = @ForeignKey(entity = Seasons.class, parentColumns = {"showId","seasonId"}, childColumns = {"showId", "seasonId"}, onDelete = CASCADE))
public class Episodes {

    private Integer showId;

    private Integer seasonId;

    @SerializedName("id")
    private Integer episodeId;

    @SerializedName("name")
    private String episodeName;

    @SerializedName("number")
    private Integer episodeNumber;

    @SerializedName("season")
    private Integer seasonNumber;

    @SerializedName("airdate")
    private String airDate;

    @SerializedName("airstamp")
    private String airStamp;

    @SerializedName("runtime")
    private Integer runTime;

    @SerializedName("image")
    private ImageLinks imageUrl;

    @SerializedName("summary")
    private String summary;

    private Boolean seenStatus;

    public Episodes(Integer showId, Integer seasonId, Integer episodeId, String episodeName, Integer episodeNumber, Integer seasonNumber, String airDate, String airStamp, Integer runTime, ImageLinks imageUrl, String summary) {
        this.showId = showId;
        this.seasonId = seasonId;
        this.episodeId = episodeId;
        this.episodeName = episodeName;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.airDate = airDate;
        this.airStamp = airStamp;
        this.runTime = runTime;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.seenStatus = false;
    }

    public Integer getShowId() {
        return showId;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public String getAirDate() {
        return airDate;
    }

    public String getAirStamp() {
        return airStamp;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public ImageLinks getImageUrl() {
        return imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public Boolean getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(Boolean status){
        this.seenStatus = status;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public void setAirStamp(String airStamp) {
        this.airStamp = airStamp;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public void setImageUrl(ImageLinks imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
