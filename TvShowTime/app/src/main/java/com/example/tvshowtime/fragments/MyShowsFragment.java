package com.example.tvshowtime.fragments;

import android.content.Intent;
import android.graphics.Rect;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowtime.R;
import com.example.tvshowtime.activities.ShowDetails;
import com.example.tvshowtime.adapters.MyShowsTabAdapter;
import com.example.tvshowtime.database.ImageLinks;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.viewmodel.MyShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyShowsFragment extends Fragment implements MyShowsTabAdapter.onClickMyShows {
    private RecyclerView recyclerView;
    private MyShowsTabAdapter adapter;
    public static final String INTENT_EXTRA = "intentExtra";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_main_myshows_tab,container,false);
        recyclerView = view.findViewById(R.id.myShowsRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        int spanCount = 3;
        int spacing = 0;
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new MyShowsTabAdapter(getContext(),new ArrayList<Show>(),this);
        recyclerView.setAdapter(adapter);
        MyShowsViewModel viewModel = ViewModelProviders.of(this).get(MyShowsViewModel.class);
        viewModel.getMyShows().observe(getViewLifecycleOwner(), new Observer<List<Show>>() {
            @Override
            public void onChanged(List<Show> showList) {
                adapter.setShows(showList);
                Log.d("logging", "onChanged: show list size " +showList.size());
            }
        });
    }

    @Override
    public void onClick(Show show) {
        Intent intent = new Intent(getContext(), ShowDetails.class);
        intent.putExtra(INTENT_EXTRA,show.getShowId());
        startActivity(intent);
    }
}

class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}
