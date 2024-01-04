package com.example.cf_sdk.changebankapi.model.member

import com.google.gson.annotations.SerializedName

/**
 * Constants for log type
 */
enum class ChangebankLogLevel(levelName: String) {
    @SerializedName("DEBUG")
    DEBUG("Debug"), @SerializedName("ERROR")
    ERROR("Error");

    var levelName = ""

    init {
        this.levelName = levelName
    }
}