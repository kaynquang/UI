package com.example.quangmetroi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PhoneLoginActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextInputEditText etPhone;
    private MaterialButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        // Initialize views
        btnBack = findViewById(R.id.btn_back);
        etPhone = findViewById(R.id.et_phone);
        btnContinue = findViewById(R.id.btn_continue);

        // Set click listeners
        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> {
            // Get phone number
            String phoneNumber = etPhone.getText().toString().trim();

            // Validate phone number
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                return;
            }

            // Show OTP verification screen
            Intent intent = new Intent(this, OtpVerificationActivity.class);
            intent.putExtra("PHONE_NUMBER", phoneNumber);
            startActivity(intent);
        });
    }
} 