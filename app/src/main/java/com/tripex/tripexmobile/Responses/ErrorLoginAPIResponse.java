package com.tripex.tripexmobile.Responses;

public class ErrorLoginAPIResponse<T> extends APIResponse<T> {
    public ErrorLoginAPIResponse(T result, int statusCode) {
        super(result, statusCode);
    }
}
