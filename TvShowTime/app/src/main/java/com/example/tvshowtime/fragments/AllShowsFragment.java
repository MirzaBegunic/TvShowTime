package com.example.tvshowtime.fragments;

import android.content.Intent;
import android.graphics.Rect;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowtime.R;
import com.example.tvshowtime.activities.ShowDetails;
import com.example.tvshowtime.adapters.AllShowsViewAdapter;
import com.example.tvshowtime.app.GridSpacingItemDecoration;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.viewmodel.AllShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllShowsFragment extends Fragment implements AllShowsViewAdapter.onClickMyShows {
    private RecyclerView recyclerView;
    private AllShowsViewAdapter adapter;
    public static final String INTENT_EXTRA = "intentExtra";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_main_myshows_tab_all_shows,container,false);
        recyclerView = view.findViewById(R.id.myShowsAllShowsRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        int spanCount = 3;
        int spacing = 0;
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new AllShowsViewAdapter(getContext(),new ArrayList<Show>(),this);
        recyclerView.setAdapter(adapter);
        AllShowsViewModel viewModel = ViewModelProviders.of(this).get(AllShowsViewModel.class);
        viewModel.getMyShows().observe(getViewLifecycleOwner(), new Observer<List<Show>>() {
            @Override
            public void onChanged(List<Show> showList) {
                adapter.setShows(showList);
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
        inflater.inflate(R.menu.top_sync_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}