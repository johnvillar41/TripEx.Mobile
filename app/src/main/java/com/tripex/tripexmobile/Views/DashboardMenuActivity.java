package com.tripex.tripexmobile.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.databinding.ActivityDashboardMenuBinding;

public class DashboardMenuActivity extends AppCompatActivity {

    private ActivityDashboardMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_menu);
    }
}