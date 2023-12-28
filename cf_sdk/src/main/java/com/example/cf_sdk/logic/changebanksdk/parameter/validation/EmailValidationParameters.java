package com.example.cf_sdk.logic.changebanksdk.parameter.validation;

import androidx.annotation.NonNull;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Email validation API parameters.
 */

public class EmailValidationParameters extends Parameters {

    @SerializedName("emailAddress")
    private String mEmail;

    public static EmailValidationParameters create(@NonNull String email) {
        return new EmailValidationParameters(new HashMap<String, String>(), email, false);
    }

    private EmailValidationParameters(Map<String, String> headers, String email, boolean skipAvailabilityCheck) {
        super(headers);

        mEmail = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailValidationParameters that = (EmailValidationParameters) o;

        return mEmail.equals(that.mEmail);
    }

    @Override
    public int hashCode() {
        return mEmail.hashCode();
    }

    public String getEmail() {
        return mEmail;
    }

}
