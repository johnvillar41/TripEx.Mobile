package com.tripex.tripexmobile.Presenters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tripex.tripexmobile.Helpers.Validator;
import com.tripex.tripexmobile.Models.Complaints.Complaint;
import com.tripex.tripexmobile.Responses.APIResponse;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IComplaintService;
import com.tripex.tripexmobile.Views.Interfaces.IComplaintView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class ComplaintPresenter {
    private final IComplaintView view;
    private final IComplaintService service;

    public ComplaintPresenter(IComplaintView view, IComplaintService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoad() {
        view.displayProgressLoader();
        service.getAllComplaints(
                view.getPageNumber(),
                view.getPageSize(),
                view.getUserId(),
                view.getToken(),
                view.getCacheDirectory(),
                new BaseService.VolleyCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        Type apiResponseType = new TypeToken<APIResponse<List<Complaint>>>() {
                        }.getType();
                        APIResponse<List<Complaint>> response = new Gson().fromJson(String.valueOf(result), apiResponseType);
                        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                            view.setTotalPageSize(response.getPageSize());
                            view.displayComplaints(response.getResult());
                        } else if (response.getStatusCode() == HttpURLConnection.HTTP_NO_CONTENT) {
                            view.displayComplaints(response.getResult());
                            view.displayCustomMessage("No results found!");
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

    public void onFilterButtonClicked() {
        view.displayFilterSettings();
    }

    public void onNextButtonClicked() {
        onLoad();
    }

    public void onBackButtonClicked() {
        onLoad();
    }
}
