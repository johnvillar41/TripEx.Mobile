package com.tripex.tripexmobile.Responses;

public class SuccessLoginResponse<T> extends APIResponse<T>{
    public SuccessLoginResponse(T result, int statusCode) {
        super(result, statusCode);
    }
}
