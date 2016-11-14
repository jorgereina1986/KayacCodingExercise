package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jorgereina.kayak.R;
import com.jorgereina.kayak.models.Airline;
import com.squareup.picasso.Picasso;

public class FavoritesActivity extends AppCompatActivity {

    private static final String BASE_LOGO_URL = "http://www.kayak.com";

    private ListView favoritesLv;

    private FirebaseListAdapter<Airline> fbAdapter;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Button homeBtn = (Button) findViewById(R.id.favorite_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return super.onOptionsItemSelected(item);
    }
}