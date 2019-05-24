package com.example.tvshowtime.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Show;
import com.example.tvshowtime.viewmodel.ShowDetailsInfoFragmentViewModel;

import org.jsoup.Jsoup;

public class ShowDetailsInfoFragment extends Fragment {

    private TextView ratingTextView;
    private TextView networkText;
    private TextView statusTextView;
    private TextView runTime;
    private TextView genresTextView;
    private TextView descriptionText;
    private int showId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_details_info,container,false);
        ratingTextView = view.findViewById(R.id.ratingTextView);
        networkText = view.findViewById(R.id.networkText);
        statusTextView = view.findViewById(R.id.statusTextView);
        runTime = view.findViewById(R.id.runtime);
        genresTextView = view.findViewById(R.id.genresTextView);
        descriptionText = view.findViewById(R.id.descriptionText);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            ShowDetailsInfoFragmentViewModel viewModel = ViewModelProviders.of(this).get(ShowDetailsInfoFragmentViewModel.class);
            viewModel.getShowInfo().observe(getViewLifecycleOwner(), new Observer<Show>() {
                @Override
                public void onChanged(Show show) {
                    if(show.getRating()!=null)
                        ratingTextView.setText(Double.toString(show.getRating().getAverage()));
                    if(show.getStatus()!=null)
                        statusTextView.setText(show.getStatus());
                    if(show.getNetwork()!=null){
                        if(show.getNetwork().getNetworkName()!=null)
                            networkText.setText(show.getNetwork().getNetworkName());
                    }
                    String runtime = Integer.toString(show.getRunTime())+"\nmin";
                    if(runtime!=null)
                        runTime.setText(runtime);
                    StringBuilder string = new StringBuilder();
                    int i;
                    for(i = 0; i < show.getGenres().length; ++i){
                        string.append(" " + show.getGenres()[i] + " ");
                        string.append("|");
                    }
                    if(string.length()>1)
                        string.deleteCharAt(string.length()-1);
                    genresTextView.setText(string);
                    if(show.getDescription()!=null){
                        String showDescription = Jsoup.parse(show.getDescription()).text();
                        descriptionText.setText(showDescription);
                    }
                }
            });

    }
}
