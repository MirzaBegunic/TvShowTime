package com.example.tvshowtime.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import retrofit2.http.POST;

@Dao
public interface ShowDao {

    @Insert
    void insert(Show show);

    @Delete
    void delete(Show show);

    @Update
    void update(Show show);

    @Query("SELECT * FROM show_table WHERE showId=:Id")
    Show getShow(int Id);
}
