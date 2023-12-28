package com.example.cf_sdk.logic;

public interface CFSDKResponseCallback<T> {
    void onSuccess(T successResponse);
    void onFailure(Throwable error) throws Exception;
}
