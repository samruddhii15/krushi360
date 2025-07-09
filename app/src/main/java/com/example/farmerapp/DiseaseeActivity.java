package com.example.farmerapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class DiseaseeActivity extends AppCompatActivity {

    EditText searchBar;
    LinearLayout cardPesticides, cardHerbicides, cardFertilizers, cardCompost,
            Rust, Downy_mildew, Anthracnose, Canker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diseasee);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.d_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Init views
        searchBar = findViewById(R.id.searchProductBar);
        cardPesticides = findViewById(R.id.cardPesticides);
        cardHerbicides = findViewById(R.id.cardHerbicides);
        cardFertilizers = findViewById(R.id.cardFertilizers);
        cardCompost = findViewById(R.id.cardCompost);
        Rust = findViewById(R.id.Rust);
        Downy_mildew = findViewById(R.id.Downy_mildew);
        Anthracnose = findViewById(R.id.Anthracnose);
        Canker = findViewById(R.id.Canker);

        // Card clicks
        setCardClick(cardPesticides, "Blight", R.id.cardPesticides);
        setCardClick(cardHerbicides, "Root Rot", R.id.cardHerbicides);
        setCardClick(cardFertilizers, "Powdery Mildew", R.id.cardFertilizers);
        setCardClick(cardCompost, "Leaf Spot", R.id.cardCompost);
        setCardClick(Rust, "Rust", R.id.Rust);
        setCardClick(Downy_mildew, "Downy Mildew", R.id.Downy_mildew);
        setCardClick(Anthracnose, "Anthracnose", R.id.Anthracnose);
        setCardClick(Canker, "Canker", R.id.Canker);

        // Search logic using EditText
        Map<LinearLayout, String> diseaseMap = new HashMap<>();
        diseaseMap.put(cardPesticides, "blight");
        diseaseMap.put(cardHerbicides, "root rot");
        diseaseMap.put(cardFertilizers, "powdery mildew");
        diseaseMap.put(cardCompost, "leaf spot");
        diseaseMap.put(Rust, "rust");
        diseaseMap.put(Downy_mildew, "downy mildew");
        diseaseMap.put(Anthracnose, "anthracnose");
        diseaseMap.put(Canker, "canker");

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().toLowerCase().trim();
                for (Map.Entry<LinearLayout, String> entry : diseaseMap.entrySet()) {
                    if (entry.getValue().contains(keyword)) {
                        entry.getKey().setVisibility(View.VISIBLE);
                    } else {
                        entry.getKey().setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void setCardClick(LinearLayout card, String title, int viewId) {
        card.setOnClickListener(v -> {
            String message = getDiseaseInfo(viewId);
            View dialogView = getLayoutInflater().inflate(R.layout.custom_disease_dialog, null);
            AlertDialog dialog = new AlertDialog.Builder(DiseaseeActivity.this)
                    .setView(dialogView)
                    .create();

            TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
            TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
            ImageView dialogIcon = dialogView.findViewById(R.id.dialogIcon);
            Button dialogButton = dialogView.findViewById(R.id.dialogButton);

            dialogTitle.setText(title);
            dialogMessage.setText(message);
            dialogIcon.setImageResource(R.drawable.ic_disease); // Change if needed
            dialogButton.setOnClickListener(btn -> dialog.dismiss());

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.show();
        });
    }

    private String getDiseaseInfo(int id) {
        if (id == R.id.cardPesticides) {
            return "Fungal disease causing brown spots on leaves.\n\n👉 Solution: Use copper-based fungicides.";
        } else if (id == R.id.cardHerbicides) {
            return "Roots decay due to overwatering and poor drainage.\n\n👉 Solution: Improve soil drainage, use fungicide.";
        } else if (id == R.id.cardFertilizers) {
            return "White powdery fungal growth on leaves and stems.\n\n👉 Solution: Use neem oil or sulfur-based spray.";
        } else if (id == R.id.cardCompost) {
            return "Spots on leaves caused by bacteria/fungi.\n\n👉 Solution: Remove infected leaves, apply copper-based fungicides.";
        } else if (id == R.id.Rust) {
            return "Orange or brown pustules on leaves.\n\n👉 Solution: Use mancozeb or sulfur-based fungicides.";
        } else if (id == R.id.Downy_mildew) {
            return "Gray or yellow patches on the underside of leaves.\n\n👉 Solution: Apply systemic fungicides and ensure good airflow.";
        } else if (id == R.id.Anthracnose) {
            return "Dark, sunken lesions on leaves, stems, or fruits.\n\n👉 Solution: Prune infected parts, use copper fungicides.";
        } else if (id == R.id.Canker) {
            return "Sunken, dead areas on stems or branches.\n\n👉 Solution: Prune cankered parts and disinfect tools.";
        } else {
            return "No information available.";
        }
    }
}
