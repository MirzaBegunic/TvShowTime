package com.example.tvshowtime.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshowtime.database.Cast;
import com.example.tvshowtime.database.CastDao;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.database.EpisodesDao;
import com.example.tvshowtime.database.Seasons;
import com.example.tvshowtime.database.SeasonsDao;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.database.ShowDao;
import com.example.tvshowtime.database.Shows;
import com.example.tvshowtime.database.ShowsDao;
import com.example.tvshowtime.database.TvShowsDatabase;
import com.example.tvshowtime.tvmazeapi.ShowJson;
import com.example.tvshowtime.tvmazeapi.TvMazeApi;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowRepository {
    private MutableLiveData<List<ShowJson>> search;
    private MutableLiveData<List<Shows>> listOfShows;
    private ShowDao showDao;
    private ShowsDao showsDao;
    private SeasonsDao seasonsDao;
    private EpisodesDao episodesDao;
    private CastDao castDao;
    private TvMazeApi tvMazeApi;
    private Retrofit retrofit;
    AppExecutors appExecutorsInstance;

    public TvShowRepository(Application application){
        TvShowsDatabase tvShowsDatabase= TvShowsDatabase.getInstance(application);
        showsDao = tvShowsDatabase.showsDao();
        showDao = tvShowsDatabase.showDao();
        seasonsDao = tvShowsDatabase.seasonsDao();
        episodesDao = tvShowsDatabase.episodesDao();
        castDao = tvShowsDatabase.castDao();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tvMazeApi = retrofit.create(TvMazeApi.class);
        appExecutorsInstance =  AppExecutors.getInstance();
    }

    public void searchSeries(String query){
        Call<List<ShowJson>> call = tvMazeApi.getSearchedListShow(query);
        if(search==null){
            search = new MutableLiveData<>();
        }
        call.enqueue(new Callback<List<ShowJson>>() {
            @Override
            public void onResponse(Call<List<ShowJson>> call, Response<List<ShowJson>> response) {
                if(!response.isSuccessful()){
                    Log.d("TVSHOWREPO","Response not successful");
                }else{
                    search.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<ShowJson>> call, Throwable t) {
                Log.d("TVSHOWREPO","Response failed");
            }
        });
    }

    public LiveData<List<ShowJson>> getSearchSeries(){
        return search;
    }

    public void insertSeriesInDatabase(final int showId ){
        appExecutorsInstance.diskAndNetworkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Shows showsCheck = showsDao.getShowById2(showId);
                if(showsCheck != null){
                    //TODO: Return to main ui that show has been added
                }else{
                    Shows shows = null;
                    Show show = null;
                    List<Seasons> seasonsList = null;
                    List<Episodes> episodesList = null;
                    List<Cast> castList = null;
                    Call<Show> showCall = tvMazeApi.getShowById(showId);
                    Call<List<Seasons>> seasonsCall = tvMazeApi.getShowSeasons(showId);
                    Call<List<Episodes>> episodesCall = tvMazeApi.getShowsEpisodes(showId);
                    Call<List<Cast>> castCall = tvMazeApi.getShowCast(showId);
                    try {
                        Response<Show> response = showCall.execute();
                        shows = new Shows(response.body().getShowId(),response.body().getShowName());
                        show = response.body();
                        Response<List<Seasons>> response1 = seasonsCall.execute();
                        seasonsList = response1.body();
                        Response<List<Episodes>> response2 = episodesCall.execute();
                        episodesList = response2.body();
                        Response<List<Cast>> response3 = castCall.execute();
                        castList = response3.body();
                    } catch (IOException e) {
                        //TODO : Make toast message on activity UI
                        e.printStackTrace();
                    }
                    if(show!=null && show!=null && seasonsList!= null && episodesList!=null && castList!=null){
                        showsDao.insert(shows);
                        showDao.insert(show);
                        for (Seasons season: seasonsList
                        ) {
                            season.setShowId(showId);
                            season.setWatchedCount(0);
                            season.setSeenStatus(false);
                            seasonsDao.insert(season);
                        }
                        for (Episodes episodes:episodesList
                        ) {
                            episodes.setShowId(showId);
                            episodes.setSeenStatus(false);
                            episodesDao.insert(episodes);
                        }
                        for (Cast cast:castList
                        ) {
                            cast.setShowId(showId);
                            castDao.insert(cast);
                        }
                    }else{
                        //TODO : Make toast message on activity UI
                    }
                }
                }
        });
    }
}
