package com.example.cf_sdk.defifination;

import com.example.cf_sdk.logic.CFSDKResponseCallback;

public interface CFCallProvider {
    void getSettingsData(String appId, String os, CFSDKResponseCallback callback);
}
