package com.example.tvshowtime.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.tvshowtime.database.SeasonAndEpisodes;
import com.example.tvshowtime.viewmodel.ShowDetailsEpisodesFragmentViewModel;

import java.util.HashMap;
import java.util.List;

public class ShowDetailsEpisodesFragment extends Fragment {

    private ShowDetailsEpisodesFragmentViewModel viewModel;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_details_episodes, container, false);
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
        viewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<SeasonAndEpisodes>>() {
            @Override
            public void onChanged(List<SeasonAndEpisodes> seasonAndEpisodes) {
                HashMap<String,Boolean> stateMap = new HashMap<>();
                for (SeasonAndEpisodes member: seasonAndEpisodes) {
                    stateMap.put(member.getTitle(),false);
                }
                recyclerView.setAdapter(new EpisodesListViewAdapter(getContext(),seasonAndEpisodes,stateMap));
            }
        });
    }
}