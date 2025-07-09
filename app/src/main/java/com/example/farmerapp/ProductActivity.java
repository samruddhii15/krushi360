package com.example.farmerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    LinearLayout cardPesticides, cardHerbicides, cardFertilizers, cardCompost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // Find views
        cardPesticides = findViewById(R.id.cardPesticides);
        cardHerbicides = findViewById(R.id.cardHerbicides);
        cardFertilizers = findViewById(R.id.cardFertilizers);
        cardCompost = findViewById(R.id.cardCompost);

        // Set click listeners
        cardPesticides.setOnClickListener(v -> {
                    Intent i = new Intent(ProductActivity.this, productinfo.class);
                    startActivity(i);
                });

        cardHerbicides.setOnClickListener(v ->
        {
            Intent i = new Intent(ProductActivity.this, herbicides.class);
            startActivity(i);
        });

        cardFertilizers.setOnClickListener(v ->
        {
            Intent i = new Intent(ProductActivity.this, Fertilizers.class);
            startActivity(i);
        });

        cardCompost.setOnClickListener(v ->
        {
            Intent i = new Intent(ProductActivity.this, CompositActivity.class);
            startActivity(i);
        });
    }
}
