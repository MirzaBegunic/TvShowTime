package com.mirza.tvshowtime.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.adapters.EpisodesListViewAdapter;
import com.mirza.tvshowtime.database.Episodes;
import com.mirza.tvshowtime.database.SeasonAndEpisodes;
import com.mirza.tvshowtime.viewmodel.ShowDetailsEpisodesFragmentViewModel;
import java.util.HashMap;
import java.util.List;

public class ShowDetailsEpisodesFragment extends Fragment implements EpisodesListViewAdapter.episodeWatchedClickListner, EpisodesListViewAdapter.episodeInfoListener, EpisodesListViewAdapter.seeAllEpisodes {

    private ShowDetailsEpisodesFragmentViewModel viewModel;
    private RecyclerView recyclerView;
    private int showId;
    private HashMap<Integer, Boolean> mapOfWatchedEpisodes;
    private EpisodesListViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_details_episodes, container, false);
        Bundle bundle = this.getArguments();
        showId = bundle.getInt("showId");
        Log.d("ShowDetails", "showId: " + showId);
        recyclerView = view.findViewById(R.id.episodesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShowDetailsEpisodesFragmentViewModel.class);
        mapOfWatchedEpisodes = new HashMap<>();
        viewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<SeasonAndEpisodes>>() {
            @Override
            public void onChanged(List<SeasonAndEpisodes> seasonAndEpisodes) {
                HashMap<String,Boolean> stateMap = new HashMap<>();
                for (SeasonAndEpisodes member: seasonAndEpisodes) {
                    stateMap.put(member.getTitle(),false);
                }
                if(viewModel.checkIfSeriesIsAdded(showId))
                    adapter = new EpisodesListViewAdapter(getContext(),seasonAndEpisodes,stateMap,mapOfWatchedEpisodes,ShowDetailsEpisodesFragment.this, ShowDetailsEpisodesFragment.this, ShowDetailsEpisodesFragment.this, true);
                else
                    adapter = new EpisodesListViewAdapter(getContext(),seasonAndEpisodes,stateMap,mapOfWatchedEpisodes,ShowDetailsEpisodesFragment.this, ShowDetailsEpisodesFragment.this, ShowDetailsEpisodesFragment.this, false);
                recyclerView.setAdapter(adapter);
            }
        });
        if(viewModel.checkIfSeriesIsAdded(showId)){
            viewModel.getShowEpisodes(showId).observe(getViewLifecycleOwner(), new Observer<List<Episodes>>() {
                @Override
                public void onChanged(List<Episodes> episodes) {
                    Log.d("episode", "onChanged: changa kad je dodana");
                    for (Episodes ep: episodes) {
                        mapOfWatchedEpisodes.put(ep.getEpisodeId(),ep.getSeenStatus());
                    }
                }
            });
        }else{
            viewModel.getShowEpisodes(showId).observe(getViewLifecycleOwner(), new Observer<List<Episodes>>() {
                @Override
                public void onChanged(List<Episodes> episodes) {
                    Log.d("episode", "onChanged: changa nije dodana");
                    for (Episodes ep: episodes) {
                        mapOfWatchedEpisodes.put(ep.getEpisodeId(),ep.getSeenStatus());
                    }
                    if(adapter!=null)
                        adapter.setWatchedEpisodesMap(mapOfWatchedEpisodes);

                }
            });
        }
    }

    public void setAdded(){
        if(adapter!=null)
            adapter.setAddedStatus(true);
    }

    public void setNotAdded(){
        if(adapter!=null)
            adapter.setAddedStatus(false);
    }

    @Override
    public void onClickAdd(Episodes ep) {
        viewModel.setEpisodeAsWatched(ep.getEpisodeId());
        Toast.makeText(getContext(),"Watched!",Toast.LENGTH_SHORT).show();
        Log.d("showDetailes", "onClick: ");
    }

    @Override
    public void onClickRemove(Episodes ep) {
        viewModel.setEpisodeAsNotWatched(ep.getEpisodeId());
        Toast.makeText(getContext(),"Removed!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Episodes ep) {
        EpisodeDetails episodeDetails = new EpisodeDetails(getContext(),ep.getEpisodeName(),ep.getAirDate(),ep.getEpisodeNumber(),ep.getSeasonNumber(),ep.getImageUrl(),ep.getSummary());
        episodeDetails.show(getFragmentManager(),"Episode Details");
    }

    @Override
    public void seeAll(String season) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=7;i<season.length();++i){
            stringBuilder.append(season.charAt(i));
        }
        int seasonNumber = Integer.parseInt(stringBuilder.toString());
        Log.d("SeasonNumber", "number is" + seasonNumber);
        viewModel.setAllSeasonEpisodesAsWatched(showId,seasonNumber);
        if(viewModel.checkIfSeriesIsAdded(showId))
            Toast.makeText(getContext(),"You've seen them all",Toast.LENGTH_SHORT).show();
    }
}