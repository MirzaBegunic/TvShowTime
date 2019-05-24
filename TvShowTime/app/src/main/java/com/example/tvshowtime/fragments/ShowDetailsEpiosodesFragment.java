package com.example.tvshowtime.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.adapters.EpisodesListAdapter;
import com.example.tvshowtime.viewmodel.ShowDetailsEpisodesFragmentViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowDetailsEpiosodesFragment extends Fragment {
    ShowDetailsEpisodesFragmentViewModel viewModel;
    ExpandableListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_details_episodes,container,false);
        listView = view.findViewById(R.id.episodesListView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShowDetailsEpisodesFragmentViewModel.class);
        final EpisodesListAdapter adapter = new EpisodesListAdapter(getContext(),new ArrayList<String>(),new HashMap<String, List<Episodes>>());
        listView.setAdapter(adapter);
        viewModel.getHeaders().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setNewHeaders(strings);
            }
        });
        viewModel.getMap().observe(getViewLifecycleOwner(), new Observer<HashMap<String, List<Episodes>>>() {
            @Override
            public void onChanged(HashMap<String, List<Episodes>> stringListHashMap) {
                adapter.setNewMapOfEpisodes(stringListHashMap);
            }
        });
    }
}
