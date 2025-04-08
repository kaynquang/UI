package com.example.quangmetroi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class FindingDriverActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvPrice;
    private TextView tvTime;
    private MaterialButton btnCancel;
    
    private String pickup;
    private String dropoff;
    private String price;
    private String vehicleType;
    
    private static final int FINDING_DELAY = 5000; // 5 seconds delay for demo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finding_driver);
        
        // Get intent extras
        Intent intent = getIntent();
        pickup = intent.getStringExtra("PICKUP");
        dropoff = intent.getStringExtra("DROPOFF");
        price = intent.getStringExtra("PRICE");
        vehicleType = intent.getStringExtra("VEHICLE_TYPE");
        
        // Initialize views
        btnBack = findViewById(R.id.btn_back);
        tvPrice = findViewById(R.id.tv_price);
        tvTime = findViewById(R.id.tv_time);
        btnCancel = findViewById(R.id.btn_cancel);
        
        // Set price
        if (price != null) {
            tvPrice.setText(price);
        }
        
        // Set arrival time based on vehicle type
        tvTime.setText(vehicleType != null && vehicleType.equals("car") ? "5 phút" : "3 phút");
        
        // Set click listeners
        btnBack.setOnClickListener(v -> finish());
        btnCancel.setOnClickListener(v -> finish());
        
        // For demo, automatically find a driver after delay
        new Handler().postDelayed(() -> {
            // Driver found, navigate to rating screen
            Intent ratingIntent = new Intent(FindingDriverActivity.this, RatingActivity.class);
            startActivity(ratingIntent);
            finish();
        }, FINDING_DELAY);
    }
} 