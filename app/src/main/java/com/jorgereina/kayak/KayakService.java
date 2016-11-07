package com.jorgereina.kayak;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KayakService {

    //https://www.kayak.com
    @GET("/h/mobileapis/directory/airlines")
    Call<List<Airline>> airlineList();
}
