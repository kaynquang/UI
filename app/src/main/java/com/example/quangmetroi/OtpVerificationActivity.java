package com.example.quangmetroi;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class OtpVerificationActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvPhone;
    private EditText etOtp1, etOtp2, etOtp3, etOtp4, etOtp5, etOtp6;
    private TextView tvResendTimer;
    private TextView tvResend;
    private MaterialButton btnVerify;

    private String phoneNumber;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Get the phone number from the intent
        phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");

        // Initialize views
        btnBack = findViewById(R.id.btn_back);
        tvPhone = findViewById(R.id.tv_phone);
        etOtp1 = findViewById(R.id.et_otp_1);
        etOtp2 = findViewById(R.id.et_otp_2);
        etOtp3 = findViewById(R.id.et_otp_3);
        etOtp4 = findViewById(R.id.et_otp_4);
        etOtp5 = findViewById(R.id.et_otp_5);
        etOtp6 = findViewById(R.id.et_otp_6);
        tvResendTimer = findViewById(R.id.tv_resend_timer);
        tvResend = findViewById(R.id.tv_resend);
        btnVerify = findViewById(R.id.btn_verify);

        // Display masked phone number
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            tvPhone.setText(maskPhoneNumber(phoneNumber));
        }

        // Set up OTP input fields
        setupOtpInputs();

        // Set click listeners
        btnBack.setOnClickListener(v -> finish());

        tvResend.setOnClickListener(v -> {
            Toast.makeText(this, "Đã gửi lại mã OTP", Toast.LENGTH_SHORT).show();
            startResendTimer();
        });

        btnVerify.setOnClickListener(v -> {
            String otp = getOtpFromFields();
            if (otp.length() == 6) {
                // For demo purposes, any 6-digit code is valid
                Intent intent = new Intent(this, BookingActivity.class);
                startActivity(intent);
                // Close all previous activities
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finishAffinity();
            } else {
                Toast.makeText(this, getString(R.string.invalid_otp), Toast.LENGTH_SHORT).show();
            }
        });

        // Start the countdown timer for OTP resend
        startResendTimer();
    }

    private void setupOtpInputs() {
        // Auto-focus handling for OTP fields
        etOtp1.addTextChangedListener(new OtpTextWatcher(etOtp1, null, etOtp2));
        etOtp2.addTextChangedListener(new OtpTextWatcher(etOtp2, etOtp1, etOtp3));
        etOtp3.addTextChangedListener(new OtpTextWatcher(etOtp3, etOtp2, etOtp4));
        etOtp4.addTextChangedListener(new OtpTextWatcher(etOtp4, etOtp3, etOtp5));
        etOtp5.addTextChangedListener(new OtpTextWatcher(etOtp5, etOtp4, etOtp6));
        etOtp6.addTextChangedListener(new OtpTextWatcher(etOtp6, etOtp5, null));
    }

    private String getOtpFromFields() {
        return etOtp1.getText().toString() +
                etOtp2.getText().toString() +
                etOtp3.getText().toString() +
                etOtp4.getText().toString() +
                etOtp5.getText().toString() +
                etOtp6.getText().toString();
    }

    private String maskPhoneNumber(String phone) {
        // Simple masking - we just show the last 3 digits
        if (phone.length() > 3) {
            return "+84 *** *** " + phone.substring(phone.length() - 3);
        }
        return phone;
    }

    private void startResendTimer() {
        tvResend.setEnabled(false);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvResendTimer.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvResendTimer.setText("0s");
                tvResend.setEnabled(true);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private class OtpTextWatcher implements TextWatcher {
        private final EditText currentField;
        private final EditText previousField;
        private final EditText nextField;

        OtpTextWatcher(EditText currentField, EditText previousField, EditText nextField) {
            this.currentField = currentField;
            this.previousField = previousField;
            this.nextField = nextField;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();
            if (text.length() == 1) {
                if (nextField != null) {
                    nextField.requestFocus();
                }
            } else if (text.isEmpty()) {
                if (previousField != null) {
                    previousField.requestFocus();
                }
            }
        }
    }
} 