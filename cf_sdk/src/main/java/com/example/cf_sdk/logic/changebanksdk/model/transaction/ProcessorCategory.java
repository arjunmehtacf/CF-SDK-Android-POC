package com.example.cf_sdk.logic.changebanksdk.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * Processor categories.
 */

public enum ProcessorCategory implements Serializable {
    UNKNOWN,

    @SerializedName("CREDIT")
    CREDIT,

    @SerializedName("DEBIT")
    DEBIT
}
