package com.example.tvshowtime.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.activities.ShowDetails;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.tvmazeapi.ShowJson;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class SearchShowAdapter extends RecyclerView.Adapter<SearchShowAdapter.SearchShowViewHolder>  {

    private List<ShowJson> shows;
    private LayoutInflater inflater;
    public static final String INTENT_EXTRA = "intentExtra";


    public SearchShowAdapter(Context context, List<ShowJson> shows){
        inflater = LayoutInflater.from(context);
        this.shows = shows;
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
        Show show = shows.get(position).getShow();
        if(show.getShowName() != null)
            holder.showName.setText(show.getShowName());
        if(show.getRating()!= null)
            holder.showRating.setText(Double.toString(show.getRating().getAverage()));
        if(show.getImageUrl()!= null && show.getImageUrl().getUrlSmall() != null)
            Glide.with(holder.view).load(show.getImageUrl().getUrlSmall()).into(holder.showImage);
        else
            Glide.with(holder.view).load(R.drawable.error).into(holder.showImage);
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public class SearchShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Show show = shows.get(position).getShow();
            Intent intent = new Intent(v.getContext(), ShowDetails.class);
            intent.putExtra(INTENT_EXTRA,show.getShowId());
            startActivity(v.getContext(),intent,null);
        }
    }
}
