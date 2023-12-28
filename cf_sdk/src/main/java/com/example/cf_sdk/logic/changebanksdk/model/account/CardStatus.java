package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Enum for card status
 */
public enum CardStatus {
    @SerializedName("ACTIVE")
    ACTIVE("Active"),

    @SerializedName("ADMIN_BLOCKED")
    ADMIN_BLOCKED("Blocked by admin"),

    @SerializedName("PIN_BLOCKED")
    PIN_BLOCKED("Pin Blocked"),

    @SerializedName("CARDHOLDER_BLOCKED")
    BLOCKED("Locked"),

    @SerializedName("CANCELED")
    CANCELED("Canceled"),

    @SerializedName("INACTIVE")
    INACTIVE("Inactive"),

    @SerializedName("LOST")
    LOST("Lost"),

    @SerializedName("PENDING_ACTIVATION")
    PENDING_ACTIVATION("Pending Activation"),

    @SerializedName("STOLEN")
    STOLEN("Stolen"),

    @SerializedName("Temporary Blocked")
    Temporary_Blocked("Temporary Blocked"),

    @SerializedName("Card to be activated")
    Card_to_be_activated("Card to be activated");

    String mStatusName;

    CardStatus(String statusName) {
        mStatusName = statusName;
    }

    public String getStatusName() {
        return mStatusName;
    }
}
