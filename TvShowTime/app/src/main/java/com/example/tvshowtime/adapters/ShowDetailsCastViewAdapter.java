package com.example.tvshowtime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tvshowtime.R;
import com.example.tvshowtime.database.Cast;

import java.util.List;

public class ShowDetailsCastViewAdapter extends RecyclerView.Adapter<ShowDetailsCastViewAdapter.CastViewHolder> {

    private List<Cast> showCast;
    private LayoutInflater layoutInflater;

    public ShowDetailsCastViewAdapter(Context context, List<Cast> showCast){
        layoutInflater = LayoutInflater.from(context);
        this.showCast = showCast;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = layoutInflater.inflate(R.layout.activity_show_details_cast_view_layout,parent,false);
        return new CastViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = showCast.get(position);
        if(cast.getRealPerson().getName()!= null && cast.getSeriesCharacter().getName() != null){
            holder.personName.setText(cast.getRealPerson().getName());
            holder.castName.setText(cast.getSeriesCharacter().getName());
            if(cast.getSeriesCharacter().getImageUrl()!=null && cast.getSeriesCharacter().getImageUrl().getUrlLarge()!=null)
                Glide.with(holder.view).load(cast.getSeriesCharacter().getImageUrl().getUrlLarge()).apply(RequestOptions.circleCropTransform()).into(holder.personImage);
            else
                Glide.with(holder.view).load(R.drawable.no_profile_picture).apply(RequestOptions.circleCropTransform()).into(holder.personImage);
        }
    }

    @Override
    public int getItemCount() {
        return showCast.size();
    }

    public void setShowCast(List<Cast> cast){
        this.showCast = cast;
        notifyDataSetChanged();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{
        private ImageView personImage;
        private TextView personName;
        private TextView castName;
        private View view;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            personImage = itemView.findViewById(R.id.imageCast);
            personName = itemView.findViewById(R.id.detailsCastRealName);
            castName = itemView.findViewById(R.id.detailsCastRoleName);
            view = itemView;
        }
    }
}
