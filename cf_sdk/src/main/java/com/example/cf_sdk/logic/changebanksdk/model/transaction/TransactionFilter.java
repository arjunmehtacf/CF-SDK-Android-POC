package com.example.cf_sdk.logic.changebanksdk.model.transaction;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Transaction filter.
 */

public enum TransactionFilter {

    @SerializedName("All")
    ALL,

    @SerializedName("Pending")
    PENDING,

    @SerializedName("Completed")
    COMPLETED
}
