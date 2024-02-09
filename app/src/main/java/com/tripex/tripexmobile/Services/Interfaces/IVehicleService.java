package com.tripex.tripexmobile.Services.Interfaces;

import com.tripex.tripexmobile.Services.BaseService;

import org.json.JSONObject;

import java.io.File;

public interface IVehicleService {
    void getAllVehicles(int pageNumber, String searchString, String token, File cacheDir, BaseService.VolleyCallback<JSONObject> callback);
    void getVehicle(String vehicleId, String token, File cacheDir, BaseService.VolleyCallback<JSONObject> callback);
}
