package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Enum for Member Account Status
 */
public enum AccountStatus {
    UNKNOWN("Unknown"),

    @SerializedName("ACTIVE")
    ACTIVE("Active"),

    @SerializedName("PENDING_CLOSURE")
    PENDING_CLOSURE("Pending Closure"),

    @SerializedName("CLOSED")
    CLOSED("Closed"),

    @SerializedName("CREATED")
    CREATED("Created"),

    @SerializedName("COMPLIANCE_HOLD")
    COMPLIANCE_HOLD("Compliance Hold"),

    @SerializedName("Temporary Blocked")
    Temporary_Blocked("Temporary Blocked"),


    @SerializedName("Card to be activated")
    Card_to_be_activated("Card to be activated");

    String mStatusName;

    AccountStatus(String statusName) {
        mStatusName = statusName;
    }

    public String getStatusName() {
        return mStatusName;
    }
}
