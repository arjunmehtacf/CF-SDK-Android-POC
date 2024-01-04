package com.example.cf_sdk.changebankapi.parameter.member;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
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


}
