package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.repository.TvShowRepository;

import java.util.HashMap;
import java.util.List;

public class ShowDetailsEpisodesFragmentViewModel extends AndroidViewModel {

    private LiveData<List<String>> headers;
    private LiveData<HashMap<String,List<Episodes>>> map;
    TvShowRepository repository;

    public ShowDetailsEpisodesFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
        headers = new MutableLiveData<>();
        map = new MutableLiveData<>();
    }

    public void fetchData(int showId){
        repository.fetchShowSeasonsAndEpisodesMap(showId);
    }

    public LiveData<List<String>> getHeaders(){
        headers = repository.getHeaders();
        return headers;
    }

    public LiveData<HashMap<String,List<Episodes>>> getMap(){
        map = repository.getShowSeasonsAndEpisodes();
        return map;
    }
}
