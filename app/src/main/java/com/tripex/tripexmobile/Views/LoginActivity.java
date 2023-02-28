package com.tripex.tripexmobile.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tripex.tripexmobile.Presenters.LoginPresenter;
import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.Services.LoginService;
import com.tripex.tripexmobile.databinding.ActivityMainBinding;

import java.io.File;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private ActivityMainBinding binding;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new LoginPresenter(this, LoginService.getInstance());
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
    }

    @Override
    public void hideProgressLoader() {
        binding.progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayProgressLoader() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public File getCacheDirectory() {
        return getCacheDir();
    }

    @Override
    public void displayError(String toString) {
        Toast.makeText(this, toString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
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

    }

    @Override
    public void storeUserData(String result, String userId) {

    }
}