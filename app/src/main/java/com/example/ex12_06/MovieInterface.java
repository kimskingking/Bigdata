package com.example.ex12_06;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieInterface {

    @GET("photos/")
    Call<List<Movie>> doGetMovie();
}
