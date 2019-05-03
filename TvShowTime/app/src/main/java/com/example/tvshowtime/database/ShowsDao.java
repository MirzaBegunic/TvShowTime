package com.example.tvshowtime.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ShowsDao {

    @Insert
    void insert(Shows shows);

    @Delete
    void delete(Shows shows);

    @Update
    void update(Shows shows);
}
