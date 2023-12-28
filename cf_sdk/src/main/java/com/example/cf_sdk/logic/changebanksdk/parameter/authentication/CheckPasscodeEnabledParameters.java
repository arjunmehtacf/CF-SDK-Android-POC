package com.example.cf_sdk.logic.changebanksdk.parameter.authentication;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles checking passcode enabled for username.
 */

public class CheckPasscodeEnabledParameters extends Parameters {

    private transient final String mUsername;


    public static CheckPasscodeEnabledParameters CreateWithUsername(String username){
        return new CheckPasscodeEnabledParameters(new HashMap<String, String>(), username);
    }

    public static CheckPasscodeEnabledParameters CreateUsingLoggedInUser(){
        return new CheckPasscodeEnabledParameters(new HashMap<String, String>(), null);
    }

    private CheckPasscodeEnabledParameters(Map<String, String> headers, String username) {
        super(headers);
        mUsername = username;
    }

    public String getUsername() {
        return mUsername;
    }


}
