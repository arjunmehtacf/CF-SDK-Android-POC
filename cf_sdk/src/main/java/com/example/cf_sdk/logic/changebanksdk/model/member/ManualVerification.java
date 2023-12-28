package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration constant card status
 */
public enum ManualVerification {

    @SerializedName("NOT_REQUIRED")
    NOT_REQUIRED("NOT_REQUIRED"),

    @SerializedName("PENDING_REVIEW")
    PENDING_REVIEW("PENDING_REVIEW"),

    @SerializedName("PENDING_APPROVAL")
    PENDING_APPROVAL("PENDING_APPROVAL"),

    @SerializedName("PENDING_ADDITIONAL_DOCUMENTS")
    PENDING_ADDITIONAL_DOCUMENTS("PENDING_ADDITIONAL_DOCUMENTS"),

    @SerializedName("APPROVED")
    APPROVED("APPROVED"),

    @SerializedName("DENIED")
    DENIED("DENIED");

    String mManualVerificationName;

    ManualVerification(String manualVerification) {
        mManualVerificationName = manualVerification;
    }

    public String geManualVerificationName() {
        return mManualVerificationName;
    }
}
