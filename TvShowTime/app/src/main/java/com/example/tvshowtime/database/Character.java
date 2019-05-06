package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class Character {

    public static final String CHARACTERID = "characterId";
    public static final String CHARACTERNAME = "characterName";

    @SerializedName("id")
    @ColumnInfo(name = CHARACTERID)
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = CHARACTERNAME)
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
