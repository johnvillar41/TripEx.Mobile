package com.tripex.tripexmobile.Services.Interfaces;

import com.tripex.tripexmobile.Services.BaseService;

import org.json.JSONObject;

import java.io.File;

public interface IUserService {
    void getAccount(String username, String password, File cacheDir, BaseService.VolleyCallback<JSONObject> callback);

    void activateAccount(String username, String code, File cacheDirectory, BaseService.VolleyCallback<JSONObject> jsonObjectVolleyCallback);

    void updateFirstTimeUserStatus(String userId, File cacheDirectory, String token, BaseService.VolleyCallback<JSONObject> jsonObjectVolleyCallback);
}
