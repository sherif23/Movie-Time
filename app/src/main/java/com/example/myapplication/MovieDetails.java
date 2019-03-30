package com.example.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.Movie;
import com.example.myapplication.ViewModels.MovieDetailsViewModel;
import com.squareup.picasso.Picasso;


public class MovieDetails extends AppCompatActivity {

    private String imdbId ;
    private MovieDetailsViewModel movieDetailsViewModel ;
    private ImageView poster ;
    private TextView releaseDate ;
    private TextView rate ;
    private TextView runTime ;
    private TextView plot ;
    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        imdbId = getIntent().getStringExtra("imdbId");

        poster = (ImageView) findViewById(R.id.im_poster) ;
        releaseDate = (TextView) findViewById(R.id.tx_release_date) ;
        runTime = (TextView) findViewById(R.id.tx_run_time) ;
        rate = (TextView) findViewById(R.id.tx_rate) ;
        plot = (TextView) findViewById(R.id.tx_plot) ;
        progressBar = (ProgressBar) findViewById(R.id.pb_details) ;

        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.init();
        movieDetailsViewModel.getMovieDetails(imdbId).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                progressBar.setVisibility(View.VISIBLE);
                if (movie != null ) {
                    addViews(movie);
                }
            }
        });
    }


    public void addViews (Movie movie) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(movie.getTitle());
        Picasso.with(this)
                .load(movie.getPoster())
                .fit()
                .placeholder(R.drawable.no_poster_available)
                .into(poster);

        releaseDate.setText(movie.getReleased());
        runTime.setText(movie.getRuntime());
        rate.setText(movie.getImdbRating());
        plot.setText(movie.getPlot());
        progressBar.setVisibility(View.GONE);

    }
}
