package com.example.myapplication.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.myapplication.Models.Movie;
import com.example.myapplication.repositories.MovieDetailsApiRequest;
import com.example.myapplication.repositories.SearchApiRequest;

public class MovieDetailsViewModel extends ViewModel {

    MovieDetailsApiRequest movieDetailsApiRequest ;
    MutableLiveData <Movie> movie ;

    public void init () {
        if (movie == null ) {
            movieDetailsApiRequest = MovieDetailsApiRequest.getInstance() ;
            movie = movieDetailsApiRequest.getMovie() ;
        }
    }

    public LiveData <Movie> getMovieDetails (String imdbId) {
        movieDetailsApiRequest.getMovieDetails(imdbId);
        return movie ;
    }
}
