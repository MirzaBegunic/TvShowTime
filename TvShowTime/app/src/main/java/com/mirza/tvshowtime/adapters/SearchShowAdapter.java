package com.mirza.tvshowtime.adapters;

import android.content.Context;
import android.util.Log;
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
import com.mirza.tvshowtime.tvmazeapi.ShowJson;

import java.util.List;

public class SearchShowAdapter extends RecyclerView.Adapter<SearchShowAdapter.SearchShowViewHolder>  {

    public interface SearchShowAdapterOnClickListener{
        void onClick(Show s);
    }

    private List<ShowJson> shows;
    private LayoutInflater inflater;
    private SearchShowAdapterOnClickListener onClickListener;



    public SearchShowAdapter(Context context, List<ShowJson> shows, SearchShowAdapterOnClickListener listener){
        inflater = LayoutInflater.from(context);
        this.shows = shows;
        this.onClickListener = listener;
    }

    public void setShows(List<ShowJson> shows){
        this.shows = shows;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_search_show_layout,parent,false);
        return new SearchShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchShowViewHolder holder, int position) {
        Log.d("binding", "onBindViewHolder: " + position);
        final Show show = shows.get(position).getShow();
        if(show.getShowName() != null)
            holder.showName.setText(show.getShowName());
        if(show.getRating()!= null)
            holder.showRating.setText(Double.toString(show.getRating().getAverage()));
        if(show.getImageUrl()!= null && show.getImageUrl().getUrlSmall() != null)
            Glide.with(holder.view).load(show.getImageUrl().getUrlSmall()).into(holder.showImage);
        else
            Glide.with(holder.view).load(R.drawable.error).into(holder.showImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null) {
                    onClickListener.onClick(show);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public class SearchShowViewHolder extends RecyclerView.ViewHolder {
        private TextView showName;
        private TextView showRating;
        private ImageView showImage;
        private View view;

        public SearchShowViewHolder(@NonNull View itemView) {
            super(itemView);
            showImage = itemView.findViewById(R.id.searchShowImageView);
            showName = itemView.findViewById(R.id.searchShowShowName);
            showRating = itemView.findViewById(R.id.searchShowRating);
            view = itemView;
        }

    }
}
