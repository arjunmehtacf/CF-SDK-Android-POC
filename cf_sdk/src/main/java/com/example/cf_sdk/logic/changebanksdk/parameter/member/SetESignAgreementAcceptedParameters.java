package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Request Parameters for setting the esign agreement to true.
 */

public class SetESignAgreementAcceptedParameters extends Parameters {

    @SerializedName("eSignAgreementAccepted")
    private boolean mESignAgreementAccepted = true;


    private transient long mMemberId;

    public static SetESignAgreementAcceptedParameters Create(){
        return new SetESignAgreementAcceptedParameters(new HashMap<String, String>());
    }


    private SetESignAgreementAcceptedParameters(Map<String, String> headers) {
        super(headers);
    }


    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }
}
