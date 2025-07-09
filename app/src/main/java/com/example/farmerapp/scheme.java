package com.example.farmerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class scheme extends AppCompatActivity implements View.OnClickListener {

    EditText searchBar;
    LinearLayout schemeList;
    LinearLayout sc1,sc2,sc3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scheme);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sc1 = findViewById(R.id.scheme1);
        sc2 = findViewById(R.id.scheme2);
        sc3 = findViewById(R.id.scheme3);


        // Set click listeners
        sc1.setOnClickListener(this);
        sc2.setOnClickListener(this);
        sc3.setOnClickListener(this);


        // 🔍 Initialize views
        searchBar = findViewById(R.id.searchBar);
        schemeList = findViewById(R.id.schemeList);

        // 🔎 Set up live filtering
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterSchemes(s.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // not used
            }
        });
    }

    private void filterSchemes(String query) {
        int childCount = schemeList.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = schemeList.getChildAt(i);

            if (view instanceof LinearLayout) {
                LinearLayout card = (LinearLayout) view;

                // Find TextView inside the card
                if (card.getChildCount() >= 2 && card.getChildAt(1) instanceof TextView) {
                    TextView text = (TextView) card.getChildAt(1);
                    String schemeName = text.getText().toString().toLowerCase();

                    if (schemeName.contains(query)) {
                        card.setVisibility(View.VISIBLE);
                    } else {
                        card.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.scheme1) {
            startActivity(new Intent(this, scheme2.class));
        } else if (id == R.id.scheme2) {
            // startActivity(new Intent(this, ProductActivity.class));
        } else if (id == R.id.scheme3) {
            // startActivity(new Intent(this, WeatherActivity.class));
        }

    }
}
