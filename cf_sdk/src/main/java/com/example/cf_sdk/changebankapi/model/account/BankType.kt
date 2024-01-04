package com.example.cf_sdk.changebankapi.model.account

import com.google.gson.annotations.SerializedName

/**
 *
 * Bank types.
 */
enum class BankType {
    @SerializedName("CARDHOLDER_BANK")
    CARDHOLDER_BANK, @SerializedName("ACH_BANK")
    ACH_BANK
}