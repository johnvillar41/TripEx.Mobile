package com.tripex.tripexmobile.Views.Implementations;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tripex.tripexmobile.Helpers.KeyStorage;
import com.tripex.tripexmobile.Models.User;
import com.tripex.tripexmobile.Presenters.RegistrationPresenter;
import com.tripex.tripexmobile.R;
import com.tripex.tripexmobile.Services.Implementations.RegistrationService;
import com.tripex.tripexmobile.Views.Interfaces.IRegistrationView;
import com.tripex.tripexmobile.databinding.ActivityRegistrationBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Base64;

public class RegistrationActivity extends AppCompatActivity implements IRegistrationView {

    private ActivityRegistrationBinding binding;
    private RegistrationPresenter presenter;
    private ActivityResultLauncher<String> launcher;
    private String imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        presenter = new RegistrationPresenter(this, RegistrationService.getInstance());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Registration");
        }

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSubmitButtonClicked();
            }
        });
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onProfileImageClicked();
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(result));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                // Store the binary data in a ByteArrayOutputStream
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write(byteArray, 0, byteArray.length);

                // Convert the ByteArrayOutputStream to a Base64 encoded string
                String encodedImage = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);

                setImageString(encodedImage);
                binding.profileImage.setImageURI(result);
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
    public boolean validateInputFields() {
        if (!binding.editTextComfirmPassword.getText().toString().equals(binding.editTextPassword.getText().toString())) {
            binding.editTextPassword.setError("Password should be equal");
            binding.editTextComfirmPassword.setError("Password should be equal");
            return true;
        }

        boolean isEmailNotValid = true;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = binding.editTextEmail.getText().toString();

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isEmailNotValid = false;
        }

        if (isEmailNotValid) {
            binding.editTextEmail.setError("Should be in email format!");
            return true;
        }


        return validateFields(
                binding.editTextComfirmPassword,
                binding.editTextPassword,
                binding.editTextFullName,
                binding.editTextUsername);
    }

    @Override
    public void displayImageFromLocal() {
        launcher.launch("image/*");
    }

    @Override
    public User getUser() {
        return new User(
                null, binding.editTextUsername.getText().toString(),
                imageString,
                binding.editTextFullName.getText().toString(),
                binding.editTextPassword.getText().toString(),
                binding.editTextEmail.getText().toString(),
                KeyStorage.getRoleId());
    }

    @Override
    public void displaySuccess() {
        Toast.makeText(this, "Successfully created user", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeActivity() {
        this.finish();
    }

    @Override
    public void disableFields() {
        binding.editTextUsername.setEnabled(false);
        binding.editTextPassword.setEnabled(false);
        binding.editTextComfirmPassword.setEnabled(false);
        binding.editTextFullName.setEnabled(false);
        binding.btnSubmit.setEnabled(false);
        binding.editTextEmail.setEnabled(false);
    }

    @Override
    public void enableFields() {
        binding.editTextUsername.setEnabled(true);
        binding.editTextPassword.setEnabled(true);
        binding.editTextComfirmPassword.setEnabled(true);
        binding.editTextFullName.setEnabled(true);
        binding.btnSubmit.setEnabled(true);
        binding.editTextEmail.setEnabled(true);
    }

    private void setImageString(String imageString) {
        this.imageString = imageString;
    }
}