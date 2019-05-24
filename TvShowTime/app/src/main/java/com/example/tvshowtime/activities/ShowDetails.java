package com.example.tvshowtime.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.fragments.ShowDetailsCastFragment;
import com.example.tvshowtime.fragments.ShowDetailsEpiosodesFragment;
import com.example.tvshowtime.fragments.ShowDetailsInfoFragment;
import com.example.tvshowtime.adapters.DiscoverViewAdapter;
import com.example.tvshowtime.viewmodel.ShowDetailsViewModel;
import com.google.android.material.tabs.TabLayout;

public class ShowDetails extends AppCompatActivity {

    private Integer showId;
    ImageView imageView;
    TabLayout tabLayout;
    TextView showName;
    private ShowDetailsViewModel vievModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        vievModel = ViewModelProviders.of(this).get(ShowDetailsViewModel.class);
        Intent intent = getIntent();
        showId = intent.getIntExtra(DiscoverViewAdapter.INTENT_EXTRA,0);
        if(showId!=0){
            vievModel.fetchShowInfo(showId);
            vievModel.fetchShowEpisodes(showId);
            vievModel.fetchCast(showId);
        }
        imageView = findViewById(R.id.imageShow);
        showName = findViewById(R.id.showNameForDisplay);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(navigationListner);
        vievModel.getShowInfo().observe(this, new Observer<Show>() {
            @Override
            public void onChanged(Show show) {
                if(show.getImageUrl()!=null)
                    Glide.with(getApplicationContext()).load(show.getImageUrl().getUrlLarge()).into(imageView);
                else
                    Glide.with(getApplicationContext()).load(R.drawable.error).into(imageView);
                showName.setText(show.getShowName());
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frameShow,new ShowDetailsInfoFragment()).commit();
    }

    private TabLayout.BaseOnTabSelectedListener navigationListner = new TabLayout.BaseOnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int id = tab.getPosition();
            Fragment selectedTab = null;
            switch (id){
                case 0:
                    selectedTab = new ShowDetailsInfoFragment();
                    break;
                case 1:
                    selectedTab = new ShowDetailsEpiosodesFragment();
                    break;
                case 2:
                    selectedTab = new ShowDetailsCastFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameShow,selectedTab).commit();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
