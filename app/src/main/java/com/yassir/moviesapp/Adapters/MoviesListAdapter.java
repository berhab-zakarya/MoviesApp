package com.yassir.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yassir.moviesapp.Models.Movie;
import com.yassir.moviesapp.R;
import com.yassir.moviesapp.Utils.OnClickItem;
import com.yassir.moviesapp.View.MovieHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MoviesListAdapter extends RecyclerView.Adapter<MovieHolder> {
    private final ArrayList<Movie> movies = new ArrayList<>();
    private OnClickItem onClickItem;

    public MoviesListAdapter(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
    public void setListMovies(ArrayList<Movie> listMovies){
        movies.addAll(listMovies);
    }

    @NonNull
    @NotNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bindView(movie);
        holder.itemView.setOnClickListener(view -> {
            onClickItem.onClickItem(movie.getId());
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
