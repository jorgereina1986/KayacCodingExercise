package com.jorgereina.kayak.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private static final String SAVE_LIST = "save_list";

    private ListView favoritesLv;
    private List<Airline> favoritesList;
    private List<Airline> saveList;
    private KayakAdapter adapter;
    private Airline airline;
//    private SharedPreferences preferences;

    private String name;
    private String logo;
    private String website;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initViews();
/***
//        getPref();

//        Intent getIntent = getIntent();
//        name = getIntent.getStringExtra(TAG_FAV_NAME);
//        logo = getIntent.getStringExtra(TAG_FAV_LOGO);
//        phone = getIntent.getStringExtra(TAG_FAV_PHONE);
//        website = getIntent.getStringExtra(TAG_FAV_WEBSITE);

***/

        SharedPreferences loadPref = getSharedPreferences("save",MODE_PRIVATE);
        String namePref = loadPref.getString(TAG_FAV_NAME, null);
        String logoPref = loadPref.getString(TAG_FAV_LOGO, null);

//        if (namePref!=null || logoPref!=null){
//            Toast.makeText(getApplicationContext(),"Nodatafound", Toast.LENGTH_SHORT).show();
//        }
//        else {
            favoritesList = new ArrayList<>();
            favoritesList.add(new Airline(namePref, logoPref));
            adapter = new KayakAdapter(getApplicationContext(), favoritesList);
            favoritesLv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Log.v("LIST", favoritesList.get(0).getName());
//        }
    }

//    private void saveData(){
//
//        preferences = getSharedPreferences("MyData",MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        for (int i = 0; i < adapter.getCount(); i++) {
//
//            editor.putString("name"+i, name);
//            editor.putString("logo"+i, logo);
//        }
//        editor.commit();
//    }
//
//    private void loadData() {
//
//        ArrayList<Airline> arr = new ArrayList<>();
//
//
//        preferences = getSharedPreferences("MyData", MODE_PRIVATE);
//
//        for (int i = 0; ; i++) {
//
//            String prefName = preferences.getString("name", null);
//            String prefLogo = preferences.getString("logo", null);
//            favoritesList.add(new Airline(prefName,prefLogo));
//        }
//
//
//    }

    private void initViews() {

        favoritesLv = (ListView) findViewById(R.id.favorites_lv);

    }

    private void getPref(){
        SharedPreferences loadPref = getSharedPreferences("save",MODE_PRIVATE);
        String name = loadPref.getString(TAG_FAV_NAME, null);
        String logo = loadPref.getString(TAG_FAV_LOGO, null);
    }
}