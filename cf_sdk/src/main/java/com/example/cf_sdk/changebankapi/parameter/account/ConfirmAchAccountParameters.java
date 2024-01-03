package com.example.cf_sdk.changebankapi.parameter.account;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains parameters to confirm Ach Account.
 */

public class ConfirmAchAccountParameters extends Parameters {

    @SerializedName("accountNumber")
    private String mAccountNumber;

    @SerializedName("routingNumber")
    private String mRoutingNumber;

    @SerializedName("accountName")
    private String mAccountName;

    private transient String mAchAccountId;


    private ConfirmAchAccountParameters(Map<String, String> headers, String accountNumber, String routingNumber,
                                        String accountName, String achAccountId) {
        super(headers);

        mAccountNumber = accountNumber;
        mRoutingNumber = routingNumber;
        mAccountName = accountName;
        mAchAccountId = achAccountId;

    }


    public String getAccountNumber() {
        return mAccountNumber;
    }

    public String getRoutingNumber() {
        return mRoutingNumber;
    }

    public String getAccountName() {
        return mAccountName;
    }

    public String getAchAccountId() {
        return mAchAccountId;
    }

    public static ConfirmAchAccountParameters create(
            HashMap<String, String> loggedInHeader,
            String accountNumber,
            String routingNumber,
            String accountName,
            String achAccountId) {
        return new ConfirmAchAccountParameters(loggedInHeader, accountNumber, routingNumber, accountName, achAccountId);
    }
}
