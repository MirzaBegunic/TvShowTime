package com.mirza.tvshowtime.tvmazeapi;

import com.mirza.tvshowtime.database.Cast;
import com.mirza.tvshowtime.database.Episodes;
import com.mirza.tvshowtime.database.Seasons;
import com.mirza.tvshowtime.database.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvMazeApi {


    /**
     * Gets list of searched shows
     * @param showName String (Query for search)
     * @return List<ShowJson> which contains Show object
     */
    @GET("search/shows")
    Call<List<ShowJson>> getSearchedListShow(@Query("q") String showName);

    /**
     * Gets single show
     * @param showName String (Query for search)
     * @return Show object
     */
    @GET("singlesearch/shows")
    Call<Show> getSearchedShow(@Query("q") String showName);

    /**
     * Gets show by showId
     * @param showId
     * @return Show object
     */
    @GET("shows/{id}")
    Call<Show> getShowById(@Path("id") int showId);

    /**
     * Gets all episodes from show
     * @param showId
     * @return List<Episodes>
     */
    @GET("shows/{id}/episodes")
    Call<List<Episodes>> getShowsEpisodes(@Path("id") int showId);

    /**
     * Gets episode from show by shows season and episode number
     * @param showId
     * @param seasonNmbr
     * @param episodeNmbr
     * @return Episode
     */
    @GET("shows/{id}/episodebynumber")
    Call<Episodes> getShowEpisode(@Path("id") int showId,@Query("season") int seasonNmbr,@Query("number") int episodeNmbr);

    /**
     * Gets list of shows seasons
     * @param showId
     * @return List<Seasons>
     */
    @GET("shows/{id}/seasons")
    Call<List<Seasons>> getShowSeasons(@Path("id") int showId);

    /**
     * Gets all episodes from season
     * @param seasonId
     * @return List<Episodes>
     */
    @GET("seasons/{seasonId}/episodes")
    Call<List<Episodes>> getSeasonEpisodes(@Path("seasonId") int seasonId);

    /**
     * Gets All cast from show
     * @param showId
     * @return List<Cast>
     */
    @GET("shows/{id}/cast")
    Call<List<Cast>> getShowCast(@Path("id") int showId);
}
