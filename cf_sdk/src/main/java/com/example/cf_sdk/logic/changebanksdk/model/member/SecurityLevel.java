package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Enum for the Security Level.
 */

public enum SecurityLevel {

    @SerializedName("HIGH")
    HIGH("High"),

    @SerializedName("MEDIUM")
    MEDIUM("Medium");

    String mLevelName;

    SecurityLevel(String levelName) {
        mLevelName = levelName;
    }

    public String getLevelName() {
        return mLevelName;
    }

}
