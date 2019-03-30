package com.example.myapplication.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.myapplication.Models.Movie;
import com.example.myapplication.repositories.ApiRequests;
import com.example.myapplication.repositories.SearchApiRequest;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData <String> textViewMass ;
    private SearchApiRequest apiRequests;

    public void init () {
        if (movies == null ) {
            apiRequests = SearchApiRequest.getInstance();
            movies = apiRequests.getMovies() ;
            textViewMass = apiRequests.getTextViewMass() ;
        }

    }

    public LiveData<List<Movie>> getMoviesList () {
        return movies ;
    }

    public LiveData <String> getTextViewMass () {return textViewMass ;}

    public void search (String name ) {
        movies= apiRequests.getsearchedMovies(name);
    }

}
