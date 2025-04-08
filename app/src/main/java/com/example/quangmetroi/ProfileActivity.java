package com.example.quangmetroi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialButton btnEditProfile;
    private LinearLayout layoutHelp;
    private LinearLayout layoutTerms;
    private LinearLayout layoutLogout;
    private LinearLayout layoutDeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        layoutHelp = findViewById(R.id.layout_help);
        layoutTerms = findViewById(R.id.layout_terms);
        layoutLogout = findViewById(R.id.layout_logout);
        layoutDeleteAccount = findViewById(R.id.layout_delete_account);

        // Set up toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.profile);
        }

        // Set click listeners
        btnEditProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng chỉnh sửa hồ sơ sẽ được triển khai sau", Toast.LENGTH_SHORT).show();
        });

        layoutHelp.setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng trợ giúp sẽ được triển khai sau", Toast.LENGTH_SHORT).show();
        });

        layoutTerms.setOnClickListener(v -> {
            Toast.makeText(this, "Điều khoản và Chính sách sẽ được triển khai sau", Toast.LENGTH_SHORT).show();
        });

        layoutLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });

        layoutDeleteAccount.setOnClickListener(v -> {
            showDeleteAccountDialog();
        });
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.logout)
                .setMessage(R.string.logout_confirm)
                .setPositiveButton(R.string.confirm, (dialog, which) -> {
                    // Perform logout and return to login screen
                    Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginOptionsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void showDeleteAccountDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_account)
                .setMessage(R.string.delete_account_message)
                .setPositiveButton(R.string.confirm, (dialog, which) -> {
                    // Perform account deletion and return to login screen
                    Toast.makeText(this, R.string.delete_success, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginOptionsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
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