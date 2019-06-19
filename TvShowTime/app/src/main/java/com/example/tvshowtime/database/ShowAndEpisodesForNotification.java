package com.example.tvshowtime.database;

public class ShowAndEpisodesForNotification {

    private int showId;
    private String showName;
    private String episodeName;


    public ShowAndEpisodesForNotification(String showName, String episodeName, int showId) {
        this.showId = showId;
        this.showName = showName;
        this.episodeName = episodeName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }
}
