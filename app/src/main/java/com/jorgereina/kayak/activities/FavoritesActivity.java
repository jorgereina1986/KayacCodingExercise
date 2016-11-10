package com.jorgereina.kayak.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jorgereina.kayak.R;
import com.jorgereina.kayak.adapters.KayakAdapter;
import com.jorgereina.kayak.models.Airline;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private static final String TAG_FAV_NAME = "airline_name";
    private static final String TAG_FAV_LOGO = "airline_logoUrl";
    private static final String TAG_FAV_PHONE = "airline_phone";
    private static final String TAG_FAV_WEBSITE = "airline_website";
    private static final String SAVE_LIST = "save_list";
    private static final String BASE_LOGO_URL = "http://www.kayak.com";

    private ListView favoritesLv;
    private List<Airline> favoritesList;
    private List<Airline> saveList;
    private KayakAdapter adapter;
    private Airline airline;

    private FirebaseListAdapter<Airline> fbAdapter;

    private String name;
    private String logo;
    private String website;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initViews();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Airlines");


        fbAdapter = new FirebaseListAdapter<Airline>(this, Airline.class, R.layout.row, databaseReference) {
            @Override
            protected void populateView(View v, Airline model, int position) {

                TextView name = (TextView) v.findViewById(R.id.airline_name_tv);
                name.setText(model.getName());

                ImageView logo = (ImageView) v.findViewById(R.id.airline_logo_iv);
                Picasso.with(getApplicationContext()).load(BASE_LOGO_URL+model.getLogoURL()).into(logo);
            }
        };

        favoritesLv.setAdapter(fbAdapter);
        fbAdapter.notifyDataSetChanged();
    }


    private void initViews() {

        favoritesLv = (ListView) findViewById(R.id.favorites_lv);

    }

}