package com.example.cf_sdk.logic.changebanksdk.parameter.ingo;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles ingo ssn information to authenticate.
 */

public class VerifyIngoSSNParameters extends Parameters {

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("ssn")
    private String ssn;

    @SerializedName("userId")
    private String userId;


    public VerifyIngoSSNParameters(Map<String, String> headers, String deviceType, String ssn) {
        super(headers);
        this.deviceType = deviceType;
        this.ssn = ssn;
    }

    public static VerifyIngoSSNParameters Create(String deviceType, String ssn) {
        return new VerifyIngoSSNParameters(new HashMap<String, String>(), deviceType, ssn);
    }
}
