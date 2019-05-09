package com.example.tvshowtime.tvmazeapi;

import com.example.tvshowtime.database.Show;

public class ShowJson {
    private double score;
    private Show show;

    public ShowJson(double score, Show show) {
        this.score = score;
        this.show = show;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
