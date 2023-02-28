package com.tripex.tripexmobile.Views;

public interface ILoginView extends IBaseView {
    String getUsername();

    String getPassword();

    void clearFields();

    boolean validateInputFields();

    void redirectToDashboard();

    void storeUserData(String result, String userId);
}
