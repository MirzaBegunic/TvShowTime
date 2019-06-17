package com.example.tvshowtime.database;

import androidx.room.DatabaseView;
import androidx.room.Embedded;

@DatabaseView(value = "SELECT show_table.*, episodes_table.episodeAirDate, episodes_table.episodeName, episodes_table.episodeNumber, episodes_table.seasonNumber, episodes_table.episodeAirTimeStamp FROM show_table LEFT JOIN episodes_table ON show_table.showId=episodes_table.showId WHERE episodeSeenStatus=0 ORDER BY episodeAirTimeStamp DESC",viewName = ShowAndEpisodes.VIEW_NAME)
public class ShowAndEpisodes {
    public static final String VIEW_NAME =  "ShowAndEpisodesView";

    private int showId;

    private String showName;

    @Embedded
    private ImageLinks imageUrl;

    private String episodeAirDate;

    private String episodeName;

    private int episodeNumber;

    private int seasonNumber;

    private String summary;

    private Long episodeAirTimeStamp;


    public ShowAndEpisodes(int showId, String showName, ImageLinks imageUrl, String episodeAirDate, String episodeName, int episodeNumber, int seasonNumber, String summary, Long episodeAirTimeStamp) {
        this.showId = showId;
        this.showName = showName;
        this.imageUrl = imageUrl;
        this.episodeAirDate = episodeAirDate;
        this.episodeName = episodeName;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.summary = summary;
        this.episodeAirTimeStamp = episodeAirTimeStamp;
    }

    public int getShowId() {
        return showId;
    }

    public String getShowName() {
        return showName;
    }

    public ImageLinks getImageUrl() {
        return imageUrl;
    }

    public String getEpisodeAirDate() {
        return episodeAirDate;
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

    public Long getEpisodeAirTimeStamp() {
        return episodeAirTimeStamp;
    }

    public String getSummary() {
        return summary;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setImageUrl(ImageLinks imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEpisodeAirDate(String episodeAirDate) {
        this.episodeAirDate = episodeAirDate;
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

    public void setEpisodeAirTimeStamp(Long episodeAirTimeStamp) {
        this.episodeAirTimeStamp = episodeAirTimeStamp;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
