package com.mirza.tvshowtime.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.app.App;
import com.mirza.tvshowtime.database.Show;
import com.mirza.tvshowtime.fragments.DiscoverFragment;
import com.mirza.tvshowtime.fragments.ShowDetailsCastFragment;
import com.mirza.tvshowtime.fragments.ShowDetailsEpisodesFragment;
import com.mirza.tvshowtime.fragments.ShowDetailsInfoFragment;
import com.mirza.tvshowtime.repository.TvShowRepository;
import com.mirza.tvshowtime.services.AddShowToDatabaseService;
import com.mirza.tvshowtime.viewmodel.ShowDetailsViewModel;
import com.google.android.material.tabs.TabLayout;



public class ShowDetails extends AppCompatActivity {

    public static final String ACTION_ADD_SHOW = "action add show";
    private Integer showId;
    private ImageView imageView;
    private TabLayout tabLayout;
    private TextView showName;
    private ShowDetailsViewModel viewModel;
    private ImageView addIcon;
    private TvShowRepository repository;
    private ShowDetailsEpisodesFragment epFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        viewModel = ViewModelProviders.of(this).get(ShowDetailsViewModel.class);
        repository = App.getInstance().getTvShowRepository();
        Intent intent = getIntent();
        showId = intent.getIntExtra(DiscoverFragment.INTENT_EXTRA,0);
        if(showId!=0){
            viewModel.fetchShowInfo(showId);
            viewModel.fetchShowEpisodes(showId);
            viewModel.fetchCast(showId);
        }
        imageView = findViewById(R.id.imageShow);
        showName = findViewById(R.id.showNameForDisplay);
        tabLayout = findViewById(R.id.tabLayout);
        addIcon = findViewById(R.id.addIcon);
        Boolean seriesIsAdded = repository.checkIfSeriesIsAdded(showId);
        if(!seriesIsAdded ){
            addIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent addIntent = new Intent(ShowDetails.this, AddShowToDatabaseService.class);
                    addIntent.putExtra(ACTION_ADD_SHOW,showId);
                    startService(addIntent);
                    addIcon.setImageDrawable(getDrawable(R.drawable.add_show_icon_added));
                    Toast.makeText(getApplicationContext(),"Show added",Toast.LENGTH_SHORT).show();
                    MainActivity.setTvShowHasBeenAdded();
                    if(epFragment!=null)
                        epFragment.setAdded();
                }
            });
        }else{
            addIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    repository.removeFromDb(showId);
                    addIcon.setImageDrawable(getDrawable(R.drawable.add_show_icon_not_added));
                    Toast.makeText(getApplicationContext(),"Show removed",Toast.LENGTH_SHORT).show();
                }
            });
            addIcon.setImageDrawable(getDrawable(R.drawable.add_show_icon_added));
        }


        tabLayout.addOnTabSelectedListener(navigationListner);
        viewModel.getShowInfo().observe(this, new Observer<Show>() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroyData();
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
                    Bundle bundle = new Bundle();
                    bundle.putInt("showId",showId);
                    selectedTab = new ShowDetailsEpisodesFragment();
                    epFragment = (ShowDetailsEpisodesFragment)selectedTab;
                    selectedTab.setArguments(bundle);
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
