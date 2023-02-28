package com.tripex.tripexmobile.Responses;

public class SuccessLoginAPIResponse<T> extends APIResponse<T>{
    public SuccessLoginAPIResponse(T result, int statusCode) {
        super(result, statusCode);
    }

    public T getUserId() {
        return null;
    }
}
