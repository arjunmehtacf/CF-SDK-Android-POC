package com.example.sdk_no_dagger.changebankapi.model.account

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