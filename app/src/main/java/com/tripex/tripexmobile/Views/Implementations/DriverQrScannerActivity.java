package com.tripex.tripexmobile.Views.Implementations;

import static com.tripex.tripexmobile.Services.BaseService.BASE_URL;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.tripex.tripexmobile.Helpers.KeyStorage;
import com.tripex.tripexmobile.Models.Complaints.Complaint;
import com.tripex.tripexmobile.Models.Complaints.CreateOrUpdateComplaint;
import com.tripex.tripexmobile.Models.Driver;
import com.tripex.tripexmobile.Models.Image;
import com.tripex.tripexmobile.Presenters.DriverQrScannerPresenter;
import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.Services.Implementations.ComplaintService;
import com.tripex.tripexmobile.Services.Implementations.DriverService;
import com.tripex.tripexmobile.Views.Interfaces.IQrScannerView;
import com.tripex.tripexmobile.databinding.ActivityQrScannerDriverBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DriverQrScannerActivity extends AppCompatActivity implements IQrScannerView {

    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private DriverQrScannerPresenter presenter;
    private ActivityQrScannerDriverBinding binding;
    private ActivityResultLauncher<ScanOptions> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_scanner_driver);
        presenter = new DriverQrScannerPresenter(this, DriverService.getInstance(), ComplaintService.getInstance());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Qr Scanner");
        }

        launcher = registerForActivityResult(new ScanContract(), new ActivityResultCallback<ScanIntentResult>() {
            @Override
            public void onActivityResult(ScanIntentResult result) {
                presenter.onScanResult(result);
            }
        });

        presenter.onLoad();

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSubmitComplaint();
            }
        });

        binding.btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onScanButtonClick();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.onLoad();
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean validateFields() {
        return validateFields(
                binding.editTextDriverId,
                binding.editTextDriverName,
                binding.editTextGender,
                binding.editTextEmail,
                binding.editTextOrganization);
    }

    @Override
    public void initializeScanner() {
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setPrompt("Volume up to open flash");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CaptureActivity.class);

        launcher.launch(scanOptions);
    }

    @Override
    public void displayDriver(Driver result) {
        binding.editTextDriverId.setText(result.getId());
        binding.editTextDriverName.setText(result.getName());
        binding.editTextGender.setText(result.getGender());
        binding.editTextEmail.setText(result.getEmail());
        binding.editTextOrganization.setText(result.getOrganization());

        List<SlideModel> slideModels = new ArrayList<>();
        for (Image imageString : result.getImages()) {
            slideModels.add(new SlideModel(BASE_URL + imageString.getImageLink(), ScaleTypes.FIT));
        }
        binding.imageSlider.setImageList(slideModels);
    }

    @Override
    public CreateOrUpdateComplaint getComplaint() {
        return new CreateOrUpdateComplaint(null,
                binding.editTextDriverId.getText().toString(),
                KeyStorage.getUserId(getApplicationContext()),
                binding.editTextDriverComplaint.getText().toString());
    }

    @Override
    public void displaySuccessMessage() {
        Toast.makeText(this, "Successfully submitted complaint!", Toast.LENGTH_SHORT).show();
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
}