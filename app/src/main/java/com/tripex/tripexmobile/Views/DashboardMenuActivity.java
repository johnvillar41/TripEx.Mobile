package com.tripex.tripexmobile.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.databinding.ActivityDashboardMenuBinding;

public class DashboardMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDashboardMenuBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_menu);
        binding.cardQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardMenuActivity.this, QrScannerActivity.class);
                startActivity(intent);
            }
        });
        binding.cardVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardMenuActivity.this, VehicleActivity.class);
                startActivity(intent);
            }
        });
        binding.cardComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardMenuActivity.this, ComplaintActivity.class  );
                startActivity(intent);
            }
        });
    }
}