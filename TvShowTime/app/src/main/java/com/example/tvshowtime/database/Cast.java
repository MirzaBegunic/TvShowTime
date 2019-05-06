package com.example.tvshowtime.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.google.gson.annotations.SerializedName;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Cast.CASTTABLENAME, primaryKeys = {Cast.SHOWID, Cast.PERSONID}, foreignKeys = @ForeignKey(entity = Show.class, parentColumns = Show.SHOWID, childColumns = Cast.SHOWID, onDelete = CASCADE))
public class Cast {

    public static final String CASTTABLENAME = "cast_table";
    public static final String SHOWID = "showId";
    public static final String PERSONID = Person.PERSONID;
    public static final String PRESONNAME = Person.PERSONNAME;
    public static final String CHARACTERID = Character.CHARACTERID;
    public static final String CHARACTERNAME = Character.CHARACTERNAME;
    public static final String PERSONIMAGELARGE = ImageLinks.IMAGELARGE;
    public static final String PERSONIMAGESMALL = ImageLinks.IMAGESMALL;

    @NonNull
    @ColumnInfo(name = SHOWID)
    private Integer showId;

    @Embedded
    @NonNull
    @SerializedName("person")
    private Person realPerson;

    @Embedded
    @SerializedName("character")
    private Character seriesCharacter;

    public Cast(@NonNull Integer showId, @NonNull Person realPerson, Character seriesCharacter) {
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
