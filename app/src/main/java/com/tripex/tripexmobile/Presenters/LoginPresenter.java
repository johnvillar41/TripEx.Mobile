package com.tripex.tripexmobile.Presenters;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tripex.tripexmobile.Helpers.Validator;
import com.tripex.tripexmobile.Responses.APIResponse;
import com.tripex.tripexmobile.Responses.ErrorLoginAPIResponse;
import com.tripex.tripexmobile.Responses.SuccessLoginAPIResponse;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IUserService;
import com.tripex.tripexmobile.Views.Interfaces.ILoginView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public class LoginPresenter {

    private final ILoginView view;
    private final IUserService service;

    public LoginPresenter(ILoginView view, IUserService service) {
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
                        Type apiResponseType = new TypeToken<SuccessLoginAPIResponse<String>>() {
                        }.getType();
                        SuccessLoginAPIResponse<String> response = new Gson().fromJson(String.valueOf(result), apiResponseType);

                        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                            view.storeUserData(
                                    response.getResult(),
                                    response.getUserId(),
                                    response.getFullName(),
                                    response.getImageLink(),
                                    response.getFirstTimeUser());
                            view.redirectToDashboard();
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        if (!Validator.isJsonValid(errorMessage)) {
                            view.displayCustomMessage(errorMessage);
                        } else {
                            Type apiResponseType = new TypeToken<ErrorLoginAPIResponse<String>>() {
                            }.getType();
                            ErrorLoginAPIResponse<String> response = new Gson().fromJson(errorMessage, apiResponseType);
                            if (response.getResult().equals("User not activated")) {
                                view.displayDialogActivator();
                                return;
                            }

                            view.displayCustomMessage(response.getResult());
                        }
                    }

                    @Override
                    public void onFinishedTask() {
                        view.hideProgressLoader();
                    }

                    @Override
                    public void onExceptionOccur(Exception exception) {
                        exception.printStackTrace();
                        Log.e("Login Error", exception.toString());
                        view.displayCustomMessage(exception.toString());
                    }
                });
    }

    public void onClearButtonClicked() {
        view.clearFields();
    }

    public void onRegisterButtonCLicked() {
        view.redirectToRegistration();
    }

    public void onConfirmCodeClick() {
        view.displayProgressLoader();
        service.activateAccount(view.getUsername(), view.getCode(), view.getCacheDirectory(),
                new BaseService.VolleyCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        Type apiResponseType = new TypeToken<SuccessLoginAPIResponse<String>>() {
                        }.getType();
                        APIResponse<String> response = new Gson().fromJson(String.valueOf(result), apiResponseType);

                        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                            view.displayCustomMessage("Successfully activated account");
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        if (!Validator.isJsonValid(errorMessage)) {
                            view.displayCustomMessage(errorMessage);
                        } else {
                            Type apiResponseType = new TypeToken<APIResponse<String>>() {
                            }.getType();
                            APIResponse<String> response = new Gson().fromJson(String.valueOf(errorMessage), apiResponseType);
                            view.displayCustomMessage(response.getResult());
                        }
                    }

                    @Override
                    public void onFinishedTask() {
                        view.hideProgressLoader();
                    }

                    @Override
                    public void onExceptionOccur(Exception exception) {
                        exception.printStackTrace();
                        view.displayCustomMessage(exception.toString());
                    }
                });
    }
}
