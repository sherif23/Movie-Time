package com.example.myapplication.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.myapplication.Models.Example;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.RetrofitUtilities.APIInterface;
import com.example.myapplication.RetrofitUtilities.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchApiRequest {

    private static SearchApiRequest instance;
    private MutableLiveData<List<Movie>> movies =  new MutableLiveData<List<Movie>> () ;
    private MutableLiveData <String> textViewMass = new MutableLiveData<String>() ;
    private static APIInterface apiInterface;
    private List <Movie> dataSet = new ArrayList<Movie>();


    public static  SearchApiRequest getInstance() {
        if (instance == null) {
            instance = new SearchApiRequest() ;
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


    public MutableLiveData<String> getTextViewMass() {
        return textViewMass;
    }

    public MutableLiveData<List<Movie>> getsearchedMovies(String name){
        searchMovie(name);
        return movies;
    }

    public void  searchMovie (String name) {
        dataSet = new ArrayList<Movie>();

        if (name == null || name.equals("")) {
            textViewMass.setValue("Search");
            movies.setValue(dataSet);
            return;
        }

        textViewMass.setValue("Searching ...");

        Call <Example> call = apiInterface.search(name) ;
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example>response) {

                if (response.body().getResponse().equals("True")){
                    dataSet = response.body().getSearch();
                    movies.setValue(dataSet);
                    textViewMass.setValue("Done") ;
                } else {
                    movies.setValue(dataSet);
                    textViewMass.setValue("Not Found...");
                }
            }

            @Override
            public void onFailure(Call<Example>call, Throwable t) {
            }
        });
    }
}
