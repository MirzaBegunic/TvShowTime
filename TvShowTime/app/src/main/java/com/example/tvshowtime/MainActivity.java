package com.example.tvshowtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.repository.TvShowRepository;
import com.example.tvshowtime.tvmazeapi.ApiTester;
import com.example.tvshowtime.tvmazeapi.ShowJson;
import com.example.tvshowtime.tvmazeapi.TvMazeApi;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TvMazeApi tvMazeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tvMazeApi = retrofit.create(TvMazeApi.class);
        TvShowRepository repository = new TvShowRepository(getApplication());
    }
}
