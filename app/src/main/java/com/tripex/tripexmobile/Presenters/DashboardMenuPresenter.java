package com.tripex.tripexmobile.Presenters;

import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IUserService;
import com.tripex.tripexmobile.Views.Interfaces.IDashboardMenuView;

import org.json.JSONObject;

public class DashboardMenuPresenter {
    private IUserService service;
    private IDashboardMenuView view;

    public DashboardMenuPresenter(IDashboardMenuView view, IUserService service) {
        this.service = service;
        this.view = view;
    }

    public void onPositiveButtonClicked() {
        service.updateFirstTimeUserStatus(
                view.getUserId(),
                view.getCacheDirectory(),
                view.getToken(), new BaseService.VolleyCallback<JSONObject>() {
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
