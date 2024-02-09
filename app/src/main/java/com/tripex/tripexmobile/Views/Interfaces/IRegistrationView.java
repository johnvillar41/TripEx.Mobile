package com.tripex.tripexmobile.Views.Interfaces;

import com.tripex.tripexmobile.Models.User;

public interface IRegistrationView extends IBaseView {
    boolean validateInputFields();

    void displayImageFromLocal();

    User getUser();

    void displaySuccess();

    void closeActivity();

    void disableFields();

    void enableFields();
}
