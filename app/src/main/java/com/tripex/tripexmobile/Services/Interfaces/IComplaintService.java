package com.tripex.tripexmobile.Services.Interfaces;

import com.tripex.tripexmobile.Models.Complaints.Complaint;
import com.tripex.tripexmobile.Models.Complaints.CreateOrUpdateComplaint;
import com.tripex.tripexmobile.Services.BaseService;

import org.json.JSONObject;

import java.io.File;

public interface IComplaintService {
    void getAllComplaints(int pageNumber, int pageSize, String userId, String token, File cacheDir, BaseService.VolleyCallback<JSONObject> callback);

    void addComplaint(CreateOrUpdateComplaint complaint, String token, File cacheDirectory, BaseService.VolleyCallback<JSONObject> callback);
}
