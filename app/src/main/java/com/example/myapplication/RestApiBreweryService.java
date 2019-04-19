package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApiBreweryService {
    @GET("breweries")
    Call<List<Brewery>> getListBreweries();
}
