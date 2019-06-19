package com.mirza.tvshowtime.app;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.database.EpisodesDao;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.database.ShowAndEpisodesForNotification;
import com.mirza.tvshowtime.database.ShowDao;
import com.mirza.tvshowtime.database.TvShowsDatabase;
import com.mirza.tvshowtime.notifications.TodayEpisodesNotification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodayEpisodesWorkerClass extends Worker {


    public TodayEpisodesWorkerClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Context context = getApplicationContext();
            TvShowsDatabase database = TvShowsDatabase.getInstance(context);
            EpisodesDao episodesDao = database.episodesDao();
            ShowDao showDao = database.showDao();
            Long timestamp = System.currentTimeMillis();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(timestamp);
            String dateFormat = format.format(date);
            List<ShowAndEpisodesForNotification> listNotificationData = episodesDao.getTodayEpisodesForNotification(dateFormat);
            int i=312;
            if(listNotificationData != null){
                for (ShowAndEpisodesForNotification data: listNotificationData) {
                    Show show = showDao.getShowById2(data.getShowId());
                    Bitmap bitmap;
                    if(show.getImageUrl()!=null && show.getImageUrl().getUrlSmall()!=null){
                        bitmap = Glide.with(context).asBitmap().load(show.getImageUrl().getUrlSmall()).apply(RequestOptions.circleCropTransform()).into(100,100).get();
                    }else{
                        bitmap = Glide.with(context).asBitmap().load(R.drawable.error).apply(RequestOptions.circleCropTransform()).into(100,100).get();
                    }
                    if(data.getShowName()==null && data.getShowName().isEmpty())
                        data.setShowName("Unknown Show Name");
                    if(data.getEpisodeName()==null && data.getEpisodeName().isEmpty())
                        data.setEpisodeName("Unknown Episode Name");
                    TodayEpisodesNotification.sendNotification(context,data,bitmap,i);
                    ++i;
                }
            }
            return Result.success();
        }catch (Exception e){
            return Result.failure();
        }
    }
}
