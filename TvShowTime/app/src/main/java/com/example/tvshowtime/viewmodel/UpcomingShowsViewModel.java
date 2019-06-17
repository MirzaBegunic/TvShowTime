package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.database.ShowAndEpisodes;
import com.example.tvshowtime.repository.TvShowRepository;

import java.util.List;

public class UpcomingShowsViewModel extends AndroidViewModel {
    private TvShowRepository repository;
    private LiveData<List<ShowAndEpisodes>> upcomingEpisodes;

    public UpcomingShowsViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public LiveData<List<ShowAndEpisodes>> getUpcomingEpisodes(){
        upcomingEpisodes = repository.getUpcomingEpisodes();
        return upcomingEpisodes;
    }
}
