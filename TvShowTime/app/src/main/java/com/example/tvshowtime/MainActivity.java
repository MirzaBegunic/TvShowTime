package com.example.tvshowtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.tvshowtime.database.Show;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TvRestApi tvRestApi;
    private Show show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tvRestApi = retrofit.create(TvRestApi.class);
        getShows();
        Log.d("REST","finished");

    }

    private void getShows(){
        Call<Show> call = tvRestApi.getShow();
        call.enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                if(!response.isSuccessful()){
                    Log.d("REST","response not succesfull");
                }
                Show show = response.body();
                Log.d("REST","success");

            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                String message = t.getMessage();
                Log.d("REST",message);
            }
        });
    }
}
