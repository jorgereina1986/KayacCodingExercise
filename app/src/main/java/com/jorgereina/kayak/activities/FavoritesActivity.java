package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    private Toolbar favToolbar;

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
                Picasso.with(getApplicationContext()).load(BASE_LOGO_URL + model.getLogoURL()).into(logo);
            }
        };

        favoritesLv.setAdapter(fbAdapter);
        fbAdapter.notifyDataSetChanged();
    }

    private void initViews() {

        favoritesLv = (ListView) findViewById(R.id.favorites_lv);
        favToolbar = (Toolbar) findViewById(R.id.fav_toolbar);
        setSupportActionBar(favToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_menu, menu);
        favToolbar.setTitle("Favorites");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.home_btn:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }
}