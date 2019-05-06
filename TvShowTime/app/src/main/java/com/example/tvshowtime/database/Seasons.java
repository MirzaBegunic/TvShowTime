package com.example.tvshowtime.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.google.gson.annotations.SerializedName;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Seasons.SEASONSTABLENAME, primaryKeys = {Seasons.SHOWID, Seasons.SEASONID}, foreignKeys = @ForeignKey(entity = Shows.class, parentColumns = Shows.SHOWID,childColumns = Seasons.SEASONID, onDelete = CASCADE ))
public class Seasons {

    public static final String SEASONSTABLENAME = "seasons_table";
    public static final String SHOWID = "showId";
    public static final String SEASONID = "seasonId";
    public static final String SEASONNUMBER = "seasonNumber";
    public static final String SEASONEPISODECOUNT = "seasonEpisodeCount";
    public static final String SEASONPREMIEREDATE = "seasonPremiereDate";
    public static final String SEASONENDDATE = "seasonEndDate";
    public static final String SEASONIMAGELARGE = ImageLinks.IMAGELARGE;
    public static final String SEASONIMAGESMALL = ImageLinks.IMAGESMALL;
    public static final String SEASONWATCHEDEPISODESCOUNT = "seasonWatchedEpisodesCount";
    public static final String SEASONWATCHEDSTATUS = "seasonWatchedStatus";

    @NonNull
    @ColumnInfo(name = SHOWID)
    private int showId;
    
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = SEASONID, index = true)
    private int seasonId;

    @SerializedName("number")
    @ColumnInfo(name = SEASONNUMBER)
    private int seasonNumber;

    @SerializedName("episodeOrder")
    @ColumnInfo(name = SEASONEPISODECOUNT)
    private int episodeCount;

    @SerializedName("premiereDate")
    @ColumnInfo(name = SEASONPREMIEREDATE)
    private String premiereDate;

    @SerializedName("endDate")
    @ColumnInfo(name = SEASONENDDATE)
    private String endDate;

    @Embedded
    @SerializedName("image")
    private ImageLinks imageUrl;

    @ColumnInfo(name = SEASONWATCHEDEPISODESCOUNT)
    private int watchedCount;

    @ColumnInfo(name = SEASONWATCHEDSTATUS)
    private Boolean seenStatus;

    public Seasons(int showId, int seasonId, int seasonNumber, int episodeCount, String premiereDate, String endDate, ImageLinks imageUrl, int watchedCount, Boolean seenStatus) {
        this.showId = showId;
        this.seasonId = seasonId;
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
        this.premiereDate = premiereDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.watchedCount = watchedCount;
        this.seenStatus = seenStatus;
    }

    public int getShowId() {
        return showId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeCount() {
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

    public int getWatchedCount() {
        return watchedCount;
    }

    public Boolean getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(Boolean seenStatus){
        this.seenStatus = seenStatus;
    }

    public void setWatchedCount(int count){
        this.watchedCount = count;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setEpisodeCount(int episodeCount) {
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
