package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.repository.TvShowRepository;
import com.example.tvshowtime.tvmazeapi.ShowJson;

import java.util.List;

public class TvViewModel extends AndroidViewModel {

    private TvShowRepository repository;
    private LiveData<List<ShowJson>> searchedShows;

    public TvViewModel(@NonNull Application application) {
        super(application);
        repository = new TvShowRepository(application);
    }

    public LiveData<List<ShowJson>> getSearchedShows(String query) {
        repository.searchSeries(query);
        searchedShows = repository.getSearchSeries();
        return searchedShows;
    }

    public void insertShow(int id){
        repository.insertSeriesInDatabase(id);
    }
}
