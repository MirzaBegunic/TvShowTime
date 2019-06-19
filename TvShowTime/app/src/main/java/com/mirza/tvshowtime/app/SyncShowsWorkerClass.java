package com.mirza.tvshowtime.app;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.mirza.tvshowtime.database.Episodes;
import com.mirza.tvshowtime.database.EpisodesDao;
import com.mirza.tvshowtime.database.ImageLinks;
import com.mirza.tvshowtime.database.Seasons;
import com.mirza.tvshowtime.database.SeasonsDao;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.database.ShowDao;
import com.mirza.tvshowtime.database.TvShowsDatabase;
import com.mirza.tvshowtime.tvmazeapi.DiscoverTabApi;
import com.mirza.tvshowtime.tvmazeapi.TvMazeApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SyncShowsWorkerClass extends Worker {

    public SyncShowsWorkerClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try{
            Log.d("sync", "doWork: syncing");
            Context context = getApplicationContext();
            TvShowsDatabase tvShowsDatabase= TvShowsDatabase.getInstance(context);
            ShowDao showDao = tvShowsDatabase.showDao();
            SeasonsDao seasonsDao = tvShowsDatabase.seasonsDao();
            EpisodesDao episodesDao = tvShowsDatabase.episodesDao();
            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl("http://api.tvmaze.com")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            DiscoverTabApi discoverTabApi = retrofit2.create(DiscoverTabApi.class);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.tvmaze.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TvMazeApi tvMazeApi = retrofit.create(TvMazeApi.class);
            Call<String> call = discoverTabApi.getUpdatesTimeStamps();
            Response<String> response = call.execute();
            String timestampsJson = response.body();
            Type mapType = new TypeToken<Map<Integer,Long>>(){}.getType();
            Gson gson = new Gson();
            Map<Integer,Long> mapOfTimestamps = gson.fromJson(timestampsJson,mapType);
            List<Integer> listOfShowIds = showDao.getShowsIds();
            for (Integer id:listOfShowIds) {
                Long restTimeStamp = mapOfTimestamps.get(id);
                Long databaseTimeStamp = showDao.getShowsUpdatedTimeStamp(id);
                if(restTimeStamp>databaseTimeStamp){
                    Call<Show> showCall = tvMazeApi.getShowById(id);
                    Call<List<Episodes>> episodesCall = tvMazeApi.getShowsEpisodes(id);
                    Call<List<Seasons>> seasonsCall = tvMazeApi.getShowSeasons(id);
                    Response<Show> showResponse = showCall.execute();
                    Response<List<Episodes>> episodesResponse = episodesCall.execute();
                    Response<List<Seasons>> seasonsResponse = seasonsCall.execute();
                    Show newShowData = showResponse.body();
                    List<Episodes> newEpisodesData = episodesResponse.body();
                    List<Seasons> newSeasonsData = seasonsResponse.body();
                    List<Episodes> oldEpisodeData = episodesDao.getEpisodesFromShowIdNL(id);
                    List<Seasons> oldSeasonsData = seasonsDao.getSeasons(id);
                    if(newShowData!=null){
                        showDao.updateShowTable(System.currentTimeMillis(),newShowData.getImageUrl().getUrlSmall(),newShowData.getImageUrl().getUrlLarge(),id);
                    }
                    if(newSeasonsData!=null && oldSeasonsData!=null){
                        if(newSeasonsData.size()>oldSeasonsData.size()){
                            for(int i=oldSeasonsData.size();i<newSeasonsData.size();++i){
                                Seasons season = newSeasonsData.get(i);
                                season.setShowId(id);
                                season.setWatchedCount(0);
                                season.setSeenStatus(false);
                                seasonsDao.insert(newSeasonsData.get(i));
                            }
                        }
                        for (Seasons season:newSeasonsData) {
                            seasonsDao.update(season);
                        }
                    }
                    if(newEpisodesData!=null && oldEpisodeData!=null){
                        if(newEpisodesData.size()>oldEpisodeData.size()){
                            for(int i=oldEpisodeData.size();i<newEpisodesData.size();++i){
                                Episodes ep = newEpisodesData.get(i);
                                ep.setShowId(id);
                                ep.setSeenStatus(false);
                                ep.generateTimeStamp(ep.getAirDate());
                                episodesDao.insert(ep);
                            }
                        }
                        for (Episodes episode:newEpisodesData) {
                            episode.generateTimeStamp(episode.getAirDate());
                            if(episode.getImageUrl()==null){
                                episode.setImageUrl(new ImageLinks(" "," "));
                            }
                            episodesDao.updateEpisodesTable(episode.getEpisodeName(),episode.getAirDate(),episode.getSummary(),episode.getAirtimestamp(),episode.getImageUrl().getUrlSmall(),episode.getImageUrl().getUrlLarge(),id,episode.getEpisodeId());
                        }
                    }
                }
            }
            return Result.success();
        }catch (Exception e){
            return Result.failure();
        }
    }
}
