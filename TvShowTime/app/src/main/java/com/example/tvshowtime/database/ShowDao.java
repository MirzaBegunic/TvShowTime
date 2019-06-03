package com.example.tvshowtime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShowDao {

    @Insert
    void insert(Show show);

    @Delete
    void delete(Show show);

    @Update
    void update(Show show);

    @Query("SELECT * FROM show_table")
    LiveData<List<Show>> getAllShows();

    @Query("SELECT showId FROM show_table")
    List<Integer> getShowsIds();

    @Query("SELECT * FROM show_table WHERE showId=:Id")
    LiveData<Show> getShowById(int Id);

    @Query("SELECT * FROM show_table WHERE showId=:Id")
    Show getShowById2(int Id);


}
