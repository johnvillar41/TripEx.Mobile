package com.tripex.tripexmobile.Services;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tripex.tripexmobile.Helpers.NukeSSLCerts;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseService {

    public static final String BASE_URL = "http://192.168.1.69:99/";
    public static final String API_KEY_VALUE = "pgH7QzFHJx4w46fI~5Uzi4RvtTwlEXp";
    public static final String API_KEY_NAME = "X-API-Key";
    public static final String BASE_URL_API = BASE_URL + "api/";

    private static final String CHARSET_NAME = "UTF-8";

    public interface VolleyCallback<T> {
        void onSuccess(T result);

        void onError(String errorMessage);

        void onFinishedTask();

        void onExceptionOccur(Exception exception);
    }

    public void transactJsonObjectData(File cacheDir, String token, String url, VolleyCallback<JSONObject> callback, JSONObject args, int requestType) {
        nukeSSLCerts(new NukeSSLCerts.OnNukeSSLCertExceptionTrigger() {
            @Override
            public void onException(Exception exception) {
                callback.onExceptionOccur(exception);
            }
        });

        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(cacheDir, 1024 * 1024);

        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonObjectRequest request = new JsonObjectRequest(requestType, url, args, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
                callback.onFinishedTask();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                parseErrorResponse(error, callback);
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                return parseAuthKey(token);
            }
        };
        requestQueue.add(request);
    }

    private Map<String, String> parseAuthKey(String token) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(API_KEY_NAME, API_KEY_VALUE);
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }
        return headers;
    }

    private <T> void parseErrorResponse(VolleyError error, VolleyCallback<T> callback) {
        try {
            if (error.networkResponse == null) {
                callback.onError("Error connecting to server. Please try again...");
                callback.onFinishedTask();
                return;
            }
            String result = new String(error.networkResponse.data, CHARSET_NAME);
            callback.onError(result);
            callback.onFinishedTask();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            callback.onExceptionOccur(e);
            callback.onFinishedTask();
        }

        if (error instanceof TimeoutError) {
            callback.onError("Network timeout. Please try again...");
        }

        if (error instanceof ParseError) {
            callback.onError("Error parsing Json Data");
        }

        error.printStackTrace();

        callback.onFinishedTask();
    }

    private void nukeSSLCerts(NukeSSLCerts.OnNukeSSLCertExceptionTrigger listener) {
        NukeSSLCerts.nuke(listener);
    }
}
