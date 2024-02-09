package com.tripex.tripexmobile.Services.Implementations;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.tripex.tripexmobile.Models.User;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IRegistrationService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class RegistrationService extends BaseService implements IRegistrationService {
    private static RegistrationService singleInstance = null;

    public static synchronized RegistrationService getInstance() {
        if (singleInstance == null)
            singleInstance = new RegistrationService();

        return singleInstance;
    }

    @Override
    public void createUser(User user, String token, File cacheDirectory, VolleyCallback<JSONObject> jsonObjectVolleyCallback) {
        String url = BASE_URL + "api/User/CreateOrUpdate";
        JsonObject jsonObject = new Gson().toJsonTree(user).getAsJsonObject();
        if (user.getImage() == null || user.getImage().isEmpty()) {
            jsonObject.addProperty("image", (String) null);
        }
        if (user.getId() == null || user.getId().isEmpty()) {
            jsonObject.addProperty("id", (String) null);
        }
        transactJsonObjectData(cacheDirectory, token, url, jsonObjectVolleyCallback, jsonObject, Request.Method.POST);
    }
}
