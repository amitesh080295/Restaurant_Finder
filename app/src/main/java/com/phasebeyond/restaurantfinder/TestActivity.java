package com.phasebeyond.restaurantfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String category = getIntent().getStringExtra("Category");
        String locality = getIntent().getStringExtra("Locality");

        testTextView = (TextView) findViewById(R.id.testTextView);
        String test = category + "_" + locality;
        testTextView.setText(test);
    }
}
