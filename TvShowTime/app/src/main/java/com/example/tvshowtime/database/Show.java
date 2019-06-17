package com.example.tvshowtime.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Show.tab_ShowTable, primaryKeys = Show.col_ShowId)
public class Show {

    public static final String tab_ShowTable = "show_table";
    public static final String col_ShowId = "showId";
    public static final String col_ShowName = "showName";
    public static final String col_ShowUrl = "showUrl";
    public static final String col_ShowGenres = "showGenres";
    public static final String col_ShowStatus = "showStatus";
    public static final String col_ShowRunTime = "showRunTime";
    public static final String col_ShowPremier = "showPremieredDate";
    public static final String col_ShowRating = "averageRating";
    public static final String col_ShowImageSmall = ImageLinks.IMAGESMALL;
    public static final String col_ShowImageLarge = ImageLinks.IMAGELARGE;
    public static final String col_ShowSelfLink = "showLinkSelf";
    public static final String col_ShowLinkNextEpisode = "showLinkNextEp";
    public static final String col_ShowLinkPreviousEpisode = "showLinkPrevEp";
    public static final String col_ShowDescription = "showDescription";
    public static final String col_ShowSeasonCount = "showSeasonCount";

    @SerializedName("id")
    @ColumnInfo(name = col_ShowId)
    private int showId;

    @SerializedName("name")
    @ColumnInfo(name = col_ShowName)
    private String showName;

    @Ignore
    @SerializedName("url")
    @ColumnInfo(name = col_ShowUrl)
    private String url;


    @Ignore
    @TypeConverters(GenresConverter.class)
    @SerializedName("genres")
    @ColumnInfo(name = col_ShowGenres)
    private String [] genres;

    @Ignore
    @SerializedName("status")
    @ColumnInfo(name = col_ShowStatus)
    private String status;

    @Ignore
    @SerializedName("runtime")
    @ColumnInfo(name = col_ShowRunTime)
    private int runTime;

    @Ignore
    @SerializedName("premiered")
    @ColumnInfo(name = col_ShowPremier)
    private String premiered;

    @Ignore
    @Embedded
    @SerializedName("rating")
    private Rating rating;

    @Embedded
    @SerializedName("image")
    private ImageLinks imageUrl;

    @Ignore
    @SerializedName("summary")
    @ColumnInfo(name = col_ShowDescription)
    private String description;

    @Ignore
    @Embedded
    @SerializedName("_links")
    private Links links;

    @Ignore
    @SerializedName("network")
    private Network network;




    public Show(int showId, String showName, String url, String[] genres, String status, int runTime, String premiered, Rating rating, ImageLinks imageUrl, String description, Links links) {
        this.showId = showId;
        this.showName = showName;
        this.url = url;
        this.genres = genres;
        this.status = status;
        this.runTime = runTime;
        this.premiered = premiered;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        this.links = links;
    }
    public Show(int showId, String showName, String url, String[] genres, String status, int runTime, String premiered, Rating rating, ImageLinks imageUrl, String description, Links links, Network network) {
        this.showId = showId;
        this.showName = showName;
        this.url = url;
        this.genres = genres;
        this.status = status;
        this.runTime = runTime;
        this.premiered = premiered;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        this.links = links;
        this.network = network;
    }


    public Show(int showId, String showName, ImageLinks imageUrl) {
        this.showId = showId;
        this.showName = showName;
        this.imageUrl = imageUrl;
    }



    public int getShowId() {
        return showId;
    }

    public String getShowName() {
        return showName;
    }

    public String getUrl() {
        return url;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public int getRunTime() {
        return runTime;
    }

    public String getPremiered() {
        return premiered;
    }

    public Rating getRating() {
        return rating;
    }

    public ImageLinks getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public Links getLinks() {
        return links;
    }

    public Network getNetwork(){ return network; }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setImageUrl(ImageLinks imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public void setNetwork (Network network) { this.network = network; }

}