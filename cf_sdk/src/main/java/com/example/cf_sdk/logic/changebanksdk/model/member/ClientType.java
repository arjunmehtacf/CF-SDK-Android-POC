package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 * Constant for device os
 */

public enum ClientType {
    @SerializedName("IOS")
    IOS("iOS"),

    @SerializedName("ANDROID")
    ANDROID("Android");

    String mClientName;

    ClientType(String clientName) {
        mClientName = clientName;
    }

    public String getClientName() {
        return mClientName;
    }
}
