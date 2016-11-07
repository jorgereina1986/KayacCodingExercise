package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jorgereina.kayak.R;
import com.jorgereina.kayak.adapters.KayakAdapter;
import com.jorgereina.kayak.models.Airline;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private ListView favoritesLv;
    private List<Airline> favoritesList;
    private Airline airline;
    private String airlineName;
    private KayakAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initViews();


        Intent getIntent = getIntent();
        airline = (Airline) getIntent.getSerializableExtra("Airline");

        airlineName = airline.getName();

        favoritesList = new ArrayList<>();
        favoritesList.add(airline);

        adapter = new KayakAdapter(getApplicationContext(), favoritesList);

        favoritesLv.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }

    private void initViews() {

        favoritesLv = (ListView) findViewById(R.id.favorites_lv);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }
}