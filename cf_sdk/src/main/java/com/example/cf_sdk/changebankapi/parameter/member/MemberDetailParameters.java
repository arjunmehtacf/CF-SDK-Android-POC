package com.example.cf_sdk.changebankapi.parameter.member;


import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Member Detail Parameters.
 */

public class MemberDetailParameters extends Parameters {

    @SerializedName("customerNumber")
    private transient String customerNumber;

    public static MemberDetailParameters create() {
        return new MemberDetailParameters(new HashMap<String, String>());
    }

    public MemberDetailParameters(Map<String, String> headers) {
        super(headers);
    }

    public void setCustomerNumber(String memberId) {
        customerNumber = memberId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
