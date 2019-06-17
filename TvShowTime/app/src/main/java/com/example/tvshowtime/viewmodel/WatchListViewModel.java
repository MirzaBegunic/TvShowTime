package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.repository.TvShowRepository;

import java.util.List;

public class WatchListViewModel extends AndroidViewModel {
    LiveData<List<Show>> watchListShows;
    TvShowRepository repository;

    public WatchListViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public LiveData<List<Show>> getWatchListShows(){
        watchListShows = repository.getWatchedListShows();
        return watchListShows;
    }

}
