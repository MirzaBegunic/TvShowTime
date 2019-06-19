package com.mirza.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirza.tvshowtime.app.App;
import com.mirza.tvshowtime.repository.TvShowRepository;
import com.mirza.tvshowtime.tvmazeapi.ShowJson;

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
