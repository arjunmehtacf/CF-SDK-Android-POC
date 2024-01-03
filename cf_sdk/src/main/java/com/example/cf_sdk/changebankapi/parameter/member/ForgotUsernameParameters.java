package com.example.cf_sdk.changebankapi.parameter.member;

import com.google.gson.annotations.SerializedName;
import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles parameters for forgot username endpoint.
 */

public class ForgotUsernameParameters extends Parameters {
    @SerializedName("emailAddress")
    private String mEmail;



    public static ForgotUsernameParameters create(String email) {
        return new ForgotUsernameParameters(
                new HashMap<String, String>(),
                email);
    }

    private ForgotUsernameParameters(Map<String, String> headers,
                                     String email) {
        super(headers);
        mEmail = email;
    }



}
