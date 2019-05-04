package com.example.tvshowtime.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "cast_table", primaryKeys = {"showId", "realPerson"}, foreignKeys = @ForeignKey(entity = Show.class, parentColumns = "showId", childColumns = "showId", onDelete = CASCADE))
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

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public void setRealPerson(Person realPerson) {
        this.realPerson = realPerson;
    }

    public void setSeriesCharacter(Character seriesCharacter) {
        this.seriesCharacter = seriesCharacter;
    }
}
