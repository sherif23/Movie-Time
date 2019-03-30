package com.example.myapplication.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.myapplication.Models.Movie;
import com.example.myapplication.repositories.ApiRequests;

import java.util.List;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Movie>> movies;
    private ApiRequests apiRequests;

    public void init () {
        if (movies == null ){
            apiRequests = ApiRequests.getInstance();
            movies = apiRequests.getHomeMovies();

        }
    }

    public LiveData<List<Movie>> getMoviesList () {
        return movies ;
    }




}
