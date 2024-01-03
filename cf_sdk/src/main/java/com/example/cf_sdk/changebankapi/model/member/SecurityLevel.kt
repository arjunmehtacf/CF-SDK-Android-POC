package com.example.sdk_no_dagger.changebankapi.model.member

import com.google.gson.annotations.SerializedName

/**
 *
 * Enum for the Security Level.
 */
enum class SecurityLevel(levelName: String) {
    @SerializedName("HIGH")
    HIGH("High"), @SerializedName("MEDIUM")
    MEDIUM("Medium");

    var levelName = ""

    init {
        this.levelName = levelName
    }
}