package com.example.tvshowtime.adapters;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Episodes;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.HashMap;
import java.util.List;

public class EpisodesListViewAdapter extends ExpandableRecyclerViewAdapter<EpisodesListViewAdapter.SeasonViewHolder,EpisodesListViewAdapter.EpisodeViewHolder> {

    private LayoutInflater inflater;
    private HashMap<String,Boolean> stateMap;

    public EpisodesListViewAdapter(Context context,List<? extends ExpandableGroup> groups,HashMap<String,Boolean> stateMap) {
        super(groups);
        inflater = LayoutInflater.from(context);
        this.stateMap = stateMap;
    }

    @Override
    public SeasonViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_show_details_episodes_header,parent,false);
        return new SeasonViewHolder(view);
    }

    @Override
    public EpisodeViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_show_details_episodes_episode,parent,false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(EpisodeViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Episodes episodes = (Episodes) group.getItems().get(childIndex);
        holder.onBind(episodes);
    }

    @Override
    public void onBindGroupViewHolder(SeasonViewHolder holder, int flatPosition, ExpandableGroup group) {
        Log.d("bindanje", "onBindGroupViewHolder: "+ group.getTitle());
        holder.setContent(group);
        Boolean state = stateMap.get(group.getTitle());
        if(state)
            holder.animateExpand();
        else
            holder.animateCollapse();
    }

    public class SeasonViewHolder extends GroupViewHolder {

        private TextView seasonTitle;
        private ImageView arrow;
        private View view;
        private String title;

        public SeasonViewHolder(View itemView) {
            super(itemView);
            seasonTitle = itemView.findViewById(R.id.detailsEpisodeHeaderText);
            arrow = itemView.findViewById(R.id.expandedButton);
            view = itemView;
        }

        public void setContent(ExpandableGroup group){
            seasonTitle.setText(group.getTitle());
            this.title = group.getTitle();
        }

        @Override
        public void expand() {
            stateMap.put(title,true);
            animateExpand();
        }

        @Override
        public void collapse() {
            stateMap.put(title,false);
            animateCollapse();
        }

        private void animateExpand() {
            Log.d("animation", "animateExpand: ");
            arrow.setImageDrawable(view.getResources().getDrawable(R.drawable.arrowup));
        }

        private void animateCollapse() {
            Log.d("animation", "animateCollapse: ");
            arrow.setImageDrawable(view.getResources().getDrawable(R.drawable.arrow));
        }

    }

    public class EpisodeViewHolder extends ChildViewHolder {

        private ImageView image;
        private TextView episodeNmbr;
        private TextView episodeName;
        private View itemView;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.detailsEpisodesEpisodeImage);
            episodeName = itemView.findViewById(R.id.detailsEpisodesEpisodeDescription);
            episodeNmbr = itemView.findViewById(R.id.detailsEpisodesEpisodeInfo);
            this.itemView = itemView;
        }

        public void onBind(Episodes episode){
            if(episode.getImageUrl()!=null && episode.getImageUrl().getUrlSmall()!=null)
                Glide.with(this.itemView).load(episode.getImageUrl().getUrlSmall()).into(image);
            else
                Glide.with(this.itemView).load(R.drawable.error).into(image);
            episodeNmbr.setText("Episode " + episode.getEpisodeNumber());
            if(episode.getEpisodeName()!=null)
                episodeName.setText(episode.getEpisodeName());
        }
    }
}