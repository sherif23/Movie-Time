package com.example.myapplication.RetrofitUtilities;


import com.example.myapplication.Models.Example;
import com.example.myapplication.Models.Movie;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/Movie/Search/c0fcb7d6cd3845969995020caa084e9e/up ")
    Call<List<Movie>> getMovies();

    @GET ("?apikey=db7f1f53")
    Call<Example> search(@Query("s") String name);

    @GET ("?apikey=db7f1f53")
    Call <Movie> getMovieDetails (@Query("i") String imdb);



   /* @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
