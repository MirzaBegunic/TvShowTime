package com.example.tvshowtime.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Show;

import java.util.List;
import java.util.zip.Inflater;

public class DiscoverViewAdapter extends RecyclerView.Adapter<DiscoverViewAdapter.DiscoverViewHolder> {

    private static final String TAG = "DiscoverViewAdapter";
    private LayoutInflater mInflater;
    private List<Show> showList;


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
        holder.showNameTextView.setText(show.getShowName());
        holder.statusTextView.setText(show.getStatus());
        holder.runtimeTextView.setText(show.getRunTime() + "min");
        //TODO: Add glide picture to holder
        //TODO: Add onClick listener to button
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder {

        public final TextView showNameTextView;
        public final TextView statusTextView;
        public final TextView runtimeTextView;
        public final ImageView showImageImageView;
        public final Button showAddButton;
        final DiscoverViewAdapter adapter;

        public DiscoverViewHolder(@NonNull View itemView, DiscoverViewAdapter adapter) {
            super(itemView);
            showNameTextView = itemView.findViewById(R.id.showName);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            showImageImageView = itemView.findViewById(R.id.showImage);
            showAddButton = itemView.findViewById(R.id.addShowButton);
            runtimeTextView = itemView.findViewById(R.id.runtimeTextView);
            this.adapter = adapter;
        }
    }
}
