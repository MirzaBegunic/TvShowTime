package com.example.tvshowtime.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.repository.TvShowRepository;
import com.example.tvshowtime.tvmazeapi.ShowJson;

import java.util.List;

public class SearchActivityViewModel extends AndroidViewModel {
    private LiveData<List<ShowJson>> searchedShows;
    private TvShowRepository repository;

    public SearchActivityViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public void setSearchedShows(String query){
        repository.searchShows(query);
    }

    public LiveData<List<ShowJson>> getSearchedShows(){
        searchedShows = repository.getSearchedShows();
        return searchedShows;
    }
}
