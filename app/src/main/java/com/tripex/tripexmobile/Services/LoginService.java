package com.tripex.tripexmobile.Services;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class LoginService extends BaseService implements ILoginService {
    private static LoginService singleInstance = null;

    public static synchronized LoginService getInstance() {
        if (singleInstance == null)
            singleInstance = new LoginService();

        return singleInstance;
    }

    private LoginService() {

    }

    @Override
    public void getAccount(String username, String password, File cacheDir, VolleyCallback<JSONObject> callback) {
        String url = BASE_URL_API + "Login";
        JSONObject object = new JSONObject();
        try {
            object.put("username", username);
            object.put("password", password);
        } catch (JSONException e) {
            callback.onExceptionOccur(e);
        }
        transactJsonObjectData(cacheDir, null, url, callback, object, Request.Method.POST);
    }

}
