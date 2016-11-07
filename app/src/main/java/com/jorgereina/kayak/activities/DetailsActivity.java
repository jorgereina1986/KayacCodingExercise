package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgereina.kayak.R;
import com.jorgereina.kayak.models.Airline;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private static final String BASE_IMAGE_URL = "http://www.kayak.com";

    private TextView titleTv;
    private TextView phoneNumberTv;
    private TextView webSiteTv;
    private ImageView logoIv;
    private Airline airline;
    private String phoneNumber;
    private String webSite;
    private String title;
    private String logo;
    private Button addToFavBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();
        loadData();
        setClickListeners();

    }

    private void loadData() {

        Intent getIntent = getIntent();
        airline = (Airline) getIntent.getSerializableExtra("Airline");

        phoneNumber = airline.getPhone();
        webSite = airline.getSite();
        title = airline.getName();
        logo = BASE_IMAGE_URL + airline.getLogoURL();

        Picasso.with(getApplicationContext()).load(logo).resize(300,300).into(logoIv);
        titleTv.setText(title);
        phoneNumberTv.setText(phoneNumber);
        webSiteTv.setText(webSite);
    }

    private void setClickListeners() {
        phoneNumberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialPhoneNumber(phoneNumber);
            }
        });
        webSiteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWebPage(webSite);
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });
        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                intent.putExtra("Airline", airline);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        titleTv = (TextView) findViewById(R.id.details_title_tv);
        phoneNumberTv = (TextView) findViewById(R.id.details_phone_tv);
        webSiteTv = (TextView) findViewById(R.id.details_website_tv);
        logoIv = (ImageView) findViewById(R.id.details_logo_iv);
        addToFavBtn = (Button) findViewById(R.id.add_to_fav_btn);
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse("http://"+url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
