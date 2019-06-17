package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.repository.TvShowRepository;

public class EpisodeDetailsViewModel extends AndroidViewModel {

    TvShowRepository repository;
    LiveData<Episodes> episode;

    public EpisodeDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public LiveData<Episodes> getEpisode(int showId, int seasonNumber, int episodeNumber) {
        episode = repository.getEpisodeInfo(showId,seasonNumber,episodeNumber);
        return episode;
    }

    public void destroyEpisodeInfo(){
        repository.destroyEpisodeInfo();
    }
}
