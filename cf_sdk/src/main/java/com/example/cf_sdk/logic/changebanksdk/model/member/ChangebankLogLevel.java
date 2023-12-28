package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 * Constants for log type
 */

public enum ChangebankLogLevel {
    @SerializedName("DEBUG")
    DEBUG("Debug"),

    @SerializedName("ERROR")
    ERROR("Error");

    String mLevelName;

    ChangebankLogLevel(String levelName) {
        mLevelName = levelName;
    }

    public String getLevelName() {
        return mLevelName;
    }
}
