package com.example.tvshowtime.fragments;


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

import com.example.tvshowtime.R;
import com.example.tvshowtime.adapters.EpisodesListViewAdapter;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.database.SeasonAndEpisodes;
import com.example.tvshowtime.viewmodel.ShowDetailsEpisodesFragmentViewModel;

import java.util.HashMap;
import java.util.List;

public class ShowDetailsEpisodesFragment extends Fragment implements EpisodesListViewAdapter.episodeWatchedClickListner {

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
        if(viewModel.checkIfSeriesIsAdded(showId)){
            viewModel.getShowEpisodes(showId).observe(this, new Observer<List<Episodes>>() {
                @Override
                public void onChanged(List<Episodes> episodes) {
                    for (Episodes ep: episodes) {
                        mapOfWatchedEpisodes.put(ep.getEpisodeId(),ep.getSeenStatus());
                    }
                }
            });
        }else{
            viewModel.getShowEpisodes(showId).observe(this, new Observer<List<Episodes>>() {
                @Override
                public void onChanged(List<Episodes> episodes) {
                    for (Episodes ep: episodes) {
                        mapOfWatchedEpisodes.put(ep.getEpisodeId(),ep.getSeenStatus());
                    }
                    adapter.setWatchedEpisodesMap(mapOfWatchedEpisodes);
                }
            });
        }
        viewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<SeasonAndEpisodes>>() {
            @Override
            public void onChanged(List<SeasonAndEpisodes> seasonAndEpisodes) {
                HashMap<String,Boolean> stateMap = new HashMap<>();
                for (SeasonAndEpisodes member: seasonAndEpisodes) {
                    stateMap.put(member.getTitle(),false);
                }
                adapter = new EpisodesListViewAdapter(getContext(),seasonAndEpisodes,stateMap,mapOfWatchedEpisodes,ShowDetailsEpisodesFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });

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
}