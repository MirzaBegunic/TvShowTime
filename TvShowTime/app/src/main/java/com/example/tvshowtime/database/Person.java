package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Person {

    public static final String PERSONID = "personId";
    public static final String PERSONNAME = "personName";

    @SerializedName("id")
    @ColumnInfo(name = PERSONID)
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = PERSONNAME)
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
