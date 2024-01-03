package com.example.sdk_no_dagger.changebankapi.model

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