package com.example.tvshowtime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShowsDao {

    @Insert
    void insert(Shows shows);

    @Delete
    void delete(Shows shows);

    @Update
    void update(Shows shows);

    @Query("SELECT * FROM shows_table")
    LiveData<List<Shows>> getAllShows();

    @Query("SELECT * FROM shows_table WHERE showId = :Id")
    LiveData<Shows> getShowById(int Id);

    @Query("SELECT * FROM shows_table WHERE showId = :Id")
    Shows getShowById2(int Id);
}
