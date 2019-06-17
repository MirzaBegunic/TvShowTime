package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.repository.TvShowRepository;

public class UpcomingSearchShowViewModel extends AndroidViewModel {

    TvShowRepository repository;
    LiveData<Episodes> episodesLiveData;

    public UpcomingSearchShowViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public void setSearchUpcoming(String showName){
        Long timestamp = System.currentTimeMillis();
        repository.setSearchUpcoming(showName,timestamp);
    }

    public LiveData<Episodes> getSearchUpcoming(){
        episodesLiveData = repository.getSearchUpcoming();
        return episodesLiveData;
    }
}
