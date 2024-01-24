package com.example.cf_sdk.defination.request;

import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 * User profile request parameter model class.
 */
public class UserProfileParameter extends Parameters {

    public static UserProfileParameter create() {
        return new UserProfileParameter(new HashMap<String, String>());
    }

    public UserProfileParameter(Map<String, String> headers) {
        super(headers);
    }
}
