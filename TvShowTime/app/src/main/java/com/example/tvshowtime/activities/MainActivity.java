package com.example.tvshowtime.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tvshowtime.R;
import com.example.tvshowtime.fragments.DiscoverFragment;
import com.example.tvshowtime.fragments.AllShowsFragment;
import com.example.tvshowtime.fragments.MyShowsFragment;
import com.example.tvshowtime.fragments.StatsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String SavedFragment = "FragmentID";
    public static final String TvShowAdded = "TvShowAdded";
    private BottomNavigationView bottomNavigationView;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    private int selectedTabId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getApplicationContext().getSharedPreferences("MyPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        if(savedInstanceState!=null){
            selectedTabId = savedInstanceState.getInt(SavedFragment,0);
        }else{
            Boolean tvShowAddedBool = preferences.getBoolean(TvShowAdded,false);
            if(tvShowAddedBool){
                selectedTabId = R.id.myShows;
            }else{
                selectedTabId = R.id.discover;
            }
        }
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListner);
        Fragment selectedTab = null;
        switch (selectedTabId){
            case R.id.discover:
                selectedTabId = R.id.discover;
                bottomNavigationView.setSelectedItemId(R.id.discover);
                selectedTab = new DiscoverFragment();
                break;
            case R.id.myShows:
                selectedTabId = R.id.myShows;
                bottomNavigationView.setSelectedItemId(R.id.myShows);
                selectedTab = new MyShowsFragment();
                break;
            case R.id.stats:
                selectedTabId = R.id.stats;
                bottomNavigationView.setSelectedItemId(R.id.stats);
                selectedTab = new StatsFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedTab).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("OptionsMenu", "onCreateOptionsMenu: ACTIVITY");
        getMenuInflater().inflate(R.menu.top_search_bar,menu);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            if(menuItem.getItemId() != selectedTabId){
                Fragment selectedTab = null;

                switch (menuItem.getItemId()){
                    case R.id.discover:
                        selectedTabId = menuItem.getItemId();
                        selectedTab = new DiscoverFragment();
                        break;
                    case R.id.myShows:
                        selectedTabId = menuItem.getItemId();
                        selectedTab = new MyShowsFragment();
                        break;
                    case R.id.stats:
                        selectedTabId = menuItem.getItemId();
                        selectedTab = new StatsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedTab).commit();
            }
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSearch:
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent,null);
                return true;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SavedFragment,selectedTabId);
    }

    public static void setTvShowHasBeenAdded(){
        if(preferences.getBoolean(TvShowAdded,true)){
            editor.putBoolean(TvShowAdded,true);
            editor.commit();
        }
    }
}
