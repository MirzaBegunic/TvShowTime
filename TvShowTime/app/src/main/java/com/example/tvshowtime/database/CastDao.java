package com.example.tvshowtime.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface CastDao {
    @Insert
    void insert(Cast cast);

    @Delete
    void delete(Cast cast);

    @Update
    void update(Cast cast);
}
