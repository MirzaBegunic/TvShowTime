package com.mirza.tvshowtime.tvmazeapi;

import android.util.Log;

import com.mirza.tvshowtime.database.Cast;
import com.mirza.tvshowtime.database.Episodes;
import com.mirza.tvshowtime.database.Seasons;
import com.mirza.tvshowtime.database.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiTester {
    public static final String LOGD = "ApiTest";

    public void getSearchedListShow(TvMazeApi tvMazeApi,String query){
        Call<List<ShowJson>> showsList = tvMazeApi.getSearchedListShow(query);
        showsList.enqueue(new Callback<List<ShowJson>>() {
            @Override
            public void onResponse(Call<List<ShowJson>> call, Response<List<ShowJson>> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successfull");
                }
                List<ShowJson> showList = response.body();
                Log.d(LOGD,"search show successfull");
            }

            @Override
            public void onFailure(Call<List<ShowJson>> call, Throwable t) {
                Log.d(LOGD,"search show failed");
            }
        });
    }

    public void getSearchedShow(TvMazeApi tvMazeApi, String query){
        Call<Show> showJsonCall = tvMazeApi.getSearchedShow(query);
        showJsonCall.enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successfull");
                }
                Show showJson = response.body();
                Log.d(LOGD,"search show successfull");
            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                Log.d(LOGD,"search show failed");
            }
        });
    }

    public void getShowById(TvMazeApi tvMazeApi, int showId){
        Call<Show> showCall = tvMazeApi.getShowById(showId);
        showCall.enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successfull");
                }
                Show show = response.body();
                Log.d(LOGD,"search show successfull");
            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                Log.d(LOGD,"search show failed");
            }
        });
    }

    public void getShowsEpisodes(TvMazeApi tvMazeApi, int showId){
        Call<List<Episodes>> listEpisodes = tvMazeApi.getShowsEpisodes(showId);
        listEpisodes.enqueue(new Callback<List<Episodes>>() {
            @Override
            public void onResponse(Call<List<Episodes>> call, Response<List<Episodes>> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successfull");
                }
                List<Episodes> episodes = response.body();
                Log.d(LOGD,"search show successfull");
            }

            @Override
            public void onFailure(Call<List<Episodes>> call, Throwable t) {
                Log.d(LOGD,"search show not successfull");
            }
        });
    }

    public void getShowEpisode(TvMazeApi tvMazeApi,int id, int seasonId, int episodeId){
        Call<Episodes> episode = tvMazeApi.getShowEpisode(id,seasonId,episodeId);
        episode.enqueue(new Callback<Episodes>() {
            @Override
            public void onResponse(Call<Episodes> call, Response<Episodes> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successfull");
                }
                Episodes episodes = response.body();
                Log.d(LOGD,"search show successfull");
            }

            @Override
            public void onFailure(Call<Episodes> call, Throwable t) {
                Log.d(LOGD,"search show failed");
            }
        });
    }

    public void getShowSeasons(TvMazeApi tvMazeApi, int showId){
        Call<List<Seasons>> call = tvMazeApi.getShowSeasons(showId);
        call.enqueue(new Callback<List<Seasons>>() {
            @Override
            public void onResponse(Call<List<Seasons>> call, Response<List<Seasons>> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successful");
                }
                List<Seasons> seasonsList = response.body();
                Log.d(LOGD,"search show successful");
            }

            @Override
            public void onFailure(Call<List<Seasons>> call, Throwable t) {
                Log.d(LOGD,"search show failed");
            }
        });
    }

    public void getSeasonEpisodes(TvMazeApi tvMazeApi,int seasonId){
        Call<List<Episodes>> call = tvMazeApi.getSeasonEpisodes(seasonId);
        call.enqueue(new Callback<List<Episodes>>() {
            @Override
            public void onResponse(Call<List<Episodes>> call, Response<List<Episodes>> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successful");
                }
                List<Episodes> episodesList = response.body();
                Log.d(LOGD,"search show success");
            }

            @Override
            public void onFailure(Call<List<Episodes>> call, Throwable t) {
                Log.d(LOGD,"search show failed");
            }
        });
    }

    public void getShowCast(TvMazeApi tvMazeApi, int showId){
        Call<List<Cast>> call = tvMazeApi.getShowCast(showId);
        call.enqueue(new Callback<List<Cast>>() {
            @Override
            public void onResponse(Call<List<Cast>> call, Response<List<Cast>> response) {
                if(!response.isSuccessful()){
                    Log.d(LOGD,"search show not successful");
                }
                List<Cast> castList = response.body();
                Log.d(LOGD,"search show successful");

            }

            @Override
            public void onFailure(Call<List<Cast>> call, Throwable t) {
                Log.d(LOGD,"search show failed");

            }
        });
    }
}
