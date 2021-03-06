package com.mirza.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirza.tvshowtime.app.App;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.repository.TvShowRepository;

public class ShowDetailsInfoFragmentViewModel extends AndroidViewModel {
    private TvShowRepository repository;
    private LiveData<Show> showInfo;

    public ShowDetailsInfoFragmentViewModel(@NonNull Application application) {
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
}
