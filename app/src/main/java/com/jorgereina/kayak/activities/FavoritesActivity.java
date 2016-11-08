package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jorgereina.kayak.R;
import com.jorgereina.kayak.adapters.KayakAdapter;
import com.jorgereina.kayak.models.Airline;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private static final String AIRLINE_LIST = "airline_arraylist";
    private static final String POSITION = "position";
    private static final String SAVE_LIST = "save_list";

    private ListView favoritesLv;
    private List<Airline> favoritesList;
    private List<Airline> receivingList;
    private Airline airline;
    private KayakAdapter adapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initViews();

        if (savedInstanceState != null) {

            favoritesList = savedInstanceState.getParcelableArrayList(SAVE_LIST);

        }
        else {
            Intent getIntent = getIntent();
            receivingList = getIntent.getParcelableArrayListExtra(AIRLINE_LIST);
            position = getIntent.getIntExtra(POSITION, 0);


            favoritesList = new ArrayList<>();
            favoritesList.add(receivingList.get(position));

            adapter = new KayakAdapter(getApplicationContext(), favoritesList);

            favoritesLv.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }

    }

    private void initViews() {

        favoritesLv = (ListView) findViewById(R.id.favorites_lv);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(SAVE_LIST, (ArrayList<? extends Parcelable>) favoritesList);
        super.onSaveInstanceState(outState);
    }
}