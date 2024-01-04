package com.example.cf_sdk.changebankapi.model.transaction

import com.google.gson.annotations.SerializedName

/**
 *
 * Transaction filter.
 */
enum class TransactionFilter {
    @SerializedName("All")
    ALL, @SerializedName("Pending")
    PENDING, @SerializedName("Completed")
    COMPLETED
}