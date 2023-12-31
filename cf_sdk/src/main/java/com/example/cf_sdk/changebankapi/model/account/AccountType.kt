package com.example.cf_sdk.changebankapi.model.account

import com.google.gson.annotations.SerializedName

/**
 *
 * Enum for Member Account Type
 */
enum class AccountType(typeName: String) {
    UNKNOWN("Unknown"), @SerializedName("CHEQUE")
    CHEQUE("Checking"), @SerializedName("SAVINGS")
    SAVINGS("Savings");

    var typeName = ""

    init {
        this.typeName = typeName
    }
}