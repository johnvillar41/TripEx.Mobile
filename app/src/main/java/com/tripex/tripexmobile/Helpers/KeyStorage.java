package com.tripex.tripexmobile.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class KeyStorage {
    public static final String TOKEN = "Token";
    public static final String USER_LOGGED_IN = "User";
    public static final String PREFS_NAME = "Login Data";
    public static final String USER_FULL_NAME = "FullName";
    private static final String USER_IMAGE_PATH = "ImagePath";
    private static final String IS_FIRST_TIME_USER = "IsFirstTimeUser";
    private static final String ROLE_ID = "5cb02642-81cf-4ad3-a823-1020ab1571df";

    public static String getUserId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_LOGGED_IN, "");
    }

    public static String getToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(TOKEN, "");
    }

    public static boolean getIsFirstTimeUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(IS_FIRST_TIME_USER, false);
    }

    public static String getUserFullName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_FULL_NAME, "");
    }

    public static String getUserImagePath(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_IMAGE_PATH, "");
    }

    public static void storeUserData(String token, String userLoggedIn, String fullName, String imagePath, Boolean isFirstTimeUser, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.putString(USER_LOGGED_IN, userLoggedIn);
        editor.putString(USER_FULL_NAME, fullName);
        editor.putString(USER_IMAGE_PATH, imagePath);
        editor.putBoolean(IS_FIRST_TIME_USER, isFirstTimeUser);
        editor.apply();
    }

    public static void clearUserData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN);
        editor.remove(USER_LOGGED_IN);
        editor.remove(USER_FULL_NAME);
        editor.remove(USER_IMAGE_PATH);
        editor.remove(IS_FIRST_TIME_USER);
        editor.apply();
    }

    public static String getRoleId() {
        return ROLE_ID;
    }
}
