package com.example.cf_sdk.changebankapi.parameter.account;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;


public class GetAccountParameters extends Parameters {
    @SerializedName("accountNumber")
    private final String mAccountId;


    public static GetAccountParameters createById(String accountId) {
        return new GetAccountParameters(new HashMap<String, String>(), accountId);
    }

    private GetAccountParameters(Map<String, String> headers, String accountId) {
        super(headers);
        mAccountId = accountId;
    }

    public String getAccountId() {
        return mAccountId;
    }

    public String getmAccountId() {
        return mAccountId;
    }
}
