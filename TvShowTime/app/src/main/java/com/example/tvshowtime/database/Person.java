package com.example.tvshowtime.database;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Person {

    public static final String mPersonId = "personId";
    public static final String mPersonName = "personName";

    @SerializedName("id")
    @ColumnInfo(name = mPersonId)
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = mPersonName)
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
