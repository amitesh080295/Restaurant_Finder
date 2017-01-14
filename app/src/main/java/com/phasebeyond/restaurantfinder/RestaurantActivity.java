package com.phasebeyond.restaurantfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantActivity extends AppCompatActivity {

    String restaurant_name;
    String restaurant_address;
    String restaurant_locality;
    String restaurant_cuisines;
    String restaurant_cost;
    String restaurant_hours;
    String restaurant_phone_no;
    String restaurant_latitude;
    String restaurant_longitude;

    private TextView restaurantNameTextView;
    private TextView restaurantLocalityTextView;
    private TextView restaurantCostTextView;
    private TextView restaurantHoursTextView;
    private TextView restaurantCuisinesTextView;
    private TextView restaurantAddressTextView;
    private TextView restaurantPhoneNoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        restaurant_name = getIntent().getStringExtra("restaurant_name");
        restaurant_address = getIntent().getStringExtra("restaurant_address");
        restaurant_locality = getIntent().getStringExtra("restaurant_locality");
        restaurant_cuisines = getIntent().getStringExtra("restaurant_cuisines");
        restaurant_cost = getIntent().getStringExtra("restaurant_cost");
        restaurant_hours = getIntent().getStringExtra("restaurant_hours");
        restaurant_phone_no = getIntent().getStringExtra("restaurant_phone_no");
        restaurant_latitude = getIntent().getStringExtra("restaurant_latitude");
        restaurant_longitude = getIntent().getStringExtra("restaurant_longitude");

        restaurantNameTextView = (TextView) findViewById(R.id.restaurantNameTextView);
        restaurantNameTextView.setText(restaurant_name);

        restaurantLocalityTextView = (TextView) findViewById(R.id.restaurantLocalityTextView);
        restaurantLocalityTextView.setText(restaurant_locality);

        restaurantCostTextView = (TextView) findViewById(R.id.restaurantCostTextView);
        restaurantCostTextView.setText(restaurant_cost);

        restaurantHoursTextView = (TextView) findViewById(R.id.restaurantHoursTextView);
        restaurantHoursTextView.setText(restaurant_hours);

        restaurantCuisinesTextView = (TextView) findViewById(R.id.restaurantCuisinesTextView);
        restaurantCuisinesTextView.setText(restaurant_cuisines);

        restaurantAddressTextView = (TextView) findViewById(R.id.restaurantAddressTextView);
        restaurantAddressTextView.setText(restaurant_address);

        restaurantPhoneNoTextView = (TextView) findViewById(R.id.restaurantPhoneNoTextView);
        restaurantPhoneNoTextView.setText(restaurant_phone_no);

        Button showOnMapButton = (Button) findViewById(R.id.showOnMapButton);
        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRoute = new Intent(RestaurantActivity.this, DirectionMapActivity.class);
                showRoute.putExtra("Latitude", restaurant_latitude);
                showRoute.putExtra("Longitude", restaurant_longitude);
                showRoute.putExtra("Name", restaurant_name);
                startActivity(showRoute);
            }
        });
    }
}
