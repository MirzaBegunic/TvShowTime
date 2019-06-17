package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.repository.TvShowRepository;

public class SearchWatchListViewModel extends AndroidViewModel {
    private TvShowRepository repository;
    private LiveData<Episodes> episode;
    private String searchString = "";

    public SearchWatchListViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public void searchEpisode(String showName){
        Long timestamp = System.currentTimeMillis();
        repository.setSearchWatchList(showName,timestamp);
    }

    public LiveData<Episodes> getSearchedEpisode(){
        episode = repository.getSearchWatchList();
        return episode;
    }
}
