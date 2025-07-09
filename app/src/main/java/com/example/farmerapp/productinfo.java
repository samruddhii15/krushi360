package com.example.farmerapp;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class productinfo extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo); // This must match your XML layout filename

        // Initialize views
        mainLayout = findViewById(R.id.main);


        // Set up toolbar


         // Optional, already set in XML

        // Simple action on click anywhere
        mainLayout.setOnClickListener(view -> {
            Snackbar.make(view, "Product Info Screen", Snackbar.LENGTH_SHORT).show();
        });
    }
}
