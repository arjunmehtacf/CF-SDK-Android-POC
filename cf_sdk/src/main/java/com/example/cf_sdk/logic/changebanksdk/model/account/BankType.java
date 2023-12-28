package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Bank types.
 */

public enum BankType {
    @SerializedName("CARDHOLDER_BANK")
    CARDHOLDER_BANK,

    @SerializedName("ACH_BANK")
    ACH_BANK,
}
