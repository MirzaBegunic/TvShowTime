package com.example.tvshowtime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Episodes;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class EpisodesListViewAdapter extends ExpandableRecyclerViewAdapter<EpisodesListViewAdapter.SeasonViewHolder,EpisodesListViewAdapter.EpisodeViewHolder> {

    private LayoutInflater inflater;

    public EpisodesListViewAdapter(Context context,List<? extends ExpandableGroup> groups) {
        super(groups);
        inflater = LayoutInflater.from(context);
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
        holder.setContent(group);
    }

    public class SeasonViewHolder extends GroupViewHolder {

        private TextView seasonTitle;
        private Button button;
        private Boolean bool;

        public SeasonViewHolder(View itemView) {
            super(itemView);
            seasonTitle = itemView.findViewById(R.id.detailsEpisodeHeaderText);
            button = itemView.findViewById(R.id.expandedButton);
            bool = false;
        }

        public void setContent(ExpandableGroup group){
            seasonTitle.setText(group.getTitle());
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            if(!bool){
                button.setBackgroundResource(R.drawable.arrow_expaned);
                bool = true;
            }else{
                button.setBackgroundResource(R.drawable.arrow_collapsed);
                bool = false;
            }

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
