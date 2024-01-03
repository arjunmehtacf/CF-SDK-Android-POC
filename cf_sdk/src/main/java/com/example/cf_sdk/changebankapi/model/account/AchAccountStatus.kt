package com.example.sdk_no_dagger.changebankapi.model.account

import com.google.gson.annotations.SerializedName

/**
 * Status for an [AchAccount].
 */
enum class AchAccountStatus(statusName: String) {
    NONE(""), @SerializedName("LOCKED")
    LOCKED("Locked"), @SerializedName("AUTHENTICATING")
    AUTHENTICATING("Authenticating"), @SerializedName("MFA_REQUIRED")
    MFA_REQUIRED("Mfa Required"), @SerializedName("PROCESSING")
    PROCESSING("Processing"), @SerializedName("FAILED")
    FAILED("Failed"), @SerializedName("PENDING")
    PENDING("Pending"), @SerializedName("REFRESHING")
    REFRESHING("Refreshing"), @SerializedName("LINKED")
    LINKED("Linked"), @SerializedName("UNLINKED")
    UNLINKED("Unlinked"), @SerializedName("DELETED")
    DELETED("Deleted"), @SerializedName("UNAUTHENTICATED")
    UNAUTHENTICATED("Unauthenticated"), @SerializedName("ACTIVE")
    ACTIVE("Active"), @SerializedName("CANCEL")
    CANCEL("Cancel");

    var statusName = ""

    init {
        this.statusName = statusName
    }

    val isTransitional: Boolean
        get() = this == REFRESHING || this == PROCESSING || this == AUTHENTICATING || this == DELETED
}