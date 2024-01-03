package com.example.cf_sdk.changebankapi.parameter.member;


import com.example.cf_sdk.changebankapi.exception.PhoneNotValidException;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.example.cf_sdk.changebankapi.util.Validator;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Contains information to make a successful request to retrieve a phone verification code.
 */

public class SendPhoneCodeParameters extends Parameters {

    @SerializedName("phoneNumber")
    private final String phoneNumber;

    public static SendPhoneCodeParameters createWithPhone(
            Validator validator,
            Map<String, String> headers,
            String phone) throws PhoneNotValidException {
        if (!validator.validatePhoneNumber(phone)) throw new PhoneNotValidException();
        return new SendPhoneCodeParameters(headers, phone);
    }

    public static SendPhoneCodeParameters createWithPhone(
            Map<String, String> headers,
            String phone) {
        return new SendPhoneCodeParameters(headers, phone);
    }

    private SendPhoneCodeParameters(Map<String, String> headers, String phone) {
        super(headers);

        phoneNumber = phone;
    }
}
