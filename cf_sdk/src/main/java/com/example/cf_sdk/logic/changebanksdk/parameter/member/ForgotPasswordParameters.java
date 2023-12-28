package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles parameters for forgot password endpoint.
 */

public class ForgotPasswordParameters extends Parameters {

    @SerializedName("emailAddress")
    private String emailAddress;

    @SerializedName("username")
    private String mUserName;

    public static ForgotPasswordParameters create(String username,
                                                  String emailAddress) {
        return new ForgotPasswordParameters(
                new HashMap<String, String>(), username,emailAddress);
    }

    private ForgotPasswordParameters(Map<String, String> headers,
                                     String username,
                                     String email) {
        super(headers);
        mUserName = username;
        emailAddress=email;
    }
}
