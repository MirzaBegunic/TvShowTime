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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.activities.ShowDetails;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.adapters.DiscoverViewAdapter;
import com.mirza.tvshowtime.viewmodel.DiscoverTabViewModel;

import java.util.ArrayList;
import java.util.List;


public class DiscoverFragment extends Fragment implements DiscoverViewAdapter.onClickDiscover {
    private RecyclerView mRecyclerView;
    private DiscoverViewAdapter adapter;
    public static final String INTENT_EXTRA = "intentExtra";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_discover_tab,container,false);
        mRecyclerView = view.findViewById(R.id.discoverRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DiscoverTabViewModel discoverTabViewModel = ViewModelProviders.of(this  ).get(DiscoverTabViewModel.class);
        adapter = new DiscoverViewAdapter(getContext(),new ArrayList<Show>(),this);
        mRecyclerView.setAdapter(adapter);
        discoverTabViewModel.getDiscoverShow().observe(getViewLifecycleOwner(), new Observer<List<Show>>() {
            @Override
            public void onChanged(List<Show> showList) {
                Log.d("logovi", "onChanged:  " + showList.size());
                adapter.setNewData(showList);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.top_search_bar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(Show s) {
        Intent intent = new Intent(getContext(),ShowDetails.class);
        intent.putExtra(INTENT_EXTRA,s.getShowId());
        startActivity(intent);
    }
}
