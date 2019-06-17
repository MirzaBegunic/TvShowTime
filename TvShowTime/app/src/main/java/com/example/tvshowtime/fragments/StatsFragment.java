package com.example.tvshowtime.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.app.App;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.repository.TvShowRepository;
import com.example.tvshowtime.viewmodel.StatsViewModel;

public class StatsFragment extends Fragment {

    private TextView statsTotalMinutes;
    private TextView statEpisodeCount;
    private ImageView showImageView;
    private TextView showMinutes;
    private TextView statShowName;
    private StatsViewModel viewModel;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main_stats_tab,container,false);
        statsTotalMinutes = view.findViewById(R.id.statsTotalMinutes);
        statEpisodeCount = view.findViewById(R.id.statsEpisodesCount);
        showImageView = view.findViewById(R.id.statImageView);
        showMinutes = view.findViewById(R.id.showMinutes);
        statShowName = view.findViewById(R.id.statShowName);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(StatsViewModel.class);
        viewModel.getTotalTime().observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong!=null)
                statsTotalMinutes.setText(String.valueOf(aLong));
            }
        });

        viewModel.getTotalEpisodeCount().observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong!=null)
                statEpisodeCount.setText(String.valueOf(aLong));
            }
        });

        viewModel.getMostWatched().observe(getViewLifecycleOwner(), new Observer<Show>() {
            @Override
            public void onChanged(Show showAndMinutesSum) {
                if(showAndMinutesSum!=null){
                    if(showAndMinutesSum.getShowName()!=null)
                        statShowName.setText(showAndMinutesSum.getShowName());
                    if(showAndMinutesSum.getImageUrl()!=null && showAndMinutesSum.getImageUrl().getUrlLarge()!=null)
                        Glide.with(view).load(showAndMinutesSum.getImageUrl().getUrlLarge()).into(showImageView);
                    else
                        Glide.with(view).load(R.drawable.error).into(showImageView);
                }
            }
        });
        viewModel.getMostWatchedSum().observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong!=null)
                    showMinutes.setText(String.valueOf(aLong));
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.top_search_bar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
