package com.example.tvshowtime.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Episodes;
import com.example.tvshowtime.viewmodel.SearchWatchListViewModel;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class SearchWatchList extends AppCompatActivity {

    private PublishSubject<String> subject;
    private SearchWatchListViewModel viewModel;
    private ImageView episodeImage;
    private TextView seasonNumber;
    private TextView episodeNumber;
    private TextView episodeDate;
    private TextView episodeName;
    private EditText searchQuery;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_watch_list);
        viewModel = ViewModelProviders.of(this).get(SearchWatchListViewModel.class);
        episodeImage = findViewById(R.id.watchListSearchImage);
        seasonNumber = findViewById(R.id.watchListSearchSNmbr);
        episodeNumber = findViewById(R.id.watchListSearchENmbr);
        episodeDate = findViewById(R.id.watchListSearchShowDate);
        episodeName = findViewById(R.id.watchListSearchEpisodeName);
        searchQuery = findViewById(R.id.watchListSearchTextView);
        cardView = findViewById(R.id.watchListCardView);
        cardView.setVisibility(View.INVISIBLE);
        if(savedInstanceState!=null){
            String search = savedInstanceState.getString("search","");
            searchQuery.setText(search);
        }
        subject = PublishSubject.create();
        subject.debounce(500, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        viewModel.searchEpisode(s);
                    }
                });
        searchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subject.onNext(s.toString());
                cardView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        viewModel.getSearchedEpisode().observe(this, new Observer<Episodes>() {
            @Override
            public void onChanged(Episodes episodes) {
                if(episodes!=null){
                    cardView.setVisibility(View.VISIBLE);
                    if(episodes.getEpisodeName()!=null && !episodes.getEpisodeName().isEmpty())
                        episodeName.setText(episodes.getEpisodeName());
                    if(episodes.getImageUrl()!=null && episodes.getImageUrl().getUrlLarge()!=null)
                        Glide.with(getWindow().getDecorView()).load(episodes.getImageUrl().getUrlLarge()).into(episodeImage);
                    else
                        Glide.with(getWindow().getDecorView()).load(R.drawable.error).into(episodeImage);
                    seasonNumber.setText(String.valueOf(episodes.getSeasonNumber()));
                    episodeNumber.setText(String.valueOf(episodes.getEpisodeNumber()));
                    if(episodes.getAirDate()!=null)
                        episodeDate.setText(episodes.getAirDate());
                }
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("search",searchQuery.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
