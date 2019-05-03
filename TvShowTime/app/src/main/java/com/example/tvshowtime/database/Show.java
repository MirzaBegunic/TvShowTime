package com.example.tvshowtime.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "show_table")
public class Show {

    @SerializedName("id")
    @PrimaryKey
    private int showId;

    @SerializedName("name")
    private String showName;

    @SerializedName("url")
    private String url;

    @SerializedName("genres")
    private String [] genres;

    @SerializedName("status")
    private String status;

    @SerializedName("runtime")
    private int runTime;

    @SerializedName("premiered")
    private String premiered;

    @SerializedName("rating")
    private Double rating;

    @SerializedName("image")
    private ImageLinks imageUrl;

    @SerializedName("summary")
    private String description;

    @SerializedName("_links")
    private Links links;

    private Integer seasonCount;

    public Show(int showId, String showName, String url, String[] genres, String status, int runTime, String premiered, double rating, ImageLinks image, String description, Links links, int seasonCount) {
        this.showId = showId;
        this.showName = showName;
        this.url = url;
        this.genres = genres;
        this.status = status;
        this.runTime = runTime;
        this.premiered = premiered;
        this.rating = rating;
        this.imageUrl = image;
        this.description = description;
        this.links = links;
        this.seasonCount = seasonCount;
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

    public double getRating() {
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

    public int getSeasonCount() {
        return seasonCount;
    }
}
