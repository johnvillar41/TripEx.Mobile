package com.tripex.tripexmobile.Views.Implementations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tripex.tripexmobile.Helpers.KeyStorage;
import com.tripex.tripexmobile.Models.Complaints.Complaint;
import com.tripex.tripexmobile.OnItemClick;
import com.tripex.tripexmobile.Presenters.ComplaintPresenter;
import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.Services.Implementations.ComplaintService;
import com.tripex.tripexmobile.Views.Adapters.ComplaintAdapter;
import com.tripex.tripexmobile.Views.Interfaces.IComplaintView;
import com.tripex.tripexmobile.databinding.ActivityComplaintBinding;

import java.io.File;
import java.util.List;

public class ComplaintActivity extends AppCompatActivity implements IComplaintView {

    private ActivityComplaintBinding binding;
    private ComplaintPresenter presenter;

    private int pageNumber = 0;
    private int pageSize = 10;
    private int totalPageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complaint);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Complaints");
        }

        presenter = new ComplaintPresenter(this, ComplaintService.getInstance());
        presenter.onLoad();

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onFilterButtonClicked();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideProgressLoader() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayProgressLoader() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public File getCacheDirectory() {
        return getCacheDir();
    }

    @Override
    public void displayCustomMessage(String toString) {
        Toast.makeText(this, toString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserId() {
        return KeyStorage.getUserId(getApplicationContext());
    }

    @Override
    public String getToken() {
        return KeyStorage.getToken(getApplicationContext());
    }

    @Override
    public void displayComplaints(List<Complaint> complaints) {
        ComplaintAdapter adapter = new ComplaintAdapter(complaints);
        adapter.setOnClickListener(new OnItemClick<String>() {
            @Override
            public void onItemClick(String data) {

            }

            @Override
            public void onItemLongClick(String data) {

            }
        });
        binding.complaints.setAdapter(adapter);
        binding.complaints.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void displayFilterSettings() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ComplaintActivity.this);
        builder.setTitle("Filter Complaints");
        builder.setPositiveButton("Next Page", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (totalPageSize >= pageNumber) {
                    pageNumber = totalPageSize;
                } else {
                    pageNumber++;
                }
                presenter.onNextButtonClicked();
                dialog.cancel();
            }
        });

        if (pageNumber > 0) {
            builder.setNegativeButton("Back Page", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (pageNumber <= 0) {
                        pageNumber = 0;
                    } else {
                        pageNumber--;
                    }
                    presenter.onBackButtonClicked();
                    dialog.cancel();
                }
            });
        }

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void setTotalPageSize(int pageSize) {
        this.totalPageSize = pageSize;
    }

}