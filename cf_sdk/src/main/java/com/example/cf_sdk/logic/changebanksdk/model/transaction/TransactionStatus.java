package com.example.cf_sdk.logic.changebanksdk.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * Transaction status.
 */

public enum TransactionStatus implements Serializable {
    UNKNOWN(""),

    @SerializedName("Settlement")
    SETTLEMENT("Settled"),

    @SerializedName("Pending")
    PENDING("Pending"),

    @SerializedName("Cancelled")
    CANCELLED("Cancelled"),

    @SerializedName("Rejected")
    REJECTED("Rejected");

    public final String label;

    TransactionStatus(String label) {
        this.label = label;
    }
}
