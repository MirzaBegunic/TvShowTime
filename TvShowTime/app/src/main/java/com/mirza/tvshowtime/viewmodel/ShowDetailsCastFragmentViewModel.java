package com.mirza.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirza.tvshowtime.app.App;
import com.mirza.tvshowtime.database.Cast;
import com.mirza.tvshowtime.repository.TvShowRepository;

import java.util.List;

public class ShowDetailsCastFragmentViewModel extends AndroidViewModel {
    private LiveData<List<Cast>> showCast;
    private TvShowRepository repository;

    public ShowDetailsCastFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }

    public void fetchShowCast(int showId){
        repository.fetchShowCast(showId);
    }

    public LiveData<List<Cast>> getShowCast(){
        showCast = repository.getShowCast();
        return showCast;
    }
}
