package com.example.tvshowtime.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshowtime.app.AppExecutors;
import com.example.tvshowtime.database.Cast;
import com.example.tvshowtime.database.CastDao;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.database.EpisodesDao;
import com.example.tvshowtime.database.SeasonAndEpisodes;
import com.example.tvshowtime.database.Seasons;
import com.example.tvshowtime.database.SeasonsDao;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.database.ShowDao;
import com.example.tvshowtime.database.Shows;
import com.example.tvshowtime.database.ShowsDao;
import com.example.tvshowtime.database.TvShowsDatabase;
import com.example.tvshowtime.tvmazeapi.DiscoverTabApi;
import com.example.tvshowtime.tvmazeapi.ShowJson;
import com.example.tvshowtime.tvmazeapi.TvMazeApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TvShowRepository {
    private static final String TAG = TvShowRepository.class.getSimpleName();
    private ShowDao showDao;
    private ShowsDao showsDao;
    private SeasonsDao seasonsDao;
    private EpisodesDao episodesDao;
    private CastDao castDao;
    private TvMazeApi tvMazeApi;
    private DiscoverTabApi discoverTabApi;
    private Retrofit retrofit;
    private Retrofit retrofit2;
    private AppExecutors appExecutorsInstance;
    private MutableLiveData<List<ShowJson>> search;
    private MutableLiveData<List<Show>> showsForDiscoverTab;
    private MutableLiveData<Show> showInfoLiveData;
    private MutableLiveData<List<SeasonAndEpisodes>> showSeasonsAndEpisodesList;
    private MutableLiveData<List<Cast>> showCast;
    private MutableLiveData<List<ShowJson>> searchShows;


    public TvShowRepository(Application application){

        TvShowsDatabase tvShowsDatabase= TvShowsDatabase.getInstance(application);
        showsDao = tvShowsDatabase.showsDao();
        showDao = tvShowsDatabase.showDao();
        seasonsDao = tvShowsDatabase.seasonsDao();
        episodesDao = tvShowsDatabase.episodesDao();
        castDao = tvShowsDatabase.castDao();

        appExecutorsInstance = AppExecutors.getInstance();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tvMazeApi = retrofit.create(TvMazeApi.class);

        retrofit2 = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        discoverTabApi = retrofit2.create(DiscoverTabApi.class);

    }
    /**Search series by word*/
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
    /**Get searched series by word*/
    public LiveData<List<ShowJson>> getSearchSeries(){
        return search;
    }
    /**Insert Series in db by Id */
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
    /**Called when app is first time launched, to download list of latest shows */
    public void setDiscoverTabShows(){
        if(showsForDiscoverTab==null){
            showsForDiscoverTab = new MutableLiveData<>();
        }
        appExecutorsInstance.diskAndNetworkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                SortedMap<Long, Integer> mapOfShows = new TreeMap<>();
                Future<SortedMap<Long,Integer>> future = appExecutorsInstance.networkExecutor().submit(new Callable<SortedMap<Long,Integer>>() {
                    @Override
                    public SortedMap<Long,Integer> call() throws Exception {
                        SortedMap<Long, Integer> sortedMap = new TreeMap<>(Collections.<Long>reverseOrder());
                        try{
                            Call<String> call = discoverTabApi.getUpdatesJson();
                            Response<String> response = call.execute();
                            String jsonStringResponse = response.body();
                            Type mapType = new TypeToken<Map<Integer, Long>>(){}.getType();
                            Gson gson = new Gson();
                            Map<Integer, Long > updatesMap = gson.fromJson(jsonStringResponse,mapType);
                            for (Map.Entry<Integer,Long> entry: updatesMap.entrySet()) {
                                sortedMap.put(entry.getValue(),entry.getKey());
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return sortedMap;
                    }
                });
                try {
                    mapOfShows = future.get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                List<Show> listForLiveData = new ArrayList<>();
                int i = 0;
                for(SortedMap.Entry<Long, Integer> entry: mapOfShows.entrySet()) {
                    if (i > 50) {
                        break;
                    } else {
                        ++i;
                        final Integer id = entry.getValue();
                        Future<Show> showFuture = appExecutorsInstance.diskExecutor().submit(new Callable<Show>() {
                            @Override
                            public Show call() throws Exception {
                                Call<Show> call = tvMazeApi.getShowById(id);
                                Response<Show> response = call.execute();
                                Show show = response.body();
                                return show;
                            }
                        });
                        try {
                            listForLiveData.add(showFuture.get());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    showsForDiscoverTab.postValue(listForLiveData);
                }
            }
        });
    }
    /**Called to get LiveData to fragment*/
    public LiveData<List<Show>> getDiscoverData(){ return showsForDiscoverTab; }

    public void fetchShowInfoById(int showId){
        if(showInfoLiveData==null){
            showInfoLiveData = new MutableLiveData<>();
        }
        Call<Show> call = tvMazeApi.getShowById(showId);
        call.enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                Show show = response.body();
                showInfoLiveData.postValue(show);
            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {

            }
        });
    }

    public LiveData<Show> getShowInfoLiveData(){
        if(showInfoLiveData==null){
            showInfoLiveData = new MutableLiveData<>();
        }
        return showInfoLiveData;
    }

    public void fetchShowSeasonsAndEpisodesList(final int showId){
        if(showSeasonsAndEpisodesList==null)
            showSeasonsAndEpisodesList = new MutableLiveData<>();
        appExecutorsInstance.diskAndNetworkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<SeasonAndEpisodes> list = new ArrayList<>();
                Call<List<Seasons>> call = tvMazeApi.getShowSeasons(showId);
                Response<List<Seasons>> response;
                try {
                    response= call.execute();
                } catch (IOException e) {
                    response = null;
                }
                if(response!=null){
                    List<Seasons> seasonsList = response.body();
                    for (Seasons season: seasonsList) {
                        String name = "Season " + season.getSeasonNumber();
                        Call<List<Episodes>> call2 = tvMazeApi.getSeasonEpisodes(season.getSeasonId());
                        Response<List<Episodes>> response2;
                        try {
                            response2 = call2.execute();
                        } catch (IOException e) {
                            response2 = null;
                        }
                        if(response2!=null){
                            List<Episodes> episodesList = response2.body();
                            list.add(new SeasonAndEpisodes(name,episodesList));
                        }
                    }
                }
                showSeasonsAndEpisodesList.postValue(list);
            }
        });
    }

    public LiveData<List<SeasonAndEpisodes>> getShowSeasonsAndEpisodes(){
        if(showSeasonsAndEpisodesList==null){
            showSeasonsAndEpisodesList = new MutableLiveData<>();
        }
        return showSeasonsAndEpisodesList;
    }

    public void fetchShowCast(final int showId){
        if(showCast == null){
            showCast = new MutableLiveData<>();
        }
        appExecutorsInstance.networkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                {
                    try {
                        Call<List<Cast>> call = tvMazeApi.getShowCast(showId);
                        Response<List<Cast>> response = call.execute();
                        List<Cast> cast = response.body();
                        showCast.postValue(cast);
                    }catch (Exception e){

                    }
                }
            }
        });
    }

    public LiveData<List<Cast>> getShowCast(){
        if (showCast == null){
            showCast = new MutableLiveData<>();
        }
        return showCast;
    }

    public void searchShows(String query){
        if(searchShows==null)
            searchShows = new MutableLiveData<>();
        Call<List<ShowJson>> call = tvMazeApi.getSearchedListShow(query);
        call.enqueue(new Callback<List<ShowJson>>() {
            @Override
            public void onResponse(Call<List<ShowJson>> call, Response<List<ShowJson>> response) {
                searchShows.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ShowJson>> call, Throwable t) {
            }
        });
    }

    public LiveData<List<ShowJson>> getSearchedShows(){
        if(searchShows == null)
            searchShows = new MutableLiveData<>();
        return searchShows;
    }
}
