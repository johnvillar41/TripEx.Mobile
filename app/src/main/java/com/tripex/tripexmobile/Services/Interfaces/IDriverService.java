package com.tripex.tripexmobile.Services.Interfaces;

import com.tripex.tripexmobile.Services.BaseService;

import org.json.JSONObject;

import java.io.File;

public interface IDriverService {
    void getDriver(String contents, String token, File cacheDirectory, BaseService.VolleyCallback<JSONObject> callback);
}
