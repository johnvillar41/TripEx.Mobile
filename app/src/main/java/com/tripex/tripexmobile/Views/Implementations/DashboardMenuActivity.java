package com.tripex.tripexmobile.Views.Implementations;

import static com.tripex.tripexmobile.Services.BaseService.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tripex.tripexmobile.Helpers.KeyStorage;
import com.tripex.tripexmobile.Presenters.DashboardMenuPresenter;
import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.Services.Implementations.UserService;
import com.tripex.tripexmobile.Views.Interfaces.IDashboardMenuView;
import com.tripex.tripexmobile.databinding.ActivityDashboardMenuBinding;

import java.io.File;


public class DashboardMenuActivity extends AppCompatActivity implements IDashboardMenuView {

    private DashboardMenuPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDashboardMenuBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_menu);

        presenter = new DashboardMenuPresenter(this, UserService.getInstance());

        final String welcomeMessage = "Welcome: " + KeyStorage.getUserFullName(getApplicationContext());

        displayTermsTermsAndConditionsOneTime();

        binding.textViewName.setText(welcomeMessage);

        Picasso.get()
                .load(BASE_URL + KeyStorage.getUserImagePath(getApplicationContext()))
                .resize(350, 350)
                .centerCrop()
                .into(binding.imageView);

        binding.cardQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardMenuActivity.this, DriverQrScannerActivity.class);
                startActivity(intent);
            }
        });
        binding.cardComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardMenuActivity.this, ComplaintActivity.class);
                startActivity(intent);
            }
        });
        binding.cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyStorage.clearUserData(getApplicationContext());
                finish();
            }
        });
        binding.cardVehicleQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardMenuActivity.this, VehicleQrScannerActivity.class);
                startActivity(intent);
            }
        });
        binding.cardPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeDialogForTerms();
            }
        });
    }

    private void displayTermsTermsAndConditionsOneTime() {
        if (!KeyStorage.getIsFirstTimeUser(getApplicationContext())) {
            return;
        }

        initializeDialogForTerms();
    }

    @Override
    public void hideProgressLoader() {

    }

    @Override
    public void displayProgressLoader() {

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

    private void initializeDialogForTerms() {
        // Initialize a dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message of the dialog
        builder.setTitle("Data Privacy Terms and Conditions");
        builder.setMessage("Please note that drafting data privacy terms and conditions requires a thorough understanding of the specific application and its operations, as well as applicable data protection laws. The following is a general template that can be used as a starting point and should be customized to fit the specific needs of your application:\n" +
                "\n" +
                "Data Collection\n" +
                "We collect personal data such as name, email address, and phone number, as well as usage data including IP addresses and browsing history. This data is collected for the purpose of providing and improving our services, and for communicating with our users.\n" +
                "\n" +
                "Data Processing\n" +
                "We process personal data for the purposes of providing and improving our services, ensuring the security of our application, and complying with legal obligations.\n" +
                "\n" +
                "Data Sharing\n" +
                "We may share personal data with third-party service providers who assist us in providing and improving our services. We may also share data when required by law or in response to legal requests.\n" +
                "\n" +
                "Consent\n" +
                "By using our application, you consent to the collection, processing, and sharing of your personal data as outlined in these terms and conditions.\n" +
                "\n" +
                "Data Subject Rights\n" +
                "As a data subject, you have the right to access, correct, and delete your personal data. You may also withdraw your consent at any time. To exercise these rights, please contact us at [insert contact information].\n" +
                "\n" +
                "Data Security\n" +
                "We take appropriate technical and organizational measures to ensure the security of personal data, including the use of encryption and access controls.\n" +
                "\n" +
                "Data Breach Notification\n" +
                "In the event of a data breach that may impact your personal data, we will notify you and the relevant authorities as required by law.\n" +
                "\n" +
                "Changes to the Terms and Conditions\n" +
                "We may update these terms and conditions from time to time. Any changes will be communicated to you via email or through the application.\n" +
                "\n" +
                "By using our application, you agree to these data privacy terms and conditions. If you do not agree with any part of these terms, please do not use our application.");

        // Set the positive button to close the dialog
        builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onPositiveButtonClicked();
            }
        });

        // Set the negative button to cancel the dialog and exit the application
        builder.setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform the desired action when the user disagrees
                finish();
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}