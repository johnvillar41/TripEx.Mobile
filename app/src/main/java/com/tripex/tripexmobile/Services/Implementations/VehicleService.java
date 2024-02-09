package com.tripex.tripexmobile.Services.Implementations;

import com.android.volley.Request;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IVehicleService;

import org.json.JSONObject;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VehicleService extends BaseService implements IVehicleService {
    private static VehicleService singleInstance = null;

    public static synchronized VehicleService getInstance() {
        if (singleInstance == null)
            singleInstance = new VehicleService();

        return singleInstance;
    }

    private VehicleService() {

    }

    @Override
    public void getAllVehicles(int pageNumber, String searchString, String token, File cacheDir, VolleyCallback<JSONObject> callback) {
        LocalDate dateNow = null;
        LocalDate minDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateNow = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            minDate = LocalDate.parse("1753-01-01", formatter);
        }
        String url = BASE_URL_API + "Vehicle/Index?pageNumber=" + pageNumber + "&dateFrom=" + minDate + "&dateTo=" + dateNow + "&searchString=" + (searchString == null ? "" : searchString) + "&vehicleType=";
        transactJsonObjectData(cacheDir, token, url, callback, null, Request.Method.GET);
    }

    @Override
    public void getVehicle(String vehicleId, String token, File cacheDir, VolleyCallback<JSONObject> callback) {
        String url = BASE_URL_API + "Vehicle/GetVehicle/" + vehicleId;
        transactJsonObjectData(cacheDir, token, url, callback, null, Request.Method.GET);
    }
}
