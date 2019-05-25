package com.example.tvshowtime.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.google.gson.annotations.SerializedName;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Episodes.tab_EpisodesTableName, indices = {@Index(value = {"showId","seasonNumber"},unique = false)},primaryKeys = {Episodes.col_ShowId, Episodes.col_EpisodeId}, foreignKeys = @ForeignKey(entity = Seasons.class, parentColumns = {Seasons.col_ShowId,Seasons.col_SeasonNumber}, childColumns = {Episodes.col_ShowId, Episodes.col_SeasonNumber}, onDelete = CASCADE))
public class Episodes implements Parcelable {

    public static final String tab_EpisodesTableName = "episodes_table";
    public static final String col_ShowId = "showId";
    public static final String col_EpisodeId = "episodeId";
    public static final String col_EpisodeName = "episodeName";
    public static final String col_EpisodeNumber = "episodeNumber";
    public static final String col_SeasonNumber = "seasonNumber";
    public static final String col_EpisodeAirDate = "episodeAirDate";
    public static final String col_EpisodeAirTimeStamp = "episodeAirTimeStamp";
    public static final String col_EpisodeRuntime = "episodeRunTime";
    public static final String col_EpisodeImageLarge = ImageLinks.IMAGELARGE;
    public static final String col_EpisodeImageSmall = ImageLinks.IMAGESMALL;
    public static final String col_EpisodeSummary = "episodeSummary";
    public static final String col_EpisodeSeenStatus = "episodeSeenStatus";

    @NonNull
    @ColumnInfo(name = col_ShowId)
    private int showId;

    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = col_EpisodeId)
    private int episodeId;

    @SerializedName("name")
    @ColumnInfo(name = col_EpisodeName)
    private String episodeName;

    @SerializedName("number")
    @ColumnInfo(name = col_EpisodeNumber)
    private int episodeNumber;

    @SerializedName("season")
    @ColumnInfo(name = col_SeasonNumber)
    private int seasonNumber;

    @SerializedName("airdate")
    @ColumnInfo(name = col_EpisodeAirDate)
    private String airDate;

    @SerializedName("airstamp")
    @ColumnInfo(name = col_EpisodeAirTimeStamp)
    private String airStamp;

    @SerializedName("runtime")
    @ColumnInfo(name = col_EpisodeRuntime)
    private int runTime;

    @Embedded
    @SerializedName("image")
    private ImageLinks imageUrl;

    @SerializedName("summary")
    @ColumnInfo(name = col_EpisodeSummary)
    private String summary;

    @ColumnInfo(name = col_EpisodeSeenStatus)
    private Boolean seenStatus;

    public Episodes(int showId, int episodeId, String episodeName, int episodeNumber, int seasonNumber, String airDate, String airStamp, int runTime, ImageLinks imageUrl, String summary, Boolean seenStatus) {
        this.showId = showId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
