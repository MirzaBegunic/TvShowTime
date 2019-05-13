package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class Character {

    public static final String mCharacterId = "characterId";
    public static final String mCharacterName = "characterName";

    @SerializedName("id")
    @ColumnInfo(name = mCharacterId)
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = mCharacterName)
    private String name;

    @SerializedName("image")
    @Embedded
    private ImageLinks imageUrl;

    public Character(int id, String name, ImageLinks imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageLinks getImageUrl() {
        return imageUrl;
    }
}
