package com.example.cf_sdk.changebankapi.parameter.authentication;


import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;

/**
 *
 * Get security profile request parameters
 */

public class GetSecurityProfileParameters extends Parameters {



    private transient long mMemberId;

    public GetSecurityProfileParameters(HashMap<String, String> headers) {
        super(headers);
    }


    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }

}
