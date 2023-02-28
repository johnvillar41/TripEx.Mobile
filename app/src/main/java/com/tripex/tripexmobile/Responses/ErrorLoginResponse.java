package com.tripex.tripexmobile.Responses;

public class ErrorLoginResponse<T> extends APIResponse<T> {
    public ErrorLoginResponse(T result, int statusCode) {
        super(result, statusCode);
    }
}
