package com.mirza.tvshowtime.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.activities.ShowDetails;
import com.mirza.tvshowtime.adapters.WatchListViewAdapter;
import com.mirza.tvshowtime.app.GridSpacingItemDecoration;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.viewmodel.WatchListViewModel;

import java.util.ArrayList;
import java.util.List;

public class WatchListFragment extends Fragment implements WatchListViewAdapter.onClicWatchListShow {
    RecyclerView recyclerView;
    WatchListViewAdapter adapter;
    WatchListViewModel viewModel;
    public static final String INTENT_EXTRA = "intentExtra";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_myshows_tab_watchlist,container,false);
        recyclerView = view.findViewById(R.id.watchListRecyclerView);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,0,true));
        adapter = new WatchListViewAdapter(getContext(),new ArrayList<Show>(),this);
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(WatchListViewModel.class);
        viewModel.getWatchListShows().observe(getViewLifecycleOwner(), new Observer<List<Show>>() {
            @Override
            public void onChanged(List<Show> showList) {
                adapter.setWatchListShow(showList);
            }
        });
    }

    @Override
    public void onClick(Show show) {
        Intent intent = new Intent(getContext(), ShowDetails.class);
        intent.putExtra(INTENT_EXTRA,show.getShowId());
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        Log.d("OptionsMenu", "onCreateOptionsMenu: Fragment");
        inflater.inflate(R.menu.top_search_watchlist_bar,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
}
