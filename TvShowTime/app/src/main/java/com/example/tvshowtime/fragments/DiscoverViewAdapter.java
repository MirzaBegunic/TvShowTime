package com.example.tvshowtime.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Show;
import java.util.List;

public class DiscoverViewAdapter extends RecyclerView.Adapter<DiscoverViewAdapter.DiscoverViewHolder> {

    private static final String TAG = "DiscoverViewAdapter";
    private LayoutInflater mInflater;
    private List<Show> showList;

    public DiscoverViewAdapter(Context context, List<Show> showList){
        mInflater = LayoutInflater.from(context);
        this.showList = showList;
    }

    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.show_view_layout,parent,false);
        return new DiscoverViewHolder(mView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        Log.d(TAG, "onBind");
        Show show = showList.get(position);
        if(show.getShowName()!=null && show.getStatus()!=null && show.getImageUrl()!=null){
            holder.showNameTextView.setText(show.getShowName());
            holder.statusTextView.setText(show.getStatus());
            holder.runtimeTextView.setText(show.getRunTime() + "min");
            Glide.with(holder.itemView).load(show.getImageUrl().getUrlLarge()).into(holder.showImageImageView);
            //TODO: Add onClick listener to button
        }
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public void setNewData(List<Show> list){
        this.showList = list;
        notifyDataSetChanged();
        Log.d(TAG, "setNewData: set");
    }

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder {

        public final TextView showNameTextView;
        public final TextView statusTextView;
        public final TextView runtimeTextView;
        public final ImageView showImageImageView;
        public final Button showAddButton;
        public final View itemView;
        final DiscoverViewAdapter adapter;

        public DiscoverViewHolder(@NonNull View itemView, DiscoverViewAdapter adapter) {
            super(itemView);
            showNameTextView = itemView.findViewById(R.id.showName);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            showImageImageView = itemView.findViewById(R.id.showImage);
            showAddButton = itemView.findViewById(R.id.addShowButton);
            runtimeTextView = itemView.findViewById(R.id.runtimeTextView);
            this.itemView = itemView;
            this.adapter = adapter;
        }
    }
}
