package com.mirza.tvshowtime.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.adapters.UpcomingShowsViewAdapter;
import com.mirza.tvshowtime.database.ShowAndEpisodes;
import com.mirza.tvshowtime.viewmodel.UpcomingShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class UpcomingShowsFragment extends Fragment implements UpcomingShowsViewAdapter.UpcomingClickListener {

    RecyclerView recyclerView;
    UpcomingShowsViewModel viewModel;
    UpcomingShowsViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_myshows_tab_upcoming,container,false);
        recyclerView = view.findViewById(R.id.upcomingRecyclerView);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UpcomingShowsViewAdapter(getContext(),new ArrayList<ShowAndEpisodes>(),this);
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(UpcomingShowsViewModel.class);
        viewModel.getUpcomingEpisodes().observe(getViewLifecycleOwner(), new Observer<List<ShowAndEpisodes>>() {
            @Override
            public void onChanged(List<ShowAndEpisodes> showAndEpisodes) {
                adapter.setEpisodes(showAndEpisodes);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.top_search_upcoming,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public void onClick(ShowAndEpisodes episodes) {
        EpisodeDetails episodeDetails = new EpisodeDetails(getContext(),episodes.getEpisodeName(),episodes.getEpisodeAirDate(),episodes.getEpisodeNumber(),episodes.getSeasonNumber(),episodes.getImageUrl(),episodes.getSummary());
        episodeDetails.show(getFragmentManager(),"Episode Details");
    }

}
