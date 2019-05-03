package com.example.tvshowtime.database;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "cast_table", primaryKeys = {"showId", "realPerson"})
public class Cast {
    private Integer showId;

    @SerializedName("person")
    private Person realPerson;

    @SerializedName("character")
    private Character seriesCharacter;

    public Cast(Integer showId, Person realPerson, Character seriesCharacter) {
        this.showId = showId;
        this.realPerson = realPerson;
        this.seriesCharacter = seriesCharacter;
    }

    public Integer getShowId() {
        return showId;
    }

    public Person getRealPerson() {
        return realPerson;
    }

    public Character getSeriesCharacter() {
        return seriesCharacter;
    }
}
