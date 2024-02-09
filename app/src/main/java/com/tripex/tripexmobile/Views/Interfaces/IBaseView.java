package com.tripex.tripexmobile.Views.Interfaces;

import android.text.TextUtils;
import android.widget.EditText;

import java.io.File;

public interface IBaseView {
    void hideProgressLoader();

    void displayProgressLoader();

    File getCacheDirectory();

    void displayCustomMessage(String toString);

    String getUserId();

    String getToken();

    default boolean validateFields(EditText... editTexts) {
        boolean hasError = false;
        for (EditText editText : editTexts) {
            if (TextUtils.isEmpty(editText.getText())) {
                editText.setError("Field is required!");
                hasError = true;
            }
        }

        return hasError;
    }
}