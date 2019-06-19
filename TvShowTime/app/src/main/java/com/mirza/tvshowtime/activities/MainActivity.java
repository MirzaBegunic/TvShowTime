package com.mirza.tvshowtime.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.app.SyncShowsWorkerClass;
import com.mirza.tvshowtime.app.TodayEpisodesWorkerClass;
import com.mirza.tvshowtime.fragments.DiscoverFragment;
import com.mirza.tvshowtime.fragments.MyShowsFragment;
import com.mirza.tvshowtime.fragments.StatsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String SavedFragment = "FragmentID";
    public static final String TvShowAdded = "TvShowAdded";
    public static final String NOTFICATION_PREF_KEY = "NotfPerfKey";
    public static final String SYNC_PREF_KEY = "SyncPerfKey";
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
        if(preferences.getBoolean(NOTFICATION_PREF_KEY,true)){
            setNotifications();
        }
        if(preferences.getBoolean(SYNC_PREF_KEY,true)){
            setUpdate();
        }
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
        menu.clear();
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

        switch (item.getItemId()){
            case R.id.actionSearchMain:
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.actionSearchFragmentUpcoming:
                Intent intent1 = new Intent(getApplicationContext(),SearchUpcoming.class);
                startActivity(intent1);
                break;
            case R.id.actionSearchFragmentWatchList:
                Intent intent2 = new Intent(getApplicationContext(),SearchWatchList.class);
                startActivity(intent2);
                break;
            case R.id.actionSync:
                startUpdate();
                break;
            default:
                return false;
        }
        return true;
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

    public void setNotifications(){
        Log.d(TAG, "setNotifications: dodajemo notifikaciju");
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(TodayEpisodesWorkerClass.class,24, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        WorkManager workManager = WorkManager.getInstance();
        workManager.enqueue(periodicWorkRequest);
        editor.putBoolean(NOTFICATION_PREF_KEY,false);
        editor.commit();
    }

    public void setUpdate(){
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(SyncShowsWorkerClass.class,23, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        WorkManager workManager = WorkManager.getInstance();
        workManager.enqueue(periodicWorkRequest);
        editor.putBoolean(SYNC_PREF_KEY,false);
        editor.commit();
    }


    public void showNotification() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(TodayEpisodesWorkerClass.class)
                .setConstraints(constraints)
                .build();
        WorkManager workManager = WorkManager.getInstance();
        workManager.enqueue(oneTimeWorkRequest);
    }

    public void startUpdate(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(false)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SyncShowsWorkerClass.class)
                .setConstraints(constraints)
                .build();
        WorkManager workManager = WorkManager.getInstance();
        workManager.enqueue(oneTimeWorkRequest);
    }
}
