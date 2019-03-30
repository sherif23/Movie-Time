package com.example.myapplication.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.myapplication.Models.Movie;
import com.example.myapplication.RetrofitUtilities.ApiClient;
import com.example.myapplication.RetrofitUtilities.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequests {

    private static ApiRequests instance;
    private MutableLiveData<List<Movie>> movies =  new MutableLiveData<List<Movie>> () ;
    private static APIInterface apiInterface;
    private List <Movie> dataSet = new ArrayList<Movie>();


    public static  ApiRequests getInstance() {
        if (instance == null) {
            instance = new ApiRequests() ;
            apiInterface = ApiClient.getClient().create(APIInterface.class);
        }
        return instance;

    }


    public void setMovies(MutableLiveData<List<Movie>> movies) {
        this.movies = movies;
    }

    public MutableLiveData<List<Movie>> getMovies(){
        movies.setValue(dataSet);
        return movies;
    }

    public  MutableLiveData<List<Movie>> getHomeMovies () {
        fetchMovies();
        return movies ;
    }
    

    public void  fetchMovies () {
        Call <List<Movie>> call = apiInterface.getMovies() ;

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, retrofit2.Response<List<Movie>>response) {
                dataSet = response.body();
                for ( int i = 0 ; i <dataSet.size(); i++ ) {
                }

            }

            @Override
            public void onFailure(Call<List<Movie>>call, Throwable t) {


            }
        });
    }


}
