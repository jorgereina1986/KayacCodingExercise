package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorgereina.kayak.R;
import com.jorgereina.kayak.models.Airline;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private static final String BASE_IMAGE_URL = "http://www.kayak.com";
    private TextView title;
    private TextView phoneNumber;
    private TextView webSite;
    private ImageView logo;
    private int itemPosition;
    private Airline airline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();

        Intent getIntent = getIntent();
        airline = (Airline) getIntent.getSerializableExtra("Airline");
        Picasso.with(getApplicationContext()).load(BASE_IMAGE_URL + airline.getLogoURL()).into(logo);
        title.setText(airline.getName());
        phoneNumber.setText(airline.getPhone());
        webSite.setText(airline.getSite());


    }

    private void initViews() {
        title = (TextView) findViewById(R.id.details_title_tv);
        phoneNumber = (TextView) findViewById(R.id.details_phone_tv);
        webSite = (TextView) findViewById(R.id.details_website_tv);
        logo = (ImageView) findViewById(R.id.details_logo_iv);
    }

}
