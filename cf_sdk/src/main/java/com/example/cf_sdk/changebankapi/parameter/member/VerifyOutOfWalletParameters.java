package com.example.cf_sdk.changebankapi.parameter.member;


import com.example.cf_sdk.changebankapi.model.oow.OowAnswer;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Handles parameters for a verify OOW Answers.
 */

public class VerifyOutOfWalletParameters extends Parameters {
    @SerializedName("referenceNumber")
    private String mReferenceNumber;

    @SerializedName("answers")
    private List<OowAnswer> mAnswers;

    private transient long mMemberId;

    public static VerifyOutOfWalletParameters create(String referenceNumber,
                                                     List<OowAnswer> answers) {
        return new VerifyOutOfWalletParameters(
                new HashMap<String, String>(),
                referenceNumber,
                answers);
    }

    public VerifyOutOfWalletParameters(Map<String, String> headers,
                                       String referenceNumber,
                                       List<OowAnswer> answers) {
        super(headers);
        mAnswers = answers;
        mReferenceNumber = referenceNumber;
    }

    public String getReferenceNumber() {
        return mReferenceNumber;
    }

    public List<OowAnswer> getAnswers() {
        return mAnswers;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }
}
