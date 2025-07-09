package com.example.farmerapp;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Fertilizers extends AppCompatActivity {

     ConstraintLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizers); // Make sure this matches your XML file

        // Initialize views
        mainLayout = findViewById(R.id.f_main);


        // Set click listener for demo action
        mainLayout.setOnClickListener(view -> {
            Snackbar.make(view, "Fertilizers Screen", Snackbar.LENGTH_SHORT).show();
        });
    }
}
