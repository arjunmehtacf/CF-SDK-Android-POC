package com.example.cf_sdk.changebankapi.parameter.account;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Handles parameters for a verify ach transfer request.
 */

public class VerifyAchTransferParameters extends Parameters {

    @SerializedName("transaction_id")
    private String mAchTransferId;

    @SerializedName("code")
    private String mVerificationCode;

    @SerializedName("memberId")
    private Long mMemberId;

    public static VerifyAchTransferParameters create(Map<String, String> headers,
                                                     String achTransferId,
                                                     String verificationCode) {
        return new VerifyAchTransferParameters(headers, achTransferId, verificationCode, null);
    }

    private VerifyAchTransferParameters(Map<String, String> headers,
                                        String achTransferId,
                                        String verificationCode,
                                        Long memberId) {
        super(headers);

        mAchTransferId = achTransferId;
        mVerificationCode = verificationCode;
        mMemberId = memberId;
    }

    public VerifyAchTransferParameters withMemberId(Long memberId) {
        return new VerifyAchTransferParameters(getHeaders(), mAchTransferId, mVerificationCode, memberId);
    }

    public String getAchTransferId() {
        return mAchTransferId;
    }

    public String getVerificationCode() {
        return mVerificationCode;
    }

    public Long getMemberId() {
        return mMemberId;
    }
}
