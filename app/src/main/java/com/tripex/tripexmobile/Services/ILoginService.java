package com.tripex.tripexmobile.Services;

import org.json.JSONObject;

import java.io.File;

public interface ILoginService {
    void getAccount(String username, String password, File cacheDir, BaseService.VolleyCallback<JSONObject> callback);
}
