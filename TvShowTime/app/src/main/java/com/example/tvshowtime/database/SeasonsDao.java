package com.example.tvshowtime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SeasonsDao {

    @Insert
    void insert(Seasons seasons);

    @Delete
    void delete(Seasons seasons);

    @Update
    void update(Seasons seasons);

    @Query("SELECT * FROM seasons_table")
    LiveData<List<Seasons>> getAllSeasons();

    @Query("SELECT * FROM seasons_table WHERE showId=:Id")
    LiveData<List<Seasons>> getSeasonsFromShowById(int Id);

    @Query("SELECT * FROM seasons_table WHERE showId=:Id")
    List<Seasons> getSeasons(int Id);

    @Query("SELECT * FROM seasons_table WHERE seasonId=:Id")
    LiveData<Seasons> getSeasonById(int Id);
}
