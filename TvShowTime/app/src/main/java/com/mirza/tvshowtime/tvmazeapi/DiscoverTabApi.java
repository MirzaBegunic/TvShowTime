package com.mirza.tvshowtime.tvmazeapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiscoverTabApi {
    @GET("updates/shows")
    Call<String> getUpdatesJson();

    @GET("updates/shows")
    Call<String> getUpdatesTimeStamps();
}
