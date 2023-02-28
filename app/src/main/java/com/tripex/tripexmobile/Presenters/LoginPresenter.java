package com.tripex.tripexmobile.Presenters;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tripex.tripexmobile.Helpers.Validator;
import com.tripex.tripexmobile.Responses.APIResponse;
import com.tripex.tripexmobile.Responses.ErrorLoginAPIResponse;
import com.tripex.tripexmobile.Responses.SuccessLoginAPIResponse;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.ILoginService;
import com.tripex.tripexmobile.Views.ILoginView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public class LoginPresenter {

    private final ILoginView view;
    private final ILoginService service;

    public LoginPresenter(ILoginView view, ILoginService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoginButtonClicked() {
        view.displayProgressLoader();

        boolean hasEmptyFields = view.validateInputFields();
        if (hasEmptyFields) {
            view.hideProgressLoader();
            return;
        }

        service.getAccount(view.getUsername(), view.getPassword(), view.getCacheDirectory(),
                new BaseService.VolleyCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        Type apiResponseType = new TypeToken<APIResponse<String>>() {
                        }.getType();
                        SuccessLoginAPIResponse<String> response = new Gson().fromJson(String.valueOf(result), apiResponseType);

                        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                            view.storeUserData(response.getResult(), response.getUserId());
                            view.redirectToDashboard();
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        if (Validator.isJsonValid(errorMessage)) {
                            view.displayError(errorMessage);
                        } else {
                            Type apiResponseType = new TypeToken<ErrorLoginAPIResponse<String>>() {
                            }.getType();
                            ErrorLoginAPIResponse<String> response = new Gson().fromJson(errorMessage, apiResponseType);
                            view.displayError(response.getResult());
                        }

                        view.hideProgressLoader();
                    }

                    @Override
                    public void onFinishedTask() {
                        view.hideProgressLoader();
                    }

                    @Override
                    public void onExceptionOccur(Exception exception) {
                        exception.printStackTrace();
                        Log.e("Login Error", exception.toString());
                        view.displayError(exception.toString());
                    }
                });
    }

    public void onClearButtonClicked() {
        view.clearFields();
    }
}
