package com.example.quangmetroi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RatingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RatingBar ratingBar;
    private TextInputEditText etComment;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        ratingBar = findViewById(R.id.rating_bar);
        etComment = findViewById(R.id.et_comment);
        btnSubmit = findViewById(R.id.btn_submit);

        // Set up toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.rate_trip);
        }

        // Set click listener for submit button
        btnSubmit.setOnClickListener(v -> {
            // Get rating and comment
            float rating = ratingBar.getRating();
            String comment = etComment.getText().toString().trim();

            // Check if rating is valid
            if (rating == 0) {
                Toast.makeText(this, "Vui lòng đánh giá tài xế", Toast.LENGTH_SHORT).show();
                return;
            }

            // Submit rating (just a demo)
            Toast.makeText(this, R.string.rating_success, Toast.LENGTH_SHORT).show();

            // Go back to home (in this case, to the profile)
            Intent intent = new Intent(RatingActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
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