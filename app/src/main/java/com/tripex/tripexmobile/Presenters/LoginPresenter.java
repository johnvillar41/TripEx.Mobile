package com.tripex.tripexmobile.Presenters;

import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.ILoginService;
import com.tripex.tripexmobile.Views.ILoginView;

import org.json.JSONObject;

public class LoginPresenter {

    private final ILoginView view;
    private final ILoginService service;

    public LoginPresenter(ILoginView view, ILoginService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoginButtonClicked() {
        service.getAccount(view.getUsername(), view.getUsername(), view.getCacheDirectory(), new BaseService.VolleyCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onError(String errorMessage) {

            }

            @Override
            public void onFinishedTask() {

            }

            @Override
            public void onExceptionOccur(Exception exception) {

            }
        });
    }

}
