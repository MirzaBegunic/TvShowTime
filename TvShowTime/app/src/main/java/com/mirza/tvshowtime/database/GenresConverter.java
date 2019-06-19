package com.mirza.tvshowtime.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GenresConverter {
    private static Gson gson = new Gson();
    private static Type type = new TypeToken<String[]>(){}.getType();

    @TypeConverter
    public static String genreToString(String [] genres){
        return gson.toJson(genres).toString();
    }

    @TypeConverter
    public static String[] stringToGenres(String data){
        return gson.fromJson(data, type);
    }
}
