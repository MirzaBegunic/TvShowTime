package com.example.tvshowtime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CastDao {
    @Insert
    void insert(Cast cast);

    @Delete
    void delete(Cast cast);

    @Update
    void update(Cast cast);

    @Query("SELECT * FROM cast_table")
    LiveData<List<Cast>> getAllCast();

    @Query("SELECT * FROM cast_table WHERE showId=:Id")
    LiveData<List<Cast>> getAllCastFromShowById(int Id);

    @Query("SELECT * FROM cast_table WHERE personId=:Id")
    LiveData<Cast> getCastMemberById(int Id);
}
