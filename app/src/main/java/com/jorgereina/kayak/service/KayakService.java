package com.jorgereina.kayak.service;

import com.jorgereina.kayak.models.Airline;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KayakService {

    //https://www.kayak.com
    @GET("/h/mobileapis/directory/airlines")
    Call<List<Airline>> airlineList();
}
