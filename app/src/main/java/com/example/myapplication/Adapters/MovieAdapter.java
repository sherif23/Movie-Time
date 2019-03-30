package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Models.Movie;
import com.example.myapplication.MovieDetails;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private List<Movie> movies ;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView ;
        public TextView textView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.im_movieposter) ;
            textView = (TextView) itemView.findViewById(R.id.tx_moviename) ;
        }
    }

    public MovieAdapter (List <Movie> movies) {
        this.movies = movies ;
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_homelist_item,viewGroup,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder myViewHolder, int i) {
        final Movie movie = movies.get(i);
        Context context = myViewHolder.imageView.getContext() ;
        myViewHolder.textView.setText(movie.getTitle());
        Picasso.with(context)
                .load(movie.getPoster())
                .fit()
                .placeholder(R.drawable.no_poster_available) // optional
                //.error(R.drawable.ic_error_fallback)         // optional
                .into(myViewHolder.imageView);
        final Context context2 = myViewHolder.itemView.getContext() ;
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("imdbId",movie.getImdbID());
                Intent myIntent = new Intent (context2, MovieDetails.class) ;
                myIntent.putExtras(bundle);
                context2.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updtateList (List <Movie> newMovies) {
        this.movies = newMovies ;
    }


}
