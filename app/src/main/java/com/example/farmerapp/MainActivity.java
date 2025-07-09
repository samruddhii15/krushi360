package com.example.farmerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout s1, p1, w1, d1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        s1 = findViewById(R.id.cardSchemes);
        p1 = findViewById(R.id.cardProducts);
        w1 = findViewById(R.id.cardWeather);
        d1 = findViewById(R.id.cardDiseases);

        s1.setOnClickListener(this);
        p1.setOnClickListener(this);
        w1.setOnClickListener(this);
        d1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.cardSchemes) {
            startActivity(new Intent(this, scheme.class));
        } else if (id == R.id.cardProducts) {
            startActivity(new Intent(this, ProductActivity.class));
        } else if (id == R.id.cardWeather) {
            startActivity(new Intent(this, WeatherActivity.class));  // ✅ This must work
        } else if (id == R.id.cardDiseases) {
            startActivity(new Intent(this, DiseaseeActivity.class));

        }
    }
}
