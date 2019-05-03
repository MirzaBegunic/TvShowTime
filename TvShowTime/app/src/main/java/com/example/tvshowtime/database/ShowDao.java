package com.example.tvshowtime.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ShowDao {

    @Insert
    void insert(Show show);

    @Delete
    void delete(Show show);

    @Update
    void update(Show show);
}
