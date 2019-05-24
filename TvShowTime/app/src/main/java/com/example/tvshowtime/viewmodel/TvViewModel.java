package com.example.tvshowtime.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.repository.TvShowRepository;
import com.example.tvshowtime.tvmazeapi.ShowJson;

import java.util.List;

public class TvViewModel extends AndroidViewModel {

    private TvShowRepository repository;
    private LiveData<List<ShowJson>> searchedShows;
    private LiveData<List<Show>> discoverShow;

    public TvViewModel(@NonNull Application application) {
        super(application);
        repository = new TvShowRepository(application);
    }

    /**Called to search and get a List of shows as result */
    public LiveData<List<ShowJson>> getSearchedShows(String query) {
        repository.searchSeries(query);
        searchedShows = repository.getSearchSeries();
        return searchedShows;
    }
    /**Insert a show in DB*/
    public void insertShow(int id){
        repository.insertSeriesInDatabase(id);
    }

}
