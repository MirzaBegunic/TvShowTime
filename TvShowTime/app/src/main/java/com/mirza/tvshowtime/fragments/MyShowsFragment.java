package com.mirza.tvshowtime.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.mirza.tvshowtime.R;
import com.google.android.material.tabs.TabLayout;

public class MyShowsFragment extends Fragment {
    private TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_myshows_tab,container,false);
        mTabLayout = view.findViewById(R.id.myShowsTabLayout);
        mTabLayout.addOnTabSelectedListener(navigationListener);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(R.id.myShowsFrame,new AllShowsFragment()).commit();
    }

    private TabLayout.BaseOnTabSelectedListener navigationListener = new TabLayout.BaseOnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Fragment selectedTab = null;
            switch (tab.getPosition()){
                case 0:
                    selectedTab = new AllShowsFragment();
                    break;
                case 1:
                    selectedTab = new WatchListFragment();
                    break;
                case 2:
                    selectedTab = new UpcomingShowsFragment();
                    break;
            }
            getFragmentManager().beginTransaction().replace(R.id.myShowsFrame,selectedTab).commit();

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
