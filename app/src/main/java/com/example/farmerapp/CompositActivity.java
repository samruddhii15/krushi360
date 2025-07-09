package com.example.farmerapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

public class CompositActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composit); // ✅ Must match your layout filename

        // Initialize views using findViewById
        mainLayout = findViewById(R.id.main);    // Root layout


        // Example Snackbar action
        mainLayout.setOnClickListener(view ->
                Snackbar.make(view, "Composit screen", Snackbar.LENGTH_SHORT).show()
        );
    }
}
