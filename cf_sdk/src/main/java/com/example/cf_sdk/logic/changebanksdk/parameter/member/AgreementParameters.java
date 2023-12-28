package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Agreement Parameters.
 */

public class AgreementParameters extends Parameters {
    private transient AgreementType mAgreementType;

    public AgreementParameters(Map<String, String> headers, AgreementType agreementType) {
        super(headers);
        mAgreementType = agreementType;
    }

    public void setAgreementType(AgreementType agreementType) {
        mAgreementType = agreementType;
    }

    public AgreementType getAgreementType() {
        return mAgreementType;
    }

    public static AgreementParameters create(AgreementType agreementType) {
        return new AgreementParameters(new HashMap<String, String>(), agreementType);
    }

    public enum AgreementType{

        @SerializedName("CARD_HOLDER_AGREEMENT")
        CARD_HOLDER_AGREEMENT("CHOLDERAGR"),

        @SerializedName("E_SIGN_AGREEMENT")
        E_SIGN_AGREEMENT("ELECTCOMS"),

        @SerializedName("FEE_SCHEDULE")
        FEE_SCHEDULE("FEESCHEDL"),

        @SerializedName("PATRIOT_ACT")
        PATRIOT_ACT("patriotAct"),

        @SerializedName("PRIVACY_POLICY")
        PRIVACY_POLICY("PRIVACYPOL"),

        @SerializedName("PROGRAM_TERMS_AGREEMENT")
        PROGRAM_TERMS_AGREEMENT("programTermsAgreement"),

        @SerializedName("SCHEDULE_A")
        SCHEDULE_A("FEESCHEDL"),

        @SerializedName("TERMS_OF_SERVICE")
        TERMS_OF_SERVICE("TERMSSERV"),

        @SerializedName("NOTICES")
        NOTICES("NOTICES");

        String mAgreementType;

        AgreementType(String agreementType) {
            mAgreementType = agreementType;
        }

        public String getAgreementTypeName() {
            return mAgreementType;
        }
    }
}
