package com.mirza.tvshowtime.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.mirza.tvshowtime.activities.ShowDetails;
import com.mirza.tvshowtime.app.App;
import com.mirza.tvshowtime.repository.TvShowRepository;

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
