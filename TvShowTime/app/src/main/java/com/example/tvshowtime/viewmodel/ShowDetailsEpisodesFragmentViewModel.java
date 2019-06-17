package com.example.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.database.SeasonAndEpisodes;
import com.example.tvshowtime.repository.TvShowRepository;

import java.util.List;

public class ShowDetailsEpisodesFragmentViewModel extends AndroidViewModel {

    private LiveData<List<SeasonAndEpisodes>> list;
    TvShowRepository repository;

    public ShowDetailsEpisodesFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
        list = new MutableLiveData<>();
    }

    public void fetchData(int showId){
        repository.fetchShowSeasonsAndEpisodesList(showId);
    }

    public LiveData<List<SeasonAndEpisodes>> getList(){
        list = repository.getShowSeasonsAndEpisodes();
        return list;
    }

    public LiveData<List<Episodes>> getShowEpisodes(int showId){
        return repository.getShowEpisodes(showId);
    }

    public boolean checkIfSeriesIsAdded(int showId){
        return repository.checkIfSeriesIsAdded(showId);
    }

    public void setEpisodeAsWatched(int epId){
        repository.setEpisodeAsWatched(epId);
    }

    public void setEpisodeAsNotWatched(int epId){
        repository.setEpisodesAsNotWatched(epId);
    }

    public void setAllSeasonEpisodesAsWatched(int showId, int seasonNumber){
        repository.setAllSeasonEpisodesAsWatched(showId,seasonNumber);
    }
}
