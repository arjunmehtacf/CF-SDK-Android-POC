package com.example.cf_sdk.changebankapi.parameter.validation;

import androidx.annotation.NonNull;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 *
 * Parameters for Phone Validation.
 */

public class PhoneValidationParameters extends Parameters {
    @SerializedName("phoneNumber")
    private String mPhone;

    public static PhoneValidationParameters create(String phone) {
        return new PhoneValidationParameters(phone);
    }

    private PhoneValidationParameters(String phone) {
        super(new HashMap<String, String>());
        mPhone = phone;
    }

    @NonNull
    public String getPhone() {
        return mPhone != null? mPhone : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneValidationParameters that = (PhoneValidationParameters) o;

        return mPhone.equals(that.mPhone);

    }

    @Override
    public int hashCode() {
        return mPhone.hashCode();
    }
}
