package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 * Constant for documents status
 */
public enum LastSuccessfulStage {

    @SerializedName("MembershipCreated")
    MembershipCreated("MembershipCreated"),

    @SerializedName("AgreementsAccepted")
    AgreementsAccepted("AgreementsAccepted"),

    @SerializedName("IdScanPending")
    IdScanPending("IdScanPending"),

    @SerializedName("CIPPassed")
    CIPPassed("CIPPassed"),

    @SerializedName("OOWPassed")
    OOWPassed("OOWPassed"),

    @SerializedName("DocumentVerificationInProgress")
    DoumentVerificaionInProgress("DocumentVerificationInProgress"),

    @SerializedName("IdScanRequired")
    IdScanRequired("IdScanRequired"),

    @SerializedName("Denied")
    Denied("Denied"),

    @SerializedName("IdScanFailed")
    IdScanFailed("IdScanFailed"),
//    IdScanFailed

    @SerializedName("AccountCreated")
    AccountCreated("AccountCreated");

    String mLastSuccessfulStageName;

    LastSuccessfulStage(String lastSuccessfulStageName) {
        mLastSuccessfulStageName = lastSuccessfulStageName;
    }

    public String getLastSuccessfulStageName() {
        return mLastSuccessfulStageName;
    }
}
