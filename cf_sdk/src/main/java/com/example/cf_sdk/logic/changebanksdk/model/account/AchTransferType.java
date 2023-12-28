package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Enum for Ach Transfer Type to describe the direction of transfer.
 */
public enum AchTransferType {
    @SerializedName("BANK_TO_CARD")
    BANK_TO_CARD("BANK_TO_CARD"),

    @SerializedName("CARD_TO_BANK")
    CARD_TO_BANK("CARD_TO_BANK"),

    @SerializedName("CARD_TO_CARD")
    CARD_TO_CARD("CARD_TO_CARD"),

    @SerializedName("CARD_TO_BANK_RETURN")
    CARD_TO_BANK_RETURN("CARD_TO_BANK_RETURN"),

    @SerializedName("Debit")
    Debit("Debit"),

    @SerializedName("Credit")
    Credit("Credit");

    String mTypeName;

    AchTransferType(String typeName) {
        mTypeName = typeName;
    }

    public String getTypeName() {
        return mTypeName;
    }


}
