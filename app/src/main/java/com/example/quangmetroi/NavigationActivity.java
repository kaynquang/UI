package com.example.quangmetroi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NavigationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize recycler view
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create data for adapter
        List<NavigationItem> items = getNavigationItems();

        // Set up adapter
        adapter = new NavigationAdapter(items, item -> {
            // Handle navigation item click
            Intent intent = null;
            
            switch (item.getId()) {
                case 1: // Splash Screen
                    intent = new Intent(this, MainActivity.class);
                    break;
                case 2: // Login Options
                    intent = new Intent(this, LoginOptionsActivity.class);
                    break;
                case 3: // Phone Login
                    intent = new Intent(this, PhoneLoginActivity.class);
                    break;
                case 4: // OTP Verification
                    intent = new Intent(this, OtpVerificationActivity.class);
                    intent.putExtra("PHONE_NUMBER", "0123456789");
                    break;
                case 5: // Booking Screen
                    intent = new Intent(this, BookingActivity.class);
                    break;
                case 6: // Finding Driver
                    intent = new Intent(this, FindingDriverActivity.class);
                    intent.putExtra("PICKUP", "Landmark 81, HCMC");
                    intent.putExtra("DROPOFF", "Saigon Center, HCMC");
                    intent.putExtra("PRICE", "120.000 VND");
                    intent.putExtra("VEHICLE_TYPE", "car");
                    break;
                case 7: // Rating Screen
                    intent = new Intent(this, RatingActivity.class);
                    break;
                case 8: // Profile Screen
                    intent = new Intent(this, ProfileActivity.class);
                    break;
            }
            
            if (intent != null) {
                startActivity(intent);
            }
        });
        
        recyclerView.setAdapter(adapter);
    }

    private List<NavigationItem> getNavigationItems() {
        List<NavigationItem> items = new ArrayList<>();
        
        items.add(new NavigationItem(1, "Màn hình Splash", "Màn hình khởi động ứng dụng"));
        items.add(new NavigationItem(2, "Lựa chọn đăng nhập", "Chọn phương thức đăng nhập"));
        items.add(new NavigationItem(3, "Đăng nhập bằng SĐT", "Nhập số điện thoại để đăng nhập"));
        items.add(new NavigationItem(4, "Xác minh OTP", "Nhập mã OTP để xác minh"));
        items.add(new NavigationItem(5, "Đặt xe", "Nhập thông tin chuyến đi"));
        items.add(new NavigationItem(6, "Tìm tài xế", "Đang tìm tài xế phù hợp"));
        items.add(new NavigationItem(7, "Đánh giá chuyến đi", "Đánh giá tài xế và chuyến đi"));
        items.add(new NavigationItem(8, "Trang cá nhân", "Thông tin và cài đặt tài khoản"));
        
        return items;
    }

    static class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

        private final List<NavigationItem> items;
        private final OnItemClickListener listener;

        NavigationAdapter(List<NavigationItem> items, OnItemClickListener listener) {
            this.items = items;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_navigation, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            NavigationItem item = items.get(position);
            holder.bind(item, listener);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView tvTitle;
            private final TextView tvDescription;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_title);
                tvDescription = itemView.findViewById(R.id.tv_description);
            }

            void bind(NavigationItem item, OnItemClickListener listener) {
                tvTitle.setText(item.getTitle());
                tvDescription.setText(item.getDescription());
                itemView.setOnClickListener(v -> listener.onItemClick(item));
            }
        }
    }

    interface OnItemClickListener {
        void onItemClick(NavigationItem item);
    }

    static class NavigationItem {
        private final int id;
        private final String title;
        private final String description;

        NavigationItem(int id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        int getId() {
            return id;
        }

        String getTitle() {
            return title;
        }

        String getDescription() {
            return description;
        }
    }
} 