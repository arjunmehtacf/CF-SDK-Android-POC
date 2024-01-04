package com.example.cf_sdk.changebankapi.model.transaction

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 * Processor categories.
 */
enum class ProcessorCategory : Serializable {
    UNKNOWN, @SerializedName("CREDIT")
    CREDIT, @SerializedName("DEBIT")
    DEBIT
}