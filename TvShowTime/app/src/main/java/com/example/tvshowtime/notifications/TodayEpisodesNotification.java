package com.example.tvshowtime.notifications;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.ShowAndEpisodesForNotification;

public class TodayEpisodesNotification {
    public static final String CHANEL_ID = "Today's Episodes Id";
    public static final String CHANNEL_NAME = "Today's Episodes ChannelName";

    public static void sendNotification(Context context, ShowAndEpisodesForNotification showAndEpisodes, Bitmap bitmap, int notificationID){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(R.color.colorMyPrimary);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,CHANEL_ID)
                .setContentTitle(showAndEpisodes.getShowName() + " is Airing Today")
                .setContentText("Episode: " +showAndEpisodes.getEpisodeName())
                .setSmallIcon(R.drawable.watched_episodes_tv)
                .setLargeIcon(bitmap);

        notificationManager.notify(notificationID,notificationBuilder.build());
    }
}
