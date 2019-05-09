package com.example.tvshowtime.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EpisodesDao {
    @Insert
    void insert(Episodes episodes);

    @Delete
    void delete(Episodes episodes);

    @Update
    void update(Episodes episodes);

    @Query("SELECT * FROM episodes_table WHERE episodeId=:id")
    Episodes getEpisodeFromId(int id);
}
