package com.example.cf_sdk.changebankapi.model.member

import com.google.gson.annotations.SerializedName

/**
 * Enumeration constant card status
 */
enum class ManualVerification(manualVerification: String) {
    @SerializedName("NOT_REQUIRED")
    NOT_REQUIRED("NOT_REQUIRED"), @SerializedName("PENDING_REVIEW")
    PENDING_REVIEW("PENDING_REVIEW"), @SerializedName("PENDING_APPROVAL")
    PENDING_APPROVAL("PENDING_APPROVAL"), @SerializedName("PENDING_ADDITIONAL_DOCUMENTS")
    PENDING_ADDITIONAL_DOCUMENTS("PENDING_ADDITIONAL_DOCUMENTS"), @SerializedName("APPROVED")
    APPROVED("APPROVED"), @SerializedName("DENIED")
    DENIED("DENIED");

    var mManualVerificationName = ""

    init {
        mManualVerificationName = manualVerification
    }

    fun geManualVerificationName(): String {
        return mManualVerificationName
    }
}