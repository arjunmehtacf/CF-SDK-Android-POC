package com.example.cf_sdk.changebankapi.model.account

import com.google.gson.annotations.SerializedName

/**
 * Enum for Member Account Status
 */
enum class AccountStatus(statusName: String) {
    UNKNOWN("Unknown"), @SerializedName("ACTIVE")
    ACTIVE("Active"), @SerializedName("PENDING_CLOSURE")
    PENDING_CLOSURE("Pending Closure"), @SerializedName("CLOSED")
    CLOSED("Closed"), @SerializedName("CREATED")
    CREATED("Created"), @SerializedName("COMPLIANCE_HOLD")
    COMPLIANCE_HOLD("Compliance Hold"), @SerializedName("Temporary Blocked")
    Temporary_Blocked("Temporary Blocked"), @SerializedName("Card to be activated")
    Card_to_be_activated("Card to be activated");

    var statusName = ""

    init {
        this.statusName = statusName
    }
}