package com.example.cf_sdk.changebankapi.parameter.member;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Request Parameters for updating email address.
 */

public class UpdateEmailAddressParameters extends Parameters {

    @SerializedName("emailAddress")
    private String emailAddress;

    public static UpdateEmailAddressParameters Create(String email){
        return new UpdateEmailAddressParameters(new HashMap<String, String>(), email);
    }

    private UpdateEmailAddressParameters(Map<String, String> headers, String emailAddress) {
        super(headers);
        this.emailAddress = emailAddress;
    }

    public String getEmail() {
        return emailAddress;
    }
}
