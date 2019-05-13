package com.example.tvshowtime.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.google.gson.annotations.SerializedName;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Cast.col_CastTableName, primaryKeys = {Cast.col_ShowId, Cast.col_PersonId}, foreignKeys = @ForeignKey(entity = Show.class, parentColumns = Show.col_ShowId, childColumns = Cast.col_ShowId, onDelete = CASCADE))
public class Cast {

    public static final String col_CastTableName = "cast_table";
    public static final String col_ShowId = "showId";
    public static final String col_PersonId = Person.mPersonId;
    public static final String col_PersonName = Person.mPersonName;
    public static final String col_CharacterId = Character.mCharacterId;
    public static final String col_CharacterName = Character.mCharacterName;
    public static final String col_PersonImageLarge = ImageLinks.IMAGELARGE;
    public static final String col_PersonImageSmall = ImageLinks.IMAGESMALL;

    @NonNull
    @ColumnInfo(name = col_ShowId)
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
