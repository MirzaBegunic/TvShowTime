package com.example.tvshowtime.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Episodes.EPISODESTABLENAME, primaryKeys = {Episodes.SHOWID, Episodes.SEASONID, Episodes.EPISODEID}, foreignKeys = @ForeignKey(entity = Seasons.class, parentColumns = {Seasons.SHOWID,Seasons.SEASONID}, childColumns = {Episodes.SHOWID, Episodes.SEASONID}, onDelete = CASCADE))
public class Episodes {

    public static final String EPISODESTABLENAME = "episodes_table";
    public static final String SHOWID = "showId";
    public static final String SEASONID = "seasonId";
    public static final String EPISODEID = "episodeId";
    public static final String EPISODENAME = "episodeName";
    public static final String EPISODENUMBER = "episodeNumber";
    public static final String SEASONNUMBER = "seasonNumber";
    public static final String EPISODEAIRDATE = "episodeAirDate";
    public static final String EPISODEAIRTIMESTAMP = "episodeAirTimeStamp";
    public static final String EPISODERUNTIME = "episodeRunTime";
    public static final String EPISODEIMAGELARGE = ImageLinks.IMAGELARGE;
    public static final String EPISODEIMAGESMALL = ImageLinks.IMAGESMALL;
    public static final String EPISODESUMMARY = "episodeSummary";
    public static final String EPISODESEENSTATUS = "episodeSeenStatus";

    @NonNull
    @ColumnInfo(name = SHOWID)
    private int showId;

    @NonNull
    @ColumnInfo(name = SEASONID)
    private int seasonId;

    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = EPISODEID)
    private int episodeId;

    @SerializedName("name")
    @ColumnInfo(name = EPISODENAME)
    private String episodeName;

    @SerializedName("number")
    @ColumnInfo(name = EPISODENUMBER)
    private int episodeNumber;

    @SerializedName("season")
    @ColumnInfo(name = SEASONNUMBER)
    private int seasonNumber;

    @SerializedName("airdate")
    @ColumnInfo(name = EPISODEAIRDATE)
    private String airDate;

    @SerializedName("airstamp")
    @ColumnInfo(name = EPISODEAIRTIMESTAMP)
    private String airStamp;

    @SerializedName("runtime")
    @ColumnInfo(name = EPISODERUNTIME)
    private int runTime;

    @Embedded
    @SerializedName("image")
    private ImageLinks imageUrl;

    @SerializedName("summary")
    @ColumnInfo(name = EPISODESUMMARY)
    private String summary;

    @ColumnInfo(name = EPISODESEENSTATUS)
    private Boolean seenStatus;

    public Episodes(int showId, int seasonId, int episodeId, String episodeName, int episodeNumber, int seasonNumber, String airDate, String airStamp, int runTime, ImageLinks imageUrl, String summary, Boolean seenStatus) {
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
        this.seenStatus = seenStatus;
    }

    public int getShowId() {
        return showId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public String getAirDate() {
        return airDate;
    }

    public String getAirStamp() {
        return airStamp;
    }

    public int getRunTime() {
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

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public void setAirStamp(String airStamp) {
        this.airStamp = airStamp;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public void setImageUrl(ImageLinks imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
