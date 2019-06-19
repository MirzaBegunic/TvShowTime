package com.mirza.tvshowtime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirza.tvshowtime.app.App;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.repository.TvShowRepository;

import java.util.List;

public class DiscoverTabViewModel extends AndroidViewModel {
    private TvShowRepository repository;
    private LiveData<List<Show>> liveDiscoverShow;

    public DiscoverTabViewModel(@NonNull Application application) {
        super(application);
        repository = App.getInstance().getTvShowRepository();
    }
    /**Called to get shows for discover tab first time the fragment is created*/
    public LiveData<List<Show>> getDiscoverShow(){
        liveDiscoverShow = repository.getDiscoverData();
        return liveDiscoverShow;
    }

}
