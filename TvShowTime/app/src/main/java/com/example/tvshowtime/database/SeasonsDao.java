package com.example.tvshowtime.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface SeasonsDao {

    @Insert
    void insert(Seasons seasons);

    @Delete
    void delete(Seasons seasons);

    @Update
    void update(Seasons seasons);
}
