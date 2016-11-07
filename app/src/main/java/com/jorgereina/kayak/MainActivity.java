package com.jorgereina.kayak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView airlineLv;
    private ArrayAdapter adapter;
    private ArrayList<String> arlineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        networkCall();

    }

    private void networkCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.kayak.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KayakService service = retrofit.create(KayakService.class);

        Call<List<Airline>> airlines = service.airlineList();

        airlines.enqueue(new Callback<List<Airline>>() {
            @Override
            public void onResponse(Call<List<Airline>> call, Response<List<Airline>> response) {
                Log.v("RESPONSE", "RESPONSE = " + response.body().get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Airline>> call, Throwable t) {

            }
        });
    }

    private void initView() {
        airlineLv = (ListView) findViewById(R.id.airline_lv);
    }
}
