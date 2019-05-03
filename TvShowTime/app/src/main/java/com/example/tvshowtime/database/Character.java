package com.example.tvshowtime.database;

import com.google.gson.annotations.SerializedName;

public class Character {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private ImageLinks imageUrl;

    public Character(Integer id, String name, ImageLinks imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageLinks getImageUrl() {
        return imageUrl;
    }
}
