package com.example.cf_sdk.changebankapi.parameter.authentication;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Handles login information to authenticate.
 */

public class UsernameParameters extends Parameters {

    @SerializedName("username")
    private String mUsername = "";


    public UsernameParameters(Map<String, String> headers, String username) {
        super(headers);
        mUsername = username;
    }

    public String getUsername() {
        return mUsername;
    }


}
