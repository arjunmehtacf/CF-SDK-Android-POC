package com.example.cf_sdk.changebankapi.model.zendesk

import com.google.gson.annotations.SerializedName

enum class RequestStatusMapped {
    @SerializedName("new")
    New, @SerializedName("open")
    Open, @SerializedName("pending")
    Pending, @SerializedName("hold")
    Hold, @SerializedName("solved")
    Solved, @SerializedName("closed")
    Closed
}