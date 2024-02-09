package com.tripex.tripexmobile.Views.Implementations;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.tripex.tripexmobile.Helpers.KeyStorage;
import com.tripex.tripexmobile.Presenters.LoginPresenter;
import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.Services.Implementations.UserService;
import com.tripex.tripexmobile.Views.Interfaces.ILoginView;
import com.tripex.tripexmobile.databinding.ActivityMainBinding;

import java.io.File;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private ActivityMainBinding binding;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new LoginPresenter(this, UserService.getInstance());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLoginButtonClicked();
            }
        });
        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClearButtonClicked();
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRegisterButtonCLicked();
            }
        });
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
        Snackbar snackbar = Snackbar.make(binding.linearLayoutCompat, toString, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(Color.YELLOW);
        snackbar.setTextColor(Color.BLACK);
        snackbar.show();
    }

    @Override
    public String getUsername() {
        return binding.editTextUser.getText().toString();
    }

    @Override
    public String getPassword() {
        return binding.editTextPassword.getText().toString();
    }

    @Override
    public void clearFields() {
        binding.editTextUser.getText().clear();
        binding.editTextPassword.getText().clear();
    }

    @Override
    public boolean validateInputFields() {
        return validateFields(binding.editTextUser, binding.editTextPassword);
    }

    @Override
    public void redirectToDashboard() {
        Intent intent = new Intent(this, DashboardMenuActivity.class);
        startActivity(intent);
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
    public void storeUserData(String token, String userLoggedIn, String fullName, String imagePath, Boolean isFirstTimeUser) {
        KeyStorage.storeUserData(token, userLoggedIn, fullName, imagePath, isFirstTimeUser, getApplicationContext());
    }

    @Override
    public void redirectToRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayDialogActivator() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Activation Code here");

        // Set up the input
        final EditText input = new EditText(this);
        // Set the input type and any other options for the EditText
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Enter text here");

        // Set layout parameters for the EditText to add margins
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margin = (int) getResources().getDimension(R.dimen.alert_dialog_edit_text_margin);
        lp.setMargins(margin, margin, margin, margin);
        input.setLayoutParams(lp);

        // Specify the layout to be used for the dialog and add the EditText to it
        FrameLayout container = new FrameLayout(this);
        container.addView(input);
        builder.setView(container);
        // Set the positive and negative button options for the dialog
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle OK button click
                String code = input.getText().toString();
                setCode(code);
                presenter.onConfirmCodeClick();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle Cancel button click
                dialog.cancel();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        Toast.makeText(this, "Enter the activation code that you got from your email here", Toast.LENGTH_SHORT).show();
    }

    private String code;

    @Override
    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

}