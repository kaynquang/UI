package com.example.quangmetroi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class BookingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputEditText etPickup;
    private TextInputEditText etDropoff;
    private RadioGroup rgVehicle;
    private RadioButton rbCar;
    private RadioButton rbBike;
    private RadioGroup rgBookingType;
    private RadioButton rbNormal;
    private RadioButton rbGrabNow;
    private TextView tvPrice;
    private MaterialButton btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        etPickup = findViewById(R.id.et_pickup);
        etDropoff = findViewById(R.id.et_dropoff);
        rgVehicle = findViewById(R.id.rg_vehicle);
        rbCar = findViewById(R.id.rb_car);
        rbBike = findViewById(R.id.rb_bike);
        rgBookingType = findViewById(R.id.rg_booking_type);
        rbNormal = findViewById(R.id.rb_normal);
        rbGrabNow = findViewById(R.id.rb_grab_now);
        tvPrice = findViewById(R.id.tv_price);
        btnBook = findViewById(R.id.btn_book);

        // Set up toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.booking);
        }

        // Set up radio groups
        rgVehicle.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_car) {
                tvPrice.setText("120.000 VND");
            } else if (checkedId == R.id.rb_bike) {
                tvPrice.setText("60.000 VND");
            }
        });

        // Set up book button
        btnBook.setOnClickListener(v -> {
            String pickup = etPickup.getText().toString().trim();
            String dropoff = etDropoff.getText().toString().trim();

            if (pickup.isEmpty()) {
                etPickup.setError("Vui lòng nhập điểm đón");
                return;
            }

            if (dropoff.isEmpty()) {
                etDropoff.setError("Vui lòng nhập điểm đến");
                return;
            }

            // Start finding driver activity
            Intent intent = new Intent(this, FindingDriverActivity.class);
            intent.putExtra("PICKUP", pickup);
            intent.putExtra("DROPOFF", dropoff);
            intent.putExtra("PRICE", tvPrice.getText().toString());
            intent.putExtra("VEHICLE_TYPE", rbCar.isChecked() ? "car" : "bike");
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 