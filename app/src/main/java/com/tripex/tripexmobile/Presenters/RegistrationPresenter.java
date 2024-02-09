package com.tripex.tripexmobile.Presenters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tripex.tripexmobile.Helpers.Validator;
import com.tripex.tripexmobile.Responses.APIResponse;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IRegistrationService;
import com.tripex.tripexmobile.Views.Interfaces.IRegistrationView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public class RegistrationPresenter {

    private final IRegistrationView view;
    private final IRegistrationService service;

    public RegistrationPresenter(IRegistrationView view, IRegistrationService service) {
        this.service = service;
        this.view = view;
    }

    public void onSubmitButtonClicked() {
        view.disableFields();

        view.displayProgressLoader();

        boolean hasEmptyFields = view.validateInputFields();
        if (hasEmptyFields) {
            view.hideProgressLoader();
            return;
        }

        service.createUser(
                view.getUser(),
                view.getToken(),
                view.getCacheDirectory(),
                new BaseService.VolleyCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        Type apiResponseType = new TypeToken<APIResponse<Boolean>>() {
                        }.getType();
                        APIResponse<Boolean> response = new Gson().fromJson(String.valueOf(result), apiResponseType);
                        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                            view.displaySuccess();
                            view.closeActivity();
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
                        view.enableFields();
                    }

                    @Override
                    public void onExceptionOccur(Exception exception) {
                        exception.printStackTrace();
                        view.displayCustomMessage(exception.toString());
                    }
                });
    }

    public void onProfileImageClicked() {
        view.displayImageFromLocal();
    }
}
