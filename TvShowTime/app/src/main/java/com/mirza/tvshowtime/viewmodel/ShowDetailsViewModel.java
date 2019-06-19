package com.mirza.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirza.tvshowtime.app.App;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.repository.TvShowRepository;

public class ShowDetailsViewModel extends AndroidViewModel {

    private LiveData<Show> showInfo;
    private TvShowRepository repository;

    public ShowDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public void fetchShowInfo(int showId){
        repository.fetchShowInfoById(showId);
    }

    public LiveData<Show> getShowInfo(){
        showInfo = repository.getShowInfoLiveData();
        return showInfo;
    }

    public void fetchShowEpisodes(int showId){
        repository.fetchShowSeasonsAndEpisodesList(showId);
    }


    public void fetchCast(int showId) {repository.fetchShowCast(showId);}

    public void destroyData(){
        repository.removeShowDetailsData();
    }

    public boolean checkIfSeriesIsAdded(int showId){
        return repository.checkIfSeriesIsAdded(showId);
    }
}
