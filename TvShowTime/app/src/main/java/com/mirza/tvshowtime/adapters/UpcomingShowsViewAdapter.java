package com.mirza.tvshowtime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.database.ShowAndEpisodes;

import java.util.List;

public class UpcomingShowsViewAdapter extends RecyclerView.Adapter<UpcomingShowsViewAdapter.UpcomingShowViewHolder> {
    List<ShowAndEpisodes> episodes;
    UpcomingClickListener listener;
    LayoutInflater inflater;

    public interface UpcomingClickListener{
        void onClick(ShowAndEpisodes episodes);
    }

    public UpcomingShowsViewAdapter(Context context, List<ShowAndEpisodes> episodes, UpcomingClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.episodes = episodes;
        this.listener = listener;
    }

    public void setEpisodes(List<ShowAndEpisodes> episodes){
        this.episodes = episodes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UpcomingShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_main_myshows_tab_upcoming_viewholder,parent,false);
        return new UpcomingShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingShowViewHolder holder, int position) {
        final ShowAndEpisodes episode = episodes.get(position);
        if(episode.getImageUrl()!=null && episode.getImageUrl().getUrlLarge()!=null)
            Glide.with(holder.itemView).load(episode.getImageUrl().getUrlLarge()).into(holder.episodeImage);
        else
            Glide.with(holder.itemView).load(R.drawable.error).into(holder.episodeImage);
        if(episode.getShowName()!=null){
            holder.showName.setText(episode.getShowName());
        }
        holder.episodeNumber.setText("E" + String.valueOf(episode.getEpisodeNumber()));
        holder.seasonNumber.setText("S" + String.valueOf(episode.getSeasonNumber()));
        if(episode.getEpisodeName()!=null)
            holder.episodeName.setText(episode.getEpisodeName());
        if(episode.getEpisodeAirDate()!=null)
            holder.airDate.setText(episode.getEpisodeAirDate());
        if(listener!=null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(episode);
                }
            });
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    class UpcomingShowViewHolder extends RecyclerView.ViewHolder{

        ImageView episodeImage;
        TextView episodeNumber;
        TextView seasonNumber;
        TextView airDate;
        TextView episodeName;
        TextView showName;

        public UpcomingShowViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeImage = itemView.findViewById(R.id.upcomingShowImage);
            episodeNumber = itemView.findViewById(R.id.upcomingENmbr);
            seasonNumber = itemView.findViewById(R.id.upcomingSNmbr);
            airDate = itemView.findViewById(R.id.upcomingShowDate);
            episodeName = itemView.findViewById(R.id.upcomingShowEpisodeName);
            showName = itemView.findViewById(R.id.upcomingShowName);
        }
    }

}
