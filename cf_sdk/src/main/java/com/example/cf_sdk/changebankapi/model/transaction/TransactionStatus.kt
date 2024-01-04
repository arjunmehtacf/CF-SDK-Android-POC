package com.example.cf_sdk.changebankapi.model.transaction

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 * Transaction status.
 */
enum class TransactionStatus(val label: String) : Serializable {
    UNKNOWN(""), @SerializedName("Settlement")
    SETTLEMENT("Settled"), @SerializedName("Pending")
    PENDING("Pending"), @SerializedName("Cancelled")
    CANCELLED("Cancelled"), @SerializedName("Rejected")
    REJECTED("Rejected");
}