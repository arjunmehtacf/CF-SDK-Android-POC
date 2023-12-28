package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Enum for Member Account Type
 */
public enum AccountType {
    UNKNOWN("Unknown"),

    @SerializedName("CHEQUE")
    CHEQUE("Checking"),

    @SerializedName("SAVINGS")
    SAVINGS("Savings");

    String mTypeName;

    AccountType(String typeName) {
        mTypeName = typeName;
    }

    public String getTypeName() {
        return mTypeName;
    }
}
