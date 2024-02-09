package com.tripex.tripexmobile.Views.Implementations;

import static com.tripex.tripexmobile.Services.BaseService.BASE_URL;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.tripex.tripexmobile.Helpers.KeyStorage;
import com.tripex.tripexmobile.Models.Image;
import com.tripex.tripexmobile.Models.Vehicle;
import com.tripex.tripexmobile.Presenters.VehicleQrScannerPresenter;
import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.Services.Implementations.VehicleService;
import com.tripex.tripexmobile.Views.Adapters.FareMatrixAdapter;
import com.tripex.tripexmobile.Views.Interfaces.IVehicleView;
import com.tripex.tripexmobile.databinding.ActivityVehicleQrScannerBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VehicleQrScannerActivity extends AppCompatActivity implements IVehicleView {

    private ActivityVehicleQrScannerBinding binding;

    private static final int PERMISSION_REQUEST_CAMERA = 0;

    private VehicleQrScannerPresenter presenter;

    private ActivityResultLauncher<ScanOptions> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_qr_scanner);
        presenter = new VehicleQrScannerPresenter(VehicleService.getInstance(), this);

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

        binding.btnScanAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onScanButtonClicked();
            }
        });

        presenter.onLoad();
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
    public void displayVehicle(Vehicle vehicle) {
        List<SlideModel> slideModels = new ArrayList<>();
        for (Image imageString : vehicle.getImages()) {
            slideModels.add(new SlideModel(BASE_URL + imageString.getImageLink(), ScaleTypes.FIT));
        }
        binding.imageSlider.setImageList(slideModels);

        binding.editTextId.setText(vehicle.getId());
        binding.editTextName.setText(vehicle.getName());
        binding.editTextPlateNumber.setText(vehicle.getPlateNumber());
        binding.editTextVehicleType.setText(vehicle.getVehicleType());

        FareMatrixAdapter adapter = new FareMatrixAdapter(vehicle.getFareMatrices());
        binding.fareMatrixes.setAdapter(adapter);
        binding.fareMatrixes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
}