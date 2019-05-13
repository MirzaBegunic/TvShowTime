package com.example.tvshowtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.fragments.DiscoverFragment;
import com.example.tvshowtime.fragments.MyShowsFragment;
import com.example.tvshowtime.fragments.StatsFragment;
import com.example.tvshowtime.repository.TvShowRepository;
import com.example.tvshowtime.tvmazeapi.ApiTester;
import com.example.tvshowtime.tvmazeapi.ShowJson;
import com.example.tvshowtime.tvmazeapi.TvMazeApi;
import com.example.tvshowtime.viewmodel.TvViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TvViewModel tvViewModel; //ViewModel instance used to manipulate data
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListner);
        bottomNavigationView.setSelectedItemId(R.id.discover);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedTab = null;

            switch (menuItem.getItemId()){
                case R.id.discover:
                    selectedTab = new DiscoverFragment();
                    break;
                case R.id.myShows:
                    selectedTab = new MyShowsFragment();
                    break;
                case R.id.stats:
                    selectedTab = new StatsFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedTab).commit();

            return true;
        }
    };

}
