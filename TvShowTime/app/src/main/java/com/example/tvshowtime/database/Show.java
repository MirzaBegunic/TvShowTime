package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Show.SHOWTABLENAME, primaryKeys = Show.SHOWID,foreignKeys = @ForeignKey(entity = Shows.class,parentColumns = Shows.SHOWID, childColumns = Show.SHOWID, onDelete = CASCADE))
public class Show {

    public static final String SHOWTABLENAME = "show_table";
    public static final String SHOWID = "showId";
    public static final String SHOWNAME = "showName";
    public static final String SHOWURL = "showUrl";
    public static final String SHOWGENRES = "showGenres";
    public static final String SHOWSTATUS = "showStatus";
    public static final String SHOWRUNTIME = "showRunTime";
    public static final String SHOWPREMIEREDDATE = "showPremieredDate";
    public static final String SHOWRATING = "averageRating";
    public static final String SHOWIMAGESMALL = ImageLinks.IMAGESMALL;
    public static final String SHOWIMAGELARGE = ImageLinks.IMAGELARGE;
    public static final String SHOWLINKSELF = "showLinkSelf";
    public static final String SHOWLINKNEXTEP = "showLinkNextEp";
    public static final String SHOWLINKPREVEP = "showLinkPrevEp";
    public static final String SHOWDESCRIPTION = "showDescription";
    public static final String SHOWSEASONCOUNT = "showSeasonCount";

    @SerializedName("id")
    @ColumnInfo(name = SHOWID)
    private int showId;

    @SerializedName("name")
    @ColumnInfo(name = SHOWNAME)
    private String showName;

    @SerializedName("url")
    @ColumnInfo(name = SHOWURL)
    private String url;

    @TypeConverters(GenresConverter.class)
    @SerializedName("genres")
    @ColumnInfo(name = SHOWGENRES)
    private String [] genres;

    @SerializedName("status")
    @ColumnInfo(name = SHOWSTATUS)
    private String status;

    @SerializedName("runtime")
    @ColumnInfo(name = SHOWRUNTIME)
    private int runTime;

    @SerializedName("premiered")
    @ColumnInfo(name = SHOWPREMIEREDDATE)
    private String premiered;

    @Embedded
    @SerializedName("rating")
    private Rating rating;

    @Embedded
    @SerializedName("image")
    private ImageLinks imageUrl;

    @SerializedName("summary")
    @ColumnInfo(name = SHOWDESCRIPTION)
    private String description;

    @Embedded
    @SerializedName("_links")
    private Links links;



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
}