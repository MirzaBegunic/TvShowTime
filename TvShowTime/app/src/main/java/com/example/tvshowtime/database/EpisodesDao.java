package com.example.tvshowtime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface EpisodesDao {
    @Insert
    void insert(Episodes episodes);

    @Delete
    void delete(Episodes episodes);

    @Update
    void update(Episodes episodes);

    @Query("SELECT * FROM episodes_table WHERE showId=:Id")
    LiveData<List<Episodes>> getEpisodesFromShowId(int Id);

    @Query("SELECT * FROM episodes_table WHERE showId=:Id")
    List<Episodes> getEpisodesFromShowIdNL(int Id);

    @Query("SELECT * FROM episodes_table WHERE showId=:Id AND seasonNumber=:Num")
    LiveData<List<Episodes>> getEpisodesBySeasonId(int Id, int Num);

    @Query("SELECT * FROM episodes_table WHERE showId=:Id AND seasonNumber=:Num")
    List<Episodes> getEpisodes(int Id, int Num);

    @Query("SELECT * FROM " + ShowAndEpisodes.VIEW_NAME + " WHERE " + "episodeAirTimeStamp>:timeStamp GROUP BY showId")
    LiveData<List<ShowAndEpisodes>> getUpcomingEpisodes(Long timeStamp);

    @Query("SELECT * FROM episodes_table WHERE episodeId=:id")
    LiveData<Episodes> getEpisodeFromId(int id);

    @Query("SELECT * FROM episodes_table")
    LiveData<List<Episodes>> getAllEpisodes();

    @Query("UPDATE episodes_table SET episodeSeenStatus = 1 WHERE episodeId=:epId")
    void setEpisodeAsSeen(int epId);

    @Query("UPDATE episodes_table SET episodeSeenStatus = 0 WHERE episodeId=:epId")
    void setEpisodeAsNotSeen(int epId);

    @Query("SELECT * FROM episodes_table WHERE showId=:Id AND episodeSeenStatus=0 ORDER BY episodeAirDate ASC LIMIT 1")
    Episodes getLastNotSeenEpisode(int Id);

    @Query("UPDATE episodes_table SET episodeSeenStatus=1 WHERE showId=:showId AND seasonNumber=:seasonNumber")
    void setAllSeasonEpisodesAsWatched(int showId, int seasonNumber);

    @Query("SELECT episodes_table.* FROM show_table LEFT JOIN episodes_table ON show_table.showId = episodes_table.showId WHERE showName=:showName AND episodeSeenStatus=0 AND episodeAirTimeStamp>:timeStamp LIMIT 1")
    Episodes searchUpcomingEpisodes(String showName, Long timeStamp);

    @Query("SELECT episodes_table.* FROM show_table LEFT JOIN episodes_table ON show_table.showId = episodes_table.showId WHERE showName=:showName AND episodeSeenStatus=0 AND episodeAirTimeStamp<:timeStamp LIMIT 1")
    Episodes searchWatchListShows(String showName, Long timeStamp);

    @Query("SELECT SUM(episodeRunTime) FROM episodes_table WHERE episodeSeenStatus=1")
    Long getTotalTime();

    @Query("SELECT COUNT(episodeId) FROM episodes_table WHERE episodeSeenStatus=1")
    Long getTotalEpisodesCount();

    @Query("SELECT SUM(episodeRunTime), show_table.* FROM show_table LEFT JOIN episodes_table ON show_table.showId = episodes_table.showId WHERE episodeSeenStatus=1 GROUP BY episodes_table.showId ORDER BY 1 DESC  LIMIT 1")
    Show getMostWatched();

    @Query("SELECT SUM(episodeRunTime) FROM show_table LEFT JOIN episodes_table ON show_table.showId = episodes_table.showId WHERE episodeSeenStatus=1 GROUP BY episodes_table.showId ORDER BY 1 DESC LIMIT 1")
    Long getMostWatchedSum();

    @Query("SELECT show_table.showId, show_table.showName, episodes_table.episodeName FROM show_table LEFT JOIN episodes_table ON show_table.showId=episodes_table.showId  WHERE episodeAirDate = :episodeAirDate")
    List<ShowAndEpisodesForNotification> getTodayEpisodesForNotification(String episodeAirDate);

    @Query("UPDATE episodes_table SET episodeName=:episodeName, episodeAirDate=:episodeAirDate,episodeSummary=:episodeSummary,episodeAirTimeStamp=:episodeAirTimeStamp,imageSmall=:imageSmall,imageLarge=:imageLarge WHERE showId=:showId AND episodeId = :episodeId")
    void updateEpisodesTable(String episodeName, String episodeAirDate, String episodeSummary, Long episodeAirTimeStamp, String imageSmall, String imageLarge, int showId, int episodeId);
}