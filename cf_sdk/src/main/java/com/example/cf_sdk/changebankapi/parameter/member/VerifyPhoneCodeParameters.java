package com.example.cf_sdk.changebankapi.parameter.member;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles parameters for a verify phone request.
 */

public class VerifyPhoneCodeParameters extends Parameters {
    @SerializedName("phoneNumber")
    private final String mPhone;

    @SerializedName("mfaCode")
    private final String mVerificationCode;

    private transient long mMemberId;

    public static VerifyPhoneCodeParameters create(String phone,
                                                   String verificationCode) {
        return new VerifyPhoneCodeParameters(new HashMap<String, String>(), phone, verificationCode);
    }

    private VerifyPhoneCodeParameters(Map<String, String> headers,
                                     String phone,
                                     String verificationCode) {
        super(headers);

        mPhone = phone;
        mVerificationCode = verificationCode;
    }

    public long getMemberId() {
        return mMemberId;
    }

    public void setMemberId(long memberId) {
        this.mMemberId = memberId;
    }

    public String getPhone() {
        return mPhone;
    }
}
