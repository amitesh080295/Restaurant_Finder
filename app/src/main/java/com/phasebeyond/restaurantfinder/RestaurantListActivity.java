package com.phasebeyond.restaurantfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private ListView mRestaurantListView;
    private RestaurantAdapter mRestaurantAdapter;
    private String category;
    private String locality;
    private String locality_category;
    private List<Restaurant> restaurants;
    private ProgressBar spinner;

    //Restaurant Info
    private String restaurant_name ;
    private String restaurant_address ;
    private String restaurant_locality ;
    private String restaurant_cuisines ;
    private String restaurant_cost ;
    private String restaurant_hours ;
    private String restaurant_phone_no ;
    private String restaurant_latitude ;
    private String restaurant_longitude ;

    //Firebase Database and Storage
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRestaurantsDatabaseReference;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mRestaurantPhotosStorageReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        category = getIntent().getStringExtra("Category");
        locality = getIntent().getStringExtra("Locality");
        locality_category = locality + "_" + category;


        spinner = (ProgressBar) findViewById(R.id.listLoad);
        spinner.setVisibility(View.VISIBLE);

        //Initialize Firebase Database and Storage
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        mRestaurantsDatabaseReference = mFirebaseDatabase.getReference().child(locality_category);
        //mRestaurantPhotosStorageReference = mFirebaseStorage.getReference().child("restaurant_photos");

        mRestaurantListView = (ListView) findViewById(R.id.restaurantListView);

        // Initialize message ListView and its adapter
        restaurants = new ArrayList<>();
        mRestaurantAdapter = new RestaurantAdapter(this, R.layout.item_restaurant, restaurants);
        mRestaurantListView.setAdapter(mRestaurantAdapter);

        attachDatabaseReadListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(restaurants.size() > 0)
        {
            spinner.setVisibility(View.GONE);
        }

        mRestaurantListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Iterate through the list of restaurants
                        int iterator ;
                        for (iterator = 0; iterator < restaurants.size(); iterator++){
                            if (iterator == i){ //Till the item position matches restaurant position in list
                                restaurant_name = restaurants.get(iterator).getName().toString(); //Get the desired restaurant object from the list and call the getName() method to fetch the name and convert to String
                                restaurant_address = restaurants.get(iterator).getAddress().toString();
                                restaurant_locality = restaurants.get(iterator).getLocality().toString();
                                restaurant_cuisines = restaurants.get(iterator).getCuisines().toString();
                                restaurant_cost = restaurants.get(iterator).getCost().toString();
                                restaurant_hours = restaurants.get(iterator).getHours().toString();
                                restaurant_phone_no = restaurants.get(iterator).getPhone_No().toString();
                                restaurant_latitude = restaurants.get(iterator).getLatitude().toString();
                                restaurant_longitude = restaurants.get(iterator).getLongitude().toString();
                                break;
                            }
                        }

                        Intent intent = new Intent(RestaurantListActivity.this, RestaurantActivity.class);
                        intent.putExtra("restaurant_name", restaurant_name);
                        intent.putExtra("restaurant_address", restaurant_address);
                        intent.putExtra("restaurant_locality", restaurant_locality);
                        intent.putExtra("restaurant_cuisines", restaurant_cuisines);
                        intent.putExtra("restaurant_cost", restaurant_cost);
                        intent.putExtra("restaurant_hours", restaurant_hours);
                        intent.putExtra("restaurant_phone_no", restaurant_phone_no);
                        intent.putExtra("restaurant_latitude", restaurant_latitude);
                        intent.putExtra("restaurant_longitude", restaurant_longitude);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        detachDatabaseReadListener();
    }

    private void attachDatabaseReadListener(){
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    mRestaurantAdapter.add(restaurant);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mRestaurantsDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener(){
        if (mChildEventListener != null) {
            mRestaurantsDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }


}
