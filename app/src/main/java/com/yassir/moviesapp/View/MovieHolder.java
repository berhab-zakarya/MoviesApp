package com.yassir.moviesapp.View;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yassir.moviesapp.Models.Movie;
import com.yassir.moviesapp.R;
import com.yassir.moviesapp.Utils.Utils;
import org.jetbrains.annotations.NotNull;

public class MovieHolder extends RecyclerView.ViewHolder {
    public RoundedImageView poster;
    public TextView title,desc,releaseDate,imdbVote;
    public MovieHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        poster = itemView.findViewById(R.id.riv_poster);
        title = itemView.findViewById(R.id.title);
        desc = itemView.findViewById(R.id.desc);
        releaseDate = itemView.findViewById(R.id.release_date);
        imdbVote = itemView.findViewById(R.id.imdb_vote);
    }
    @SuppressLint("SetTextI18n")
    public void bindView(Movie movie){
        Glide.with(itemView.getContext()).load(movie.getImgPoster())
                .into(poster);
        title.setText(movie.getTitle());
        desc.setText(Utils.minimizeString(movie.getDesc())+itemView.getContext().getResources().getString(R.string.points));
        releaseDate.setText(movie.getReleaseDate());
        imdbVote.setText(movie.getVote_average()+"");
    }
}
