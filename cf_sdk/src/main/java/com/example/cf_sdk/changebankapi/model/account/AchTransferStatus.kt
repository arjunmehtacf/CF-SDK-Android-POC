package com.example.sdk_no_dagger.changebankapi.model.account

import com.google.gson.annotations.SerializedName

enum class AchTransferStatus(statusName: String) {
    @SerializedName("CREATED")
    CREATED("Created"), @SerializedName("PENDING")
    PENDING("Pending"), @SerializedName("SETTLED")
    SETTLED("Settled"), @SerializedName("FAILED")
    FAILED("Failed"), @SerializedName("CANCELLED")
    CANCELLED("Cancelled"), @SerializedName("Approved")
    Approved("Approved"), @SerializedName("Reversed")
    Reversed("Reversed");

    var statusName = ""

    init {
        this.statusName = statusName
    }
}