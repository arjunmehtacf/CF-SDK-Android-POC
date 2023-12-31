package com.example.cf_sdk.changebankapi.parameter.member;

import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Parameters for
 */

public class RefreshOowParameters extends Parameters {
    private transient long mMemberId;

    public static RefreshOowParameters create() {
        return new RefreshOowParameters(new HashMap<String, String>());
    }

    private RefreshOowParameters(Map<String, String> headers) {
        super(headers);
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }
}
