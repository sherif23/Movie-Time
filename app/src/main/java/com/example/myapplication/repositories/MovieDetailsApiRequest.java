package com.example.myapplication.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.myapplication.Models.Example;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.RetrofitUtilities.APIInterface;
import com.example.myapplication.RetrofitUtilities.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

public class MovieDetailsApiRequest extends ViewModel {

    private static MovieDetailsApiRequest instance ;
    private static MutableLiveData <Movie> movieMutableLiveData  ;
    private static APIInterface apiInterface;

    public static  MovieDetailsApiRequest getInstance() {
        if (instance == null) {
            instance = new MovieDetailsApiRequest() ;
            apiInterface = ApiClient.getClient().create(APIInterface.class);
        }
        movieMutableLiveData = new MutableLiveData<Movie>() ;
        return instance;
    }

    public MutableLiveData<Movie> getMovie () {
        return movieMutableLiveData;
    }

    public void getMovieDetails (String imdbId) {
        Call<Movie> call = apiInterface.getMovieDetails(imdbId) ;
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, retrofit2.Response<Movie>response) {
                movieMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie>call, Throwable t) {
            }
        });
    }
}
