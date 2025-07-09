package com.example.farmerapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

public class herbicides extends AppCompatActivity {

    private ConstraintLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herbicides); // ✅ Must match XML filename



        mainLayout = findViewById(R.id.main);     // Root layout ID


        // Example click action
        mainLayout.setOnClickListener(view ->
                Snackbar.make(view, "Herbicides Screen", Snackbar.LENGTH_SHORT).show()
        );
    }
}
