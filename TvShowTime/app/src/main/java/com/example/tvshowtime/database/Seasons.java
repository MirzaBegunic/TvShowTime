package com.example.tvshowtime.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.google.gson.annotations.SerializedName;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Seasons.tab_SeasonsTableName, indices = {@Index(value = {Seasons.col_SeasonNumber, Seasons.col_ShowId}, unique = true)}, primaryKeys = {Seasons.col_ShowId, Seasons.col_SeasonId}, foreignKeys = @ForeignKey(entity = Show.class, parentColumns = Show.col_ShowId,childColumns = Seasons.col_ShowId,onDelete = CASCADE ))
public class Seasons {

    public static final String tab_SeasonsTableName = "seasons_table";
    public static final String col_ShowId = "showId";
    public static final String col_SeasonId = "seasonId";
    public static final String col_SeasonNumber = "seasonNumber";
    public static final String col_SeasonEpisodeCount = "seasonEpisodeCount";
    public static final String col_SeasonPremiereDate = "seasonPremiereDate";
    public static final String col_SeasonEndDate = "seasonEndDate";
    public static final String col_SeasonImageLarge = ImageLinks.IMAGELARGE;
    public static final String col_SeasonImageSmall = ImageLinks.IMAGESMALL;
    public static final String col_SeasonWatchedCount = "seasonWatchedEpisodesCount";
    public static final String col_SeasonWatchedStatus = "seasonWatchedStatus";

    @NonNull
    @ColumnInfo(name = col_ShowId)
    private int showId;
    
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = col_SeasonId, index = true)
    private int seasonId;

    @SerializedName("number")
    @ColumnInfo(name = col_SeasonNumber)
    private int seasonNumber;

    @Ignore
    @SerializedName("episodeOrder")
    @ColumnInfo(name = col_SeasonEpisodeCount)
    private int episodeCount;

    @Ignore
    @SerializedName("premiereDate")
    @ColumnInfo(name = col_SeasonPremiereDate)
    private String premiereDate;

    @Ignore
    @SerializedName("endDate")
    @ColumnInfo(name = col_SeasonEndDate)
    private String endDate;

    @Ignore
    @Embedded
    @SerializedName("image")
    private ImageLinks imageUrl;

    @ColumnInfo(name = col_SeasonWatchedCount)
    private int watchedCount;

    @ColumnInfo(name = col_SeasonWatchedStatus)
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

    public Seasons(int showId, int seasonId, int seasonNumber, int watchedCount, Boolean seenStatus) {
        this.showId = showId;
        this.seasonId = seasonId;
        this.seasonNumber = seasonNumber;
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
