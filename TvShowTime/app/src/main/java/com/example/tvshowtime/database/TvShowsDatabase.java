package com.example.tvshowtime.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Shows.class,Show.class,Seasons.class,Episodes.class,Cast.class},version = 1)
public abstract class TvShowsDatabase extends RoomDatabase {
    private static TvShowsDatabase instance;

    public abstract ShowsDao showsDao();

    public abstract ShowDao showDao();

    public abstract SeasonsDao seasonsDao();

    public abstract EpisodesDao episodesDao();

    public abstract CastDao castDao();

    public static synchronized TvShowsDatabase getInstance(Context context){
        if(instance!=null){
            instance = Room.databaseBuilder(context.getApplicationContext(),TvShowsDatabase.class,"tv_shows_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
