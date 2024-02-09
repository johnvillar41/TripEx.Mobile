package com.tripex.tripexmobile.Views.Interfaces;

public interface ILoginView extends IBaseView {
    String getUsername();

    String getPassword();

    void clearFields();

    boolean validateInputFields();

    void redirectToDashboard();

    void storeUserData(String result, String userId, String fullName, String imagePath, Boolean isFirstTimeUser);

    void redirectToRegistration();

    void displayDialogActivator();

    String getCode();
}
