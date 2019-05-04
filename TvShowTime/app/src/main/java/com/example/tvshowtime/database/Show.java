package com.example.tvshowtime.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "show_table", foreignKeys = @ForeignKey(entity = Shows.class,parentColumns = "showId", childColumns = "showId", onDelete = CASCADE))
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

    public void setRating(Double rating) {
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

    public void setSeasonCount(Integer seasonCount) {
        this.seasonCount = seasonCount;
    }
}
