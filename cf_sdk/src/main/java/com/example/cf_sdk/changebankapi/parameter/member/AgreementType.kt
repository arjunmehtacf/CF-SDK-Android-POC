package com.example.cf_sdk.changebankapi.parameter.member

import com.google.gson.annotations.SerializedName

enum class AgreementType(var agreementTypeName: String) {
    @SerializedName("CARD_HOLDER_AGREEMENT")
    CARD_HOLDER_AGREEMENT("CHOLDERAGR"), @SerializedName("E_SIGN_AGREEMENT")
    E_SIGN_AGREEMENT("ELECTCOMS"), @SerializedName("FEE_SCHEDULE")
    FEE_SCHEDULE("FEESCHEDL"), @SerializedName("PATRIOT_ACT")
    PATRIOT_ACT("patriotAct"), @SerializedName("PRIVACY_POLICY")
    PRIVACY_POLICY("PRIVACYPOL"), @SerializedName("PROGRAM_TERMS_AGREEMENT")
    PROGRAM_TERMS_AGREEMENT("programTermsAgreement"), @SerializedName("SCHEDULE_A")
    SCHEDULE_A("FEESCHEDL"), @SerializedName("TERMS_OF_SERVICE")
    TERMS_OF_SERVICE("TERMSSERV"), @SerializedName("NOTICES")
    NOTICES("NOTICES");

}