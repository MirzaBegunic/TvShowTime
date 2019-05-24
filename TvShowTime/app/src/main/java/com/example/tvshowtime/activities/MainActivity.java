package com.example.tvshowtime.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.tvshowtime.R;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListner);
        bottomNavigationView.setSelectedItemId(R.id.discover);
        preferences = getApplicationContext().getSharedPreferences("MyPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        Log.d(TAG, "onCreate: Finished");
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
