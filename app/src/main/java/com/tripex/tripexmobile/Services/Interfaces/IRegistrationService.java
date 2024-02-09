package com.tripex.tripexmobile.Services.Interfaces;

import com.tripex.tripexmobile.Models.User;
import com.tripex.tripexmobile.Services.BaseService;

import org.json.JSONObject;

import java.io.File;

public interface IRegistrationService {
    void createUser(User user, String token, File cacheDirectory, BaseService.VolleyCallback<JSONObject> jsonObjectVolleyCallback);
}
