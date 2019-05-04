package com.example.tvshowtime.database;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
