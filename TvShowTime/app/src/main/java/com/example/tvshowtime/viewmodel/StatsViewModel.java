package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.repository.TvShowRepository;


public class StatsViewModel extends AndroidViewModel {
    TvShowRepository repository;
    LiveData<Long> totalTime;
    LiveData<Long> totalEpisodeCount;
    LiveData<Show> mostWatched;
    LiveData<Long> mostWatchedSum;
    public StatsViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public LiveData<Long> getTotalTime() {
        totalTime = repository.getTotalTime();
        return totalTime;
    }

    public LiveData<Long> getTotalEpisodeCount() {
        totalEpisodeCount = repository.getTotalEpisodesCount();
        return totalEpisodeCount;
    }

    public LiveData<Show> getMostWatched() {
        mostWatched = repository.getMostWatched();
        return mostWatched;
    }

    public LiveData<Long> getMostWatchedSum(){
        mostWatchedSum = repository.getMostWatchedSum();
        return mostWatchedSum;
    }
}
