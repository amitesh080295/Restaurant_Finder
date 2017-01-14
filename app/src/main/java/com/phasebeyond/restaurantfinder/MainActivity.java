package com.phasebeyond.restaurantfinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;
    private String category_selection;
    private TextView usernameTextView;
    private Button loungeButton;
    private Button cafeButton;
    private Button quickBitesButton;
    private Button bakeryButton;
    private Button fineDiningButton;
    private Button casualDiningButton;
    private Button placesOfInterestButton;
    private Spinner localitySpinner;
    private String locality;

    //Firebase Authentication
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize references to views
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        loungeButton = (Button) findViewById(R.id.loungeButton);
        cafeButton = (Button) findViewById(R.id.cafeButton);
        quickBitesButton = (Button) findViewById(R.id.quickBitesButton);
        bakeryButton = (Button) findViewById(R.id.bakeryButton);
        fineDiningButton = (Button) findViewById(R.id.fineDiningButton);
        casualDiningButton = (Button) findViewById(R.id.casualDiningButton);
        placesOfInterestButton = (Button) findViewById(R.id.pointsOfInterestButton);
        localitySpinner = (Spinner) findViewById(R.id.localitySpinner);

        //Array Adapter for Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.locality_array, R.layout.item_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localitySpinner.setAdapter(adapter);

        localitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                locality = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Another Interface Callback
            }
        });

        //Initialize Firebase Authentication
        mFirebaseAuth = FirebaseAuth.getInstance();

        //Authentication Page
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null)
                {
                    //User is signed in
                    onSignedInInitialize(user.getDisplayName());
                }
                else
                {
                    //User is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

        placesOfInterestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent placesofInterest = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(placesofInterest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                //sign out
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void onSignedInInitialize(String username){
        usernameTextView.setText(username);
    }

    public void cuisineButtonsClicked (View view){
        switch (view.getId()){

            case R.id.loungeButton:
                category_selection = "Lounge";
                break;
            case R.id.cafeButton:
                category_selection = "Cafe";
                break;
            case R.id.quickBitesButton:
                category_selection = "Quick Bites";
                break;
            case R.id.bakeryButton:
                category_selection = "Bakery";
                break;
            case R.id.fineDiningButton:
                category_selection = "Fine Dining";
                break;
            case R.id.casualDiningButton:
                category_selection = "Casual Dining";
                break;
            default:
                break;
        }

        Intent intent = new Intent(this, RestaurantListActivity.class);
        intent.putExtra("Category", category_selection);
        intent.putExtra("Locality", locality);
        startActivity(intent);

    }
}
