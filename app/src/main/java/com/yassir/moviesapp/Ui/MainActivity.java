package com.yassir.moviesapp.Ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yassir.moviesapp.Adapters.MoviesListAdapter;
import com.yassir.moviesapp.Models.Movie;
import com.yassir.moviesapp.R;
import com.yassir.moviesapp.Utils.Constants;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListMovies;
    private final ArrayList<Movie> movieArrayList = new ArrayList<>();
    private MoviesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    private void getData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constants.API_URL_LIST).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
                new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                assert response.body() != null;
                final String myResponse = response.body().string();
                runOnUiThread(() -> {
                    try {
                        JSONObject jsonObject = new JSONObject(myResponse);
                        JSONArray dataArrays = jsonObject.getJSONArray("results");
                        for(int i = 0 ; i < dataArrays.length();i++){
                            JSONObject currentData = dataArrays.getJSONObject(i);
                            long id = currentData.getLong("id");
                            String title = currentData.getString("title");
                            String overview = currentData.getString("overview");
                            String releaseDate = currentData.getString("release_date");
                            double voteAverage = currentData.getLong("vote_average");
                            String poster  = Constants.HELPER_IMAGE+ currentData.getString("poster_path");
                            String image  =  Constants.HELPER_IMAGE+ currentData.getString("backdrop_path");
                            Movie movie = new Movie(id,title,
                                    overview,poster,image,releaseDate,voteAverage);
                            movieArrayList.add(movie);
                        }
                        initViews();
                        configureRecyclerView();
                        adapter = new MoviesListAdapter(id -> startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                                .putExtra(Constants.ID,id)));
                        rvListMovies.setAdapter(adapter);
                        adapter.setListMovies(movieArrayList);
                        adapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                });

            }
        });
    }




    private void configureRecyclerView() {
        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        rvListMovies.setLayoutManager(lm);
    }

    private void initViews(){
        rvListMovies = findViewById(R.id.rv_list_movies);
    }
}