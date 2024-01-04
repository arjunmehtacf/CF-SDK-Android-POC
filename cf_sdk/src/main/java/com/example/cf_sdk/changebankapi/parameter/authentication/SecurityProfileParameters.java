package com.example.cf_sdk.changebankapi.parameter.authentication;


import com.example.cf_sdk.changebankapi.model.member.SecurityLevel;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 *
 * Update security profile request parameters
 */

public class SecurityProfileParameters extends Parameters {


    @SerializedName("securityLevel")
    private SecurityLevel mSecurityLevel;


    private transient long mMemberId;

    private SecurityProfileParameters(HashMap<String, String> headers, SecurityLevel securityLevel) {
        super(headers);
        mSecurityLevel = securityLevel;
    }


    public static SecurityProfileParameters Create(SecurityLevel securityLevel) {
        return new SecurityProfileParameters(new HashMap<String, String>(), securityLevel);
    }


    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }

}
