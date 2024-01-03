package com.example.sdk_no_dagger.changebankapi.model.member

import com.google.gson.annotations.SerializedName

/**
 * Constant for device os
 */
enum class ClientType(clientName: String) {
    @SerializedName("IOS")
    IOS("iOS"), @SerializedName("ANDROID")
    ANDROID("Android");

    var clientName = ""

    init {
        this.clientName = clientName
    }
}