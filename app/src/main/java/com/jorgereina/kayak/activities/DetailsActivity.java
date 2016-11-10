package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgereina.kayak.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG_FAV_NAME = "airline_name";
    private static final String TAG_FAV_LOGO = "airline_logoUrl";
    private static final String TAG_FAV_PHONE = "airline_phone";
    private static final String TAG_FAV_WEBSITE = "airline_website";
    private static final String TAG_NAME = "airline_name";
    private static final String TAG_LOGO = "airline_logoUrl";
    private static final String TAG_PHONE = "airline_phone";
    private static final String TAG_WEBSITE = "airline_website";
    private static final String BASE_IMAGE_URL = "http://www.kayak.com";
    private static final String AIRLINE_LIST = "airline_arraylist";
    private static final String POSITION = "position";

    private TextView titleTv;
    private TextView phoneNumberTv;
    private TextView webSiteTv;
    private ImageView logoIv;
    private String name;
    private String logo;
    private String phone;
    private String website;
    private Button addToFavBtn;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();
        passingDataToViews();
        setClickListeners();

    }

    private void passingDataToViews(){
        Intent getIntent = getIntent();
        name = getIntent.getStringExtra(TAG_NAME);
        phone = getIntent.getStringExtra(TAG_PHONE);
        logo = getIntent.getStringExtra(TAG_LOGO);
        website = getIntent.getStringExtra(TAG_WEBSITE);

        Picasso.with(getApplicationContext()).load(BASE_IMAGE_URL+logo).resize(300,300).into(logoIv);
        titleTv.setText(name);
        phoneNumberTv.setText(phone);
        webSiteTv.setText(website);
    }

    private void setClickListeners() {
        phoneNumberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialPhoneNumber(phone);
            }
        });
        webSiteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWebPage(website);
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });
        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                intent.putExtra(TAG_NAME, name);
                intent.putExtra(TAG_LOGO, logo);
                intent.putExtra(TAG_PHONE, phone);
                intent.putExtra(TAG_WEBSITE, website);
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

    private void sendPref(){
        SharedPreferences savePref = getSharedPreferences("save",MODE_PRIVATE);
        SharedPreferences.Editor editor= savePref.edit();
        editor.putString(TAG_FAV_NAME, name);
        editor.putString(TAG_FAV_LOGO, logo);
        editor.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        sendPref();
        super.onSaveInstanceState(outState);
    }
}
