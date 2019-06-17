package com.example.tvshowtime.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.tvshowtime.activities.ShowDetails;
import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Cast;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.database.Seasons;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.repository.TvShowRepository;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AddShowToDatabaseService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AddShowToDatabaseService(){
        super("AddShowToDatabaseService");
    }

    public AddShowToDatabaseService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int showId = intent.getIntExtra(ShowDetails.ACTION_ADD_SHOW,0);
        TvShowRepository repository = App.getInstance().getTvShowRepository();
        repository.insertSeriesInDatabase(showId);
    }
}
