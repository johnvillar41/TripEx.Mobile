package com.tripex.tripexmobile.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tripex.tripexmobile.R;

import java.io.File;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void hideProgressLoader() {

    }

    @Override
    public void displayProgressLoader() {

    }

    @Override
    public File getCacheDirectory() {
        return null;
    }

    @Override
    public void displayError(String toString) {

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
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}