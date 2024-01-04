package com.example.cf_sdk.changebankapi.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Changebank roles.
 */
enum class Role {
    @SerializedName("USER")
    USER, @SerializedName("ADMIN")
    ADMIN
}