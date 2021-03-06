package com.mirza.tvshowtime.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Show.class,Seasons.class,Episodes.class},views = {ShowAndEpisodes.class},version = 4,exportSchema = false)
public abstract class TvShowsDatabase extends RoomDatabase {
    private static TvShowsDatabase instance;
    public abstract ShowDao showDao();
    public abstract SeasonsDao seasonsDao();
    public abstract EpisodesDao episodesDao();


    public static synchronized TvShowsDatabase getInstance(Context context){
        Log.d("DATABASE", "Getting Instance");
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),TvShowsDatabase.class,"tv_shows_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}

