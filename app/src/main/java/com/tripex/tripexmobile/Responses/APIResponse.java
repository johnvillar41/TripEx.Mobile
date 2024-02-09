package com.tripex.tripexmobile.Responses;

import com.google.gson.annotations.SerializedName;

public class APIResponse<T> {

    @SerializedName("result")
    private final T result;

    @SerializedName("statusCode")
    private final int statusCode;

    @SerializedName("pageSize")
    private int pageSize;

    public APIResponse(T result, int statusCode, int pageSize) {
        this.result = result;
        this.statusCode = statusCode;
        this.pageSize = pageSize;
    }

    public APIResponse(T result, int statusCode) {
        this.result = result;
        this.statusCode = statusCode;
    }

    public T getResult() {
        return result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getPageSize() {
        return pageSize;
    }
}