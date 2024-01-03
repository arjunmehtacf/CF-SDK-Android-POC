package com.example.sdk_no_dagger.changebankapi.model.member

import com.google.gson.annotations.SerializedName

/**
 * Constant for documents status
 */
enum class LastSuccessfulStage(lastSuccessfulStageName: String) {
    @SerializedName("MembershipCreated")
    MembershipCreated("MembershipCreated"), @SerializedName("AgreementsAccepted")
    AgreementsAccepted("AgreementsAccepted"), @SerializedName("IdScanPending")
    IdScanPending("IdScanPending"), @SerializedName("CIPPassed")
    CIPPassed("CIPPassed"), @SerializedName("OOWPassed")
    OOWPassed("OOWPassed"), @SerializedName("DocumentVerificationInProgress")
    DoumentVerificaionInProgress("DocumentVerificationInProgress"), @SerializedName("IdScanRequired")
    IdScanRequired("IdScanRequired"), @SerializedName("Denied")
    Denied("Denied"), @SerializedName("IdScanFailed")
    IdScanFailed("IdScanFailed"),  //    IdScanFailed
    @SerializedName("AccountCreated")
    AccountCreated("AccountCreated");

    var lastSuccessfulStageName = ""

    init {
        this.lastSuccessfulStageName = lastSuccessfulStageName
    }
}