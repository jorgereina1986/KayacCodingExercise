package com.jorgereina.kayak.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jorgereina.kayak.R;
import com.jorgereina.kayak.adapters.KayakAdapter;
import com.jorgereina.kayak.models.Airline;
import com.jorgereina.kayak.service.KayakService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_NAME = "airline_name";
    private static final String TAG_LOGO = "airline_logoUrl";
    private static final String TAG_PHONE = "airline_phone";
    private static final String TAG_WEBSITE = "airline_website";
    private ListView airlineLv;
    private KayakAdapter adapter;
    private List<Airline> airlineList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        networkCall();
        itemSelected();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        toolbar.setTitle("Home");
        return true;

    }

    private void networkCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.kayak.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KayakService service = retrofit.create(KayakService.class);
        Call<List<Airline>> airlineCall = service.airlineList();
        airlineCall.enqueue(new Callback<List<Airline>>() {
            @Override
            public void onResponse(Call<List<Airline>> call, Response<List<Airline>> response) {
//                Log.v("RESPONSE", "RESPONSE = " + response.body());

                airlineList = response.body();
                adapter = new KayakAdapter(getApplicationContext(), airlineList);
                airlineLv.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Airline>> call, Throwable t) {

            }
        });
    }

    private void initView() {
        airlineLv = (ListView) findViewById(R.id.airline_lv);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    private void itemSelected(){

        airlineLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Airline airline = airlineList.get(i);
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra(TAG_NAME, airline.getName());
                intent.putExtra(TAG_LOGO, airline.getLogoURL());
                intent.putExtra(TAG_PHONE, airline.getPhone());
                intent.putExtra(TAG_WEBSITE, airline.getSite());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favorite_btn:

                Intent intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
