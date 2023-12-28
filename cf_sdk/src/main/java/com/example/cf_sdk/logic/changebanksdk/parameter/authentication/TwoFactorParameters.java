package com.example.cf_sdk.logic.changebanksdk.parameter.authentication;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Two Factor request parameters.
 */

public class TwoFactorParameters extends Parameters {

    @SerializedName("memberId")
    private final Long mMemberId;

    @SerializedName("code")
    private final int mVerificationCode;

    public static TwoFactorParameters create(int verificationCode) {
        return new TwoFactorParameters(new HashMap<String, String>(), null, verificationCode);
    }

    private TwoFactorParameters(Map<String, String> headers,
                                Long memberId,
                                int verificationCode) {
        super(headers);
        mVerificationCode = verificationCode;
        mMemberId = memberId;
    }

    public TwoFactorParameters withMemberId(Long memberId) {
        return new TwoFactorParameters(getHeaders(), memberId, mVerificationCode);
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public int getVerificationCode() {
        return mVerificationCode;
    }
}
