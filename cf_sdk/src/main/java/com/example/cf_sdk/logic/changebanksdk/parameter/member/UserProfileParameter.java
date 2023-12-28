package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

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
