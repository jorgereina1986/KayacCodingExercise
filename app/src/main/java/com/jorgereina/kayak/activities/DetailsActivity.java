package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private int itemPosition;
    private Airline airline;
    private String phoneNumber;
    private String webSite;
    private String title;
    private String logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();

        Intent getIntent = getIntent();
        airline = (Airline) getIntent.getSerializableExtra("Airline");

        phoneNumber = airline.getPhone();
        webSite = airline.getSite();
        title = airline.getName();
        logo = BASE_IMAGE_URL + airline.getLogoURL();

        Picasso.with(getApplicationContext()).load(logo).into(logoIv);
        titleTv.setText(title);
        phoneNumberTv.setText(phoneNumber);
        webSiteTv.setText(webSite);

        setIntents();

    }

    private void setIntents() {
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
    }

    private void initViews() {
        titleTv = (TextView) findViewById(R.id.details_title_tv);
        phoneNumberTv = (TextView) findViewById(R.id.details_phone_tv);
        webSiteTv = (TextView) findViewById(R.id.details_website_tv);
        logoIv = (ImageView) findViewById(R.id.details_logo_iv);
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
