package com.example.tvshowtime.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class LinkTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static String linkToString(Link link){
        return gson.toJson(link).toString();
    }

    @TypeConverter
    public static Link stringToLink(String data){
        return gson.fromJson(data,Link.class);
    }
}
