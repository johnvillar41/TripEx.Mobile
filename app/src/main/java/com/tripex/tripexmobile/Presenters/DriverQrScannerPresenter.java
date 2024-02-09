package com.tripex.tripexmobile.Presenters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.tripex.tripexmobile.Helpers.Validator;
import com.tripex.tripexmobile.Models.Driver;
import com.tripex.tripexmobile.Responses.APIResponse;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IComplaintService;
import com.tripex.tripexmobile.Services.Interfaces.IDriverService;
import com.tripex.tripexmobile.Views.Interfaces.IQrScannerView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public class DriverQrScannerPresenter {

    private final IQrScannerView view;
    private final IDriverService driverService;
    private final IComplaintService complaintService;

    public DriverQrScannerPresenter(
            IQrScannerView view,
            IDriverService driverService,
            IComplaintService complaintService) {
        this.view = view;
        this.driverService = driverService;
        this.complaintService = complaintService;
    }

    public void onLoad() {
        view.initializeScanner();
    }

    public void onScanResult(ScanIntentResult result) {
        view.displayProgressLoader();
        driverService.getDriver(result.getContents(), view.getToken(), view.getCacheDirectory(), new BaseService.VolleyCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Type apiResponseType = new TypeToken<APIResponse<Driver>>() {
                }.getType();
                APIResponse<Driver> response = new Gson().fromJson(String.valueOf(result), apiResponseType);
                if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    view.displayDriver(response.getResult());
                } else {
                    view.displayCustomMessage("QrCode not found");
                }
            }

            @Override
            public void onError(String errorMessage) {
                if (Validator.isJsonValid(errorMessage)) {
                    view.displayCustomMessage(errorMessage);
                } else {
                    Type apiResponseType = new TypeToken<String>() {
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
            }
        });
    }

    public void onSubmitComplaint() {
        if (view.validateFields()) {
            return;
        }

        view.displayProgressLoader();
        complaintService.addComplaint(view.getComplaint(), view.getToken(), view.getCacheDirectory(), new BaseService.VolleyCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Type apiResponseType = new TypeToken<APIResponse<Boolean>>() {
                }.getType();
                APIResponse<Boolean> response = new Gson().fromJson(String.valueOf(result), apiResponseType);
                if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    view.displaySuccessMessage();
                }
            }

            @Override
            public void onError(String errorMessage) {
                if (Validator.isJsonValid(errorMessage)) {
                    view.displayCustomMessage(errorMessage);
                } else {
                    Type apiResponseType = new TypeToken<String>() {
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
            }
        });
    }

    public void onScanButtonClick() {
        view.initializeScanner();
    }
}
