package com.example.tvshowtime.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.adapters.DiscoverViewAdapter;
import com.example.tvshowtime.viewmodel.DiscoverTabViewModel;

import java.util.ArrayList;
import java.util.List;


public class DiscoverFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DiscoverViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_discover_tab,container,false);
        mRecyclerView = view.findViewById(R.id.discoverRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DiscoverTabViewModel discoverTabViewModel = ViewModelProviders.of(this  ).get(DiscoverTabViewModel.class);
        adapter = new DiscoverViewAdapter(getContext(),new ArrayList<Show>());
        mRecyclerView.setAdapter(adapter);
        discoverTabViewModel.getDiscoverShow().observe(getViewLifecycleOwner(), new Observer<List<Show>>() {
            @Override
            public void onChanged(List<Show> showList) {
                Log.d("logovi", "onChanged:  " + showList.size());
                adapter.setNewData(showList);
            }
        });
    }

}
