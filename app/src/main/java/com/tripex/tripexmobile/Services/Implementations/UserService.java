package com.tripex.tripexmobile.Services.Implementations;

import com.android.volley.Request;
import com.google.gson.JsonObject;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IUserService;

import org.json.JSONObject;

import java.io.File;

public class UserService extends BaseService implements IUserService {
    private static UserService singleInstance = null;

    public static synchronized UserService getInstance() {
        if (singleInstance == null)
            singleInstance = new UserService();

        return singleInstance;
    }

    private UserService() {

    }

    @Override
    public void getAccount(String username, String password, File cacheDir, VolleyCallback<JSONObject> callback) {
        String url = BASE_URL_API + "User/Login/Login";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        transactJsonObjectData(cacheDir, null, url, callback, jsonObject, Request.Method.POST);
    }

    @Override
    public void activateAccount(String username, String code, File cacheDirectory, VolleyCallback<JSONObject> jsonObjectVolleyCallback) {
        String url = BASE_URL_API + "User/Activate/" + code + "/" + username;
        transactJsonObjectData(cacheDirectory, null, url, jsonObjectVolleyCallback, null, Request.Method.PUT);
    }

    @Override
    public void updateFirstTimeUserStatus(String userId, File cacheDirectory, String token, VolleyCallback<JSONObject> jsonObjectVolleyCallback) {
        String url = BASE_URL_API + "User/ModifyIsUserFirstTimer?userId=" + userId ;
        transactJsonObjectData(cacheDirectory, token, url, jsonObjectVolleyCallback, null, Request.Method.PUT);
    }

}
