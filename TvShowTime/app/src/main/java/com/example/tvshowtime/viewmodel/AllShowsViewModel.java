package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.repository.TvShowRepository;

import java.util.List;

public class AllShowsViewModel extends AndroidViewModel {
    private LiveData<List<Show>> myShows;
    private TvShowRepository repository;

    public AllShowsViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public LiveData<List<Show>> getMyShows(){
        myShows = repository.getMyShows();
        return myShows;
    }
}
