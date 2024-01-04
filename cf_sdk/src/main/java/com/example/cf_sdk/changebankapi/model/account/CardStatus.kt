package com.example.cf_sdk.changebankapi.model.account

import com.google.gson.annotations.SerializedName

/**
 *
 * Enum for card status
 */
enum class CardStatus(statusName: String) {
    @SerializedName("ACTIVE")
    ACTIVE("Active"), @SerializedName("ADMIN_BLOCKED")
    ADMIN_BLOCKED("Blocked by admin"), @SerializedName("PIN_BLOCKED")
    PIN_BLOCKED("Pin Blocked"), @SerializedName("CARDHOLDER_BLOCKED")
    BLOCKED("Locked"), @SerializedName("CANCELED")
    CANCELED("Canceled"), @SerializedName("INACTIVE")
    INACTIVE("Inactive"), @SerializedName("LOST")
    LOST("Lost"), @SerializedName("PENDING_ACTIVATION")
    PENDING_ACTIVATION("Pending Activation"), @SerializedName("STOLEN")
    STOLEN("Stolen"), @SerializedName("Temporary Blocked")
    Temporary_Blocked("Temporary Blocked"), @SerializedName("Card to be activated")
    Card_to_be_activated("Card to be activated");

    var statusName = ""

    init {
        this.statusName = statusName
    }
}