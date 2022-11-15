package com.yassir.moviesapp.Ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yassir.moviesapp.R;
import com.yassir.moviesapp.Utils.Constants;
import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {
    private long id;
    private String image,title,desc,poster,releaseDate;
    private double voteAverage;
    private RoundedImageView posterIV,imageIVBlurred,imageIV;
    private TextView titleTV,releaseDateTV,descTV,imdbVoteTV;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details);
        id = getIntent().getLongExtra(Constants.ID,0);
        initViews();
        getData();
    }

    private void initViews() {
        posterIV = findViewById(R.id.riv_details_poster);
        imageIVBlurred = findViewById(R.id.riv_details_blurred_image);
        imageIV = findViewById(R.id.riv_details_image);
        titleTV = findViewById(R.id.title_details);
        releaseDateTV = findViewById(R.id.release_date_details);
        descTV = findViewById(R.id.desc_details);
        imdbVoteTV = findViewById(R.id.imdb_vote_details);
    }
    @SuppressLint("SetTextI18n")
    private void installViews() {
        titleTV.setText(title);
        releaseDateTV.setText("Release Date : "+releaseDate);
        descTV.setText(desc);
        imdbVoteTV.setText(voteAverage+"");
        Glide.with(this)
                .load(poster)
                .into(posterIV);
        Glide.with(this)
                .load(image)
                .into(imageIV);
        Glide.with(this).load(image)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25)))
                .into(imageIVBlurred);

    }

    private void getData() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(Constants.API_URL_DETAILS_1+id+Constants.API_URL_DETAILS_2).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                assert response.body() != null;
                final String myResponse = response.body().string();
                runOnUiThread(() -> {
                    try {
                        JSONObject json = new JSONObject(myResponse);
                        image =  Constants.HELPER_IMAGE+ json.getString("backdrop_path");
                        title = json.getString("title");
                        desc = json.getString("overview");
                        poster =  Constants.HELPER_IMAGE+ json.getString("poster_path");
                        releaseDate = json.getString("release_date");
                        voteAverage = json.getDouble("vote_average");
                        installViews();

                    } catch (JSONException e) {
                        Toast.makeText(getBaseContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }


    public void exit(View view) {
        finish();
    }
}
