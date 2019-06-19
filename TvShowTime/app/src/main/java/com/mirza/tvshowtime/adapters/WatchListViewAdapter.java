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
import com.mirza.tvshowtime.database.Show;

import java.util.List;

public class WatchListViewAdapter extends RecyclerView.Adapter<WatchListViewAdapter.WatchListShowViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Show> watchListShow;
    private onClicWatchListShow listener;

    public interface onClicWatchListShow{
        void onClick(Show show);
    }

    public WatchListViewAdapter(Context context,List<Show> watchListShow, onClicWatchListShow listener){
        this.watchListShow = watchListShow;
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public void setWatchListShow(List<Show> watchListShow) {
        this.watchListShow = watchListShow;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WatchListShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_main_myshows_tab_watchlist_viewholder,parent,false);
        return new WatchListShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListShowViewHolder holder, int position) {
        final Show show = watchListShow.get(position);
        if(show.getShowName() != null)
            holder.showName.setText(show.getShowName());
        if(show.getImageUrl()!=null && show.getImageUrl().getUrlLarge()!=null)
            Glide.with(holder.itemView).load(show.getImageUrl().getUrlLarge()).into(holder.showImage);
        else
            Glide.with(holder.itemView).load(R.drawable.error).into(holder.showImage);
        if(listener!=null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(show);
                }
            });
    }

    @Override
    public int getItemCount() {
        return watchListShow.size();
    }

    class WatchListShowViewHolder extends RecyclerView.ViewHolder{

        private ImageView showImage;
        private TextView showName;

        public WatchListShowViewHolder(@NonNull View itemView) {
            super(itemView);
            showImage = itemView.findViewById(R.id.watchListShowImage);
            showName = itemView.findViewById(R.id.watchListShowName);
        }
    }
}
