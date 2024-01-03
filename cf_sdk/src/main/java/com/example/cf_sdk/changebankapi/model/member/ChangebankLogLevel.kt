package com.example.sdk_no_dagger.changebankapi.model.member

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