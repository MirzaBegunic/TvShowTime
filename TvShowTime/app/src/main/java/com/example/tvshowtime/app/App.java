package com.example.tvshowtime.app;

import android.app.Application;

import com.example.tvshowtime.repository.TvShowRepository;

public class App extends Application {
    private static App sInstance;
    private TvShowRepository tvShowRepository;

    @Override
    public void onCreate() {
        sInstance = this;
        tvShowRepository = new TvShowRepository(this);
        tvShowRepository.setDiscoverTabShows();
        super.onCreate();
    }

    public static App getInstance() {
        return sInstance;
    }

    public TvShowRepository getTvShowRepository(){
        return tvShowRepository;
    }
}
