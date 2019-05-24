package com.example.tvshowtime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Episodes;

import java.util.HashMap;
import java.util.List;

public class EpisodesListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> headers;
    private HashMap<String, List<Episodes>> mapOfEpisdes;

    public EpisodesListAdapter(Context context, List<String> headers, HashMap<String, List<Episodes>> mapOfEpisdes) {
        this.context = context;
        this.headers = headers;
        this.mapOfEpisdes = mapOfEpisdes;
    }

    @Override
    public int getGroupCount() {
        return this.headers.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mapOfEpisdes.get(getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headers.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mapOfEpisdes.get(headers.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_show_details_episodes_header,null);
        }
        TextView header = convertView.findViewById(R.id.detailsEpisodeHeaderText);
        header.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Episodes episodes = (Episodes) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_show_details_episodes_episode,null);
        }
        ImageView image = convertView.findViewById(R.id.detailsEpisodesEpisodeImage);
        TextView episodeNumber = convertView.findViewById(R.id.detailsEpisodesEpisodeInfo);
        TextView episodeDescription = convertView.findViewById(R.id.detailsEpisodesEpisodeDescription);
        if(episodes.getImageUrl()!=null){
            if(episodes.getImageUrl().getUrlLarge()!=null)
                Glide.with(convertView).load(episodes.getImageUrl().getUrlLarge()).into(image);
        }else {
            Glide.with(convertView).load(R.drawable.error).into(image);
        }
        episodeNumber.setText("Episode " + episodes.getEpisodeNumber());
        if(episodes.getEpisodeName()!=null)
            episodeDescription.setText(episodes.getEpisodeName());
        else
            episodeDescription.setText("Name Unknown");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setNewHeaders(List<String> headers){
        this.headers = headers;
    }

    public void setNewMapOfEpisodes(HashMap<String,List<Episodes>> mapOfEpisdes){
        this.mapOfEpisdes = mapOfEpisdes;
    }
}
