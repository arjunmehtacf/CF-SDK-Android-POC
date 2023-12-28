package com.example.cf_sdk.logic.changebanksdk.parameter.authentication;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Parameters for resending two factor code for login.
 */

public class ResendTwoFactorParameters extends Parameters {
    private transient long mMemberId;

    public static ResendTwoFactorParameters create() {
        return new ResendTwoFactorParameters(new HashMap<String, String>());
    }

    private ResendTwoFactorParameters(Map<String, String> headers) {
        super(headers);
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }
}
