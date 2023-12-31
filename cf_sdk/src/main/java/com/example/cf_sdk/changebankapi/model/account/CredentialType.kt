package com.example.cf_sdk.changebankapi.model.account

import com.google.gson.annotations.SerializedName

/**
 *
 * Credential types.
 */
enum class CredentialType {
    @SerializedName("LOGIN")
    LOGIN, @SerializedName("PASSWORD")
    PASSWORD, @SerializedName("TEXT")
    TEXT, @SerializedName("OPTIONS")
    OPTIONS, @SerializedName("IMAGE_DATA")
    IMAGE_DATA
}