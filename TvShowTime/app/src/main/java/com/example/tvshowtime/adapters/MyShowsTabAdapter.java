package com.example.tvshowtime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Show;

import java.util.List;

public class MyShowsTabAdapter extends RecyclerView.Adapter<MyShowsTabAdapter.showViewHolder>{

    private List<Show> shows;
    private onClickMyShows listener;
    private LayoutInflater inflater;

    public interface onClickMyShows{
        void onClick(Show show);
    }


    public MyShowsTabAdapter(Context context, List<Show> shows, onClickMyShows listener){
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.shows = shows;
    }

    @NonNull
    @Override
    public showViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.activity_main_mysows_tab_holder,parent,false);
        return new showViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showViewHolder holder, int position) {
        final Show show = shows.get(position);
        if(show.getShowName()!= null){
            holder.showTitle.setText(show.getShowName());
        }
        if(show.getImageUrl()!= null && show.getImageUrl().getUrlLarge()!=null){
            Glide.with(holder.itemView).load(show.getImageUrl().getUrlLarge()).into(holder.showImage);
        }else{
            Glide.with(holder.itemView).load(R.drawable.error).into(holder.showImage);
        }
        if(listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(show);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public void setShows(List<Show> shows){
        this.shows = shows;
        notifyDataSetChanged();
    }

    public class showViewHolder extends RecyclerView.ViewHolder {
        private ImageView showImage;
        private TextView showTitle;

        public showViewHolder(@NonNull View itemView) {
            super(itemView);
            showImage = itemView.findViewById(R.id.myShowsImage);
            showTitle = itemView.findViewById(R.id.myShowsName);
        }
    }
}
