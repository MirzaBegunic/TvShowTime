package com.mirza.tvshowtime.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mirza.tvshowtime.R;
import com.mirza.tvshowtime.database.Episodes;
import com.mirza.tvshowtime.viewmodel.UpcomingSearchShowViewModel;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class SearchUpcoming extends AppCompatActivity {

    private PublishSubject<String> subject;
    private UpcomingSearchShowViewModel viewModel;
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
        setContentView(R.layout.activity_search_upcoming);
        viewModel = ViewModelProviders.of(this).get(UpcomingSearchShowViewModel.class);
        episodeImage = findViewById(R.id.upcomingSearchImage);
        seasonNumber = findViewById(R.id.upcomingSearchSNmbr);
        episodeNumber = findViewById(R.id.upcomingSearchENmbr);
        episodeDate = findViewById(R.id.upcomingSearchShowDate);
        episodeName = findViewById(R.id.upcomingSearchEpisodeName);
        searchQuery = findViewById(R.id.upcomingSearchTextView);
        cardView = findViewById(R.id.upcomingCardView);
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
                        viewModel.setSearchUpcoming(s);
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
        viewModel.getSearchUpcoming().observe(this, new Observer<Episodes>() {
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
}
