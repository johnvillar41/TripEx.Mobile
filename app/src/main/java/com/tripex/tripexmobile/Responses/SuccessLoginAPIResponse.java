package com.tripex.tripexmobile.Responses;

import com.google.gson.annotations.SerializedName;

public class SuccessLoginAPIResponse<T> extends APIResponse<T>{

    @SerializedName("userId")
    private final String userId;

    @SerializedName("fullName")
    private final String fullName;

    @SerializedName("imageLink")
    private final String imageLink;

    @SerializedName("isFirstTimeUser")
    private final Boolean isFirstTimeUser;

    public SuccessLoginAPIResponse(T result, int statusCode, String userId, String fullName, String imageLink, Boolean isFirstTimeUser) {
        super(result, statusCode);
        this.userId = userId;
        this.fullName = fullName;
        this.imageLink = imageLink;
        this.isFirstTimeUser = isFirstTimeUser;
    }

    public String getUserId() {
        return userId;
    }

    public Boolean getFirstTimeUser() {
        return isFirstTimeUser;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImageLink() {
        return imageLink;
    }
}
