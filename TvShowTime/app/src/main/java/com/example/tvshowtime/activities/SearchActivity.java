package com.example.tvshowtime.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tvshowtime.R;
import com.example.tvshowtime.adapters.SearchShowAdapter;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.tvmazeapi.ShowJson;
import com.example.tvshowtime.viewmodel.SearchActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.subjects.PublishSubject;

import static androidx.core.content.ContextCompat.startActivity;

public class SearchActivity extends AppCompatActivity implements SearchShowAdapter.SearchShowAdapterOnClickListener {

    private PublishSubject<String> subject;
    private EditText searchShow;
    private RecyclerView recyclerView;
    private SearchShowAdapter adapter;
    private SearchActivityViewModel viewModel;
    public static final String INTENT_EXTRA = "intentExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchShow = findViewById(R.id.searchTextView);
        viewModel = ViewModelProviders.of(this).get(SearchActivityViewModel.class);
        if(savedInstanceState!=null){
            String search = savedInstanceState.getString("search","");
            searchShow.setText(search);
        }
        recyclerView = findViewById(R.id.showSearchRecyclerView);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });
        viewModel.setSearchedShows("");
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        subject = PublishSubject.create();
        subject.debounce(500, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        viewModel.setSearchedShows(s);
                    }
                });
        searchShow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subject.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        adapter = new SearchShowAdapter(getApplicationContext(),new ArrayList<ShowJson>(), this);
        recyclerView.setAdapter(adapter);
        viewModel.getSearchedShows().observe(this, new Observer<List<ShowJson>>() {
            @Override
            public void onChanged(List<ShowJson> showJsons) {
                adapter.setShows(showJsons);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("search",searchShow.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(Show s) {
        Intent intent = new Intent(this, ShowDetails.class);
        intent.putExtra(INTENT_EXTRA,s.getShowId());
        startActivity(intent);
    }
}
