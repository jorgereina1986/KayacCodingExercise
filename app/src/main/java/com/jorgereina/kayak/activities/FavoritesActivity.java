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

    private static final String TAG_FAV_NAME = "airline_name";
    private static final String TAG_FAV_LOGO = "airline_logoUrl";
    private static final String TAG_FAV_PHONE = "airline_phone";
    private static final String TAG_FAV_WEBSITE = "airline_website";
    private static final String AIRLINE_LIST = "airline_arraylist";
    private static final String POSITION = "position";
    private static final String SAVE_LIST = "save_list";

    private ListView favoritesLv;
    private List<Airline> favoritesList;
    private List<Airline> receivingList;
    private Airline airline;
    private KayakAdapter adapter;
    private int position;

    private String name;
    private String logo;
    private String website;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initViews();


        Intent getIntent = getIntent();
        name = getIntent.getStringExtra(TAG_FAV_NAME);
        logo = getIntent.getStringExtra(TAG_FAV_LOGO);
        phone = getIntent.getStringExtra(TAG_FAV_PHONE);
        website = getIntent.getStringExtra(TAG_FAV_WEBSITE);

        favoritesList = new ArrayList<>();
        favoritesList.add(new Airline(name, logo));

        adapter = new KayakAdapter(getApplicationContext(), favoritesList);

        favoritesLv.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }

    private void initViews() {

        favoritesLv = (ListView) findViewById(R.id.favorites_lv);

    }

}