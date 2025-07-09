package com.example.farmerapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    TextView dateTimeText, locationText, weatherText, futureForecastText, soilMoistureText, windText, humidityText;
    Button refreshButton;
    LocationManager locationManager;

    final String API_KEY = "your_api_key_here"; // 🔑 Replace this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // UI references
        dateTimeText = findViewById(R.id.dateTimeText);
        locationText = findViewById(R.id.locationText);
        weatherText = findViewById(R.id.weatherText);
        futureForecastText = findViewById(R.id.futureForecastText);
        soilMoistureText = findViewById(R.id.soilMoistureText);
        windText = findViewById(R.id.windText);
        humidityText = findViewById(R.id.humidityText);
        refreshButton = findViewById(R.id.refreshButton);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            fetchWeather();
        }

        refreshButton.setOnClickListener(v -> fetchWeather());
    }

    @SuppressLint("MissingPermission")
    private void fetchWeather() {
        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            if (location == null) {
                locationText.setText("📍 Turn on Location Services");
                return;
            }

            double lat = location.getLatitude();
            double lon = location.getLongitude();

            // Fetch data
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String urlString = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat +
                    "&lon=" + lon + "&appid=" + API_KEY + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) result.append(line);

            JSONObject jsonObject = new JSONObject(result.toString());

            // Extract data
            String city = jsonObject.getJSONObject("city").getString("name");
            String country = jsonObject.getJSONObject("city").getString("country");
            locationText.setText("📍 " + city + ", " + country);

            String currentTime = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.getDefault()).format(new Date());
            dateTimeText.setText("🕒 Last Updated: " + currentTime);

            JSONArray list = jsonObject.getJSONArray("list");

            JSONObject current = list.getJSONObject(0);
            JSONObject forecast1hr = list.getJSONObject(1);

            // Current data
            String mainWeather = current.getJSONArray("weather").getJSONObject(0).getString("main");
            double temp = current.getJSONObject("main").getDouble("temp");
            double wind = current.getJSONObject("wind").getDouble("speed");
            int humidity = current.getJSONObject("main").getInt("humidity");

            weatherText.setText("☀️ Weather: " + mainWeather + " " + temp + "°C");
            windText.setText("💨 Wind Speed: " + wind + " m/s");
            humidityText.setText("💧 Humidity: " + humidity + "%");

            // 1-hour forecast
            String weatherNext = forecast1hr.getJSONArray("weather").getJSONObject(0).getString("main");
            double tempNext = forecast1hr.getJSONObject("main").getDouble("temp");
            futureForecastText.setText("🔮 In 1 Hour: " + weatherNext + " " + tempNext + "°C");

            // Fake soil moisture (you can integrate real soil API later)
            soilMoistureText.setText("🌱 Soil Moisture: 29% (estimated)");

        } catch (Exception e) {
            e.printStackTrace();
            weatherText.setText("❌ Failed to fetch weather");
            locationText.setText("❌ Location unavailable");
        }
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fetchWeather();
        }
    }
}
