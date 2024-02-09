package com.tripex.tripexmobile.Presenters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.tripex.tripexmobile.Helpers.Validator;
import com.tripex.tripexmobile.Models.Vehicle;
import com.tripex.tripexmobile.Responses.APIResponse;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IVehicleService;
import com.tripex.tripexmobile.Views.Interfaces.IVehicleView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public class VehicleQrScannerPresenter {


    private final IVehicleService service;
    private final IVehicleView view;

    public VehicleQrScannerPresenter(IVehicleService service, IVehicleView view) {
        this.service = service;
        this.view = view;
    }

    public void onLoad() {
        view.initializeScanner();
    }

    public void onScanResult(ScanIntentResult result) {
        view.displayProgressLoader();
        service.getVehicle(result.getContents(), view.getToken(), view.getCacheDirectory(), new BaseService.VolleyCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Type apiResponseType = new TypeToken<APIResponse<Vehicle>>() {
                }.getType();
                APIResponse<Vehicle> response = new Gson().fromJson(String.valueOf(result), apiResponseType);
                if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    view.displayVehicle(response.getResult());
                    view.displayCustomMessage(response.getResult().toString());
                } else {
                    view.displayCustomMessage("QrCode not found");
                }
            }

            @Override
            public void onError(String errorMessage) {
                if (!Validator.isJsonValid(errorMessage)) {
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

    public void onScanButtonClicked() {
        view.initializeScanner();
    }
}
