package com.tripex.tripexmobile.Services.Implementations;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tripex.tripexmobile.Models.Complaints.Complaint;
import com.tripex.tripexmobile.Models.Complaints.CreateOrUpdateComplaint;
import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IComplaintService;

import org.json.JSONObject;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComplaintService extends BaseService implements IComplaintService {

    private static ComplaintService singleInstance = null;

    public static synchronized ComplaintService getInstance() {
        if (singleInstance == null)
            singleInstance = new ComplaintService();

        return singleInstance;
    }

    @Override
    public void getAllComplaints(int pageNumber, int pageSize, String userId, String token, File cacheDir, VolleyCallback<JSONObject> callback) {
        LocalDate dateNow = null;
        LocalDate minDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateNow = LocalDate.parse(LocalDate.now().toString(), formatter);
            minDate = LocalDate.parse("1753-01-01", formatter);
        }
        String url = BASE_URL_API + "Complaint/Index?pageNumber=" + pageNumber + "&dateFrom=" + minDate + "&dateTo=" + dateNow + "&pageSize=" + pageSize;
        transactJsonObjectData(cacheDir, token, url, callback, null, Request.Method.GET);
    }

    @Override
    public void addComplaint(CreateOrUpdateComplaint complaint, String token, File cacheDirectory, VolleyCallback<JSONObject> callback) {
        String url = BASE_URL_API + "Complaint/CreateOrUpdate";
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject jsonObject = gson.toJsonTree(complaint).getAsJsonObject();
        transactJsonObjectData(cacheDirectory, token, url, callback, jsonObject, Request.Method.POST);
    }
}
