package com.mirza.tvshowtime.fragments;

import android.os.Bundle;
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

import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.adapters.ShowDetailsCastViewAdapter;
import com.mirza.tvshowtime.database.Cast;
import com.mirza.tvshowtime.viewmodel.ShowDetailsCastFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailsCastFragment extends Fragment {
    private RecyclerView recyclerView;
    ShowDetailsCastFragmentViewModel viewModel;
    ShowDetailsCastViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_details_cast,container,false);
        recyclerView = view.findViewById(R.id.castRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShowDetailsCastFragmentViewModel.class);
        final List<Cast> cast = new ArrayList<>();
        adapter = new ShowDetailsCastViewAdapter(getContext(),cast);
        recyclerView.setAdapter(adapter);

        viewModel.getShowCast().observe(getViewLifecycleOwner(), new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                adapter.setShowCast(casts);
            }
        });
    }
}
