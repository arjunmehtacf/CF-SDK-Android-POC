package com.example.cf_sdk.changebankapi.parameter.member;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains parameters to make a successful request to Confidential.
 */

public class AddConfidentialParameters extends Parameters {
    @SerializedName("socialSecurity")
    private String mSsn;

    @SerializedName("dateOfBirth")
    private String mDob;

    @SerializedName("profilingSessionId")
    private final String mThreatMetrixSessionId;

    private transient long mMemberId;

    public static AddConfidentialParameters create(String dob,
                                                   String ssn,
                                                   String threatMetrixSessionId) {
        return new AddConfidentialParameters(new HashMap<String, String>(), dob, ssn, threatMetrixSessionId);
    }

    private AddConfidentialParameters(Map<String, String> headers, String dob, String ssn, String threatMetrixSessionId) {
        super(headers);

        mDob = dob;
        mSsn = ssn;
        mThreatMetrixSessionId = threatMetrixSessionId;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }
}
