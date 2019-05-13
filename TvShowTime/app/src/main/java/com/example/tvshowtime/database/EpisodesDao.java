package com.example.tvshowtime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EpisodesDao {
    @Insert
    void insert(Episodes episodes);

    @Delete
    void delete(Episodes episodes);

    @Update
    void update(Episodes episodes);

    @Query("SELECT * FROM episodes_table WHERE showId=:Id")
    LiveData<List<Episodes>> getEpisodesFromShowId(int Id);

    @Query("SELECT * FROM episodes_table WHERE showId=:Id AND seasonNumber=:Num")
    LiveData<List<Episodes>> getEpisodesBySeasonId(int Id, int Num);

    @Query("SELECT * FROM episodes_table WHERE episodeId=:id")
    LiveData<Episodes> getEpisodeFromId(int id);

    @Query("SELECT * FROM episodes_table")
    LiveData<List<Episodes>> getAllEpisodes();
}
