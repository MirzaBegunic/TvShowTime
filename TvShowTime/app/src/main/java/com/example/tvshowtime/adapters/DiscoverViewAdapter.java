package com.example.tvshowtime.adapters;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.activities.ShowDetails;
import com.example.tvshowtime.database.Show;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

public class DiscoverViewAdapter extends RecyclerView.Adapter<DiscoverViewAdapter.DiscoverViewHolder>{

    private static final String TAG = "DiscoverViewAdapter";
    private LayoutInflater mInflater;
    private List<Show> showList;
    private onClickDiscover listener;

    public interface onClickDiscover{
        void onClick(Show s);
    }

    public DiscoverViewAdapter(Context context, List<Show> showList, onClickDiscover listener){
        mInflater = LayoutInflater.from(context);
        this.showList = showList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.activity_main_discover_tab_show_view_layout,parent,false);
        return new DiscoverViewHolder(mView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        Log.d(TAG, "onBind");
        final Show show = showList.get(position);
        if(show.getShowName()!=null && show.getStatus()!=null){
            holder.showNameTextView.setText(show.getShowName());
            holder.statusTextView.setText(show.getStatus());
            holder.runtimeTextView.setText(show.getRunTime() + "min");
            if(show.getImageUrl()==null || show.getImageUrl().getUrlLarge() == null)
                Glide.with(holder.itemView).load(R.drawable.error).into(holder.showImageImageView);
            else
                Glide.with(holder.itemView).load(show.getImageUrl().getUrlLarge()).into(holder.showImageImageView);
            if(listener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(show);
                    }
                });
            }
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


    public class DiscoverViewHolder extends RecyclerView.ViewHolder {

        public final TextView showNameTextView;
        public final TextView statusTextView;
        public final TextView runtimeTextView;
        public final ImageView showImageImageView;
        public final View itemView;
        final DiscoverViewAdapter adapter;

        public DiscoverViewHolder(@NonNull View itemView, DiscoverViewAdapter adapter) {
            super(itemView);
            showNameTextView = itemView.findViewById(R.id.showName);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            showImageImageView = itemView.findViewById(R.id.showImage);
            runtimeTextView = itemView.findViewById(R.id.runtimeTextView);
            this.itemView = itemView;
            this.adapter = adapter;
        }
    }
}
