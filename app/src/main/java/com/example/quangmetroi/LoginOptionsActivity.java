package com.example.quangmetroi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class LoginOptionsActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton btnPhoneLogin;
    private MaterialButton btnGmailLogin;
    private MaterialButton btnFacebookLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);

        // Initialize views
        btnPhoneLogin = findViewById(R.id.btn_phone_login);
        btnGmailLogin = findViewById(R.id.btn_gmail_login);
        btnFacebookLogin = findViewById(R.id.btn_facebook_login);

        // Set click listeners
        btnPhoneLogin.setOnClickListener(this);
        btnGmailLogin.setOnClickListener(this);
        btnFacebookLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_phone_login) {
            // Start phone login activity
            Intent intent = new Intent(this, PhoneLoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn_gmail_login) {
            // Show a toast for now since Gmail login is not implemented yet
            Toast.makeText(this, "Đăng nhập Gmail sẽ được triển khai sau", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btn_facebook_login) {
            // Show a toast for now since Facebook login is not implemented yet
            Toast.makeText(this, "Đăng nhập Facebook sẽ được triển khai sau", Toast.LENGTH_SHORT).show();
        }
    }
} 