package com.example.tvshowtime;

import com.example.tvshowtime.database.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TvRestApi {

    @GET("shows/80")
    Call<Show> getShow();

}
