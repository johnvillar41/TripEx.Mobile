package com.tripex.tripexmobile.Views;

public interface ILoginView extends IBaseView {
    String getUsername();
    String getPassword();

    void clearFields();
}
