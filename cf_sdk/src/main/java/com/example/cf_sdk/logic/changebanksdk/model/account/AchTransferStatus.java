package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;


public enum AchTransferStatus {
    @SerializedName("CREATED")
    CREATED("Created"),

    @SerializedName("PENDING")
    PENDING("Pending"),

    @SerializedName("SETTLED")
    SETTLED("Settled"),

    @SerializedName("FAILED")
    FAILED("Failed"),

    @SerializedName("CANCELLED")
    CANCELLED("Cancelled"),

    @SerializedName("Approved")
    Approved("Approved"),

    @SerializedName("Reversed")
    Reversed("Reversed");

    String mStatusName;

    AchTransferStatus(String statusName) {
        mStatusName = statusName;
    }

    public String getStatusName() {
        return mStatusName;
    }
}
