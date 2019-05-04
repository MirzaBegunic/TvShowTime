package com.example.tvshowtime.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface DatabaseDao {
    @Insert
    void insert(Shows shows, Show show, Seasons seasons, Episodes episodes, Cast cast);

    @Delete
    void delete(Shows shows, Show show, Seasons seasons, Episodes episodes, Cast cast);

    @Update
    void update(Shows shows, Show show, Seasons seasons, Episodes episodes, Cast cast);
}
