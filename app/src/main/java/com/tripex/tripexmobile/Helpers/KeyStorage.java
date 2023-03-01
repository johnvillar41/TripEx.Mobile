package com.tripex.tripexmobile.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class KeyStorage {
    public static final String TOKEN = "Token";
    public static final String USER_LOGGED_IN = "User";
    public static final String PREFS_NAME = "Login Data";

    private static KeyStorage singleInstance = null;

    public static synchronized KeyStorage getInstance() {
        if (singleInstance == null)
            singleInstance = new KeyStorage();

        return singleInstance;
    }

    private KeyStorage() {

    }

    public String getUserId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(USER_LOGGED_IN, "");
    }

    public String getToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(TOKEN, "");
    }

    public void storeUserData(String token, String userLoggedIn, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.putString(USER_LOGGED_IN, userLoggedIn);
        editor.apply();
    }
}
