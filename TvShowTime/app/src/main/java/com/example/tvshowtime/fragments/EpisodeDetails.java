package com.example.tvshowtime.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.ImageLinks;
import com.example.tvshowtime.viewmodel.EpisodeDetailsViewModel;

import org.jsoup.Jsoup;

public class EpisodeDetails extends DialogFragment {
    private LayoutInflater layoutInflater;
    private EpisodeDetailsViewModel viewModel;
    private String epName;
    private String epDate;
    private int epNmbr;
    private int seNmbr;
    private String summ;
    private ImageLinks img;
    private TextView episodeName;
    private TextView episodeSeasonNumber;
    private TextView episodeNumber;
    private TextView date;
    private TextView summary;
    private ImageView imageView;
    private View view;

    public EpisodeDetails(Context context, String epName, String epDate, int epNmbr, int seNmbr, ImageLinks img, String summ) {
        layoutInflater = LayoutInflater.from(context);
        this.epName = epName;
        this.epDate = epDate;
        this.epNmbr = epNmbr;
        this.seNmbr = seNmbr;
        this.img = img;
        this.summ = summ;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        view = layoutInflater.inflate(R.layout.episodes_details_fragment,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        episodeNumber = view.findViewById(R.id.episodeEpisodeNumber);
        episodeName = view.findViewById(R.id.epEpisodeName);
        episodeSeasonNumber = view.findViewById(R.id.episodeSeasonNumber);
        date = view.findViewById(R.id.episodeAirDate);
        summary = view.findViewById(R.id.episodeSummary);
        imageView  = view.findViewById(R.id.episodeImage);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(epName!=null && !epName.isEmpty())
            episodeName.setText(epName);
        if(epDate!=null && !epDate.isEmpty())
            date.setText(epDate);
        episodeNumber.setText("E" + String.valueOf(epNmbr));
        episodeSeasonNumber.setText("S" + String.valueOf(seNmbr));

        if(summ!=null && !summ.isEmpty())
            summary.setText(Jsoup.parse(summ).text());
        if(img!=null && img.getUrlLarge()!=null)
            Glide.with(this).load(img.getUrlLarge()).into(imageView);
        else
            Glide.with(this).load(R.drawable.error).into(imageView);
    }
}
