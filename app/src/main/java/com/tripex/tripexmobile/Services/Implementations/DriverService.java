package com.tripex.tripexmobile.Services.Implementations;

import com.android.volley.Request;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IDriverService;

import org.json.JSONObject;

import java.io.File;

public class DriverService extends BaseService implements IDriverService {
    private static DriverService singleInstance = null;

    public static synchronized DriverService getInstance() {
        if (singleInstance == null)
            singleInstance = new DriverService();

        return singleInstance;
    }

    @Override
    public void getDriver(String contents, String token, File cacheDirectory, BaseService.VolleyCallback<JSONObject> callback) {
        String url = BASE_URL + "api/Driver/GetDriver/" + contents;
        transactJsonObjectData(cacheDirectory, token, url, callback, null, Request.Method.GET);
    }
}
