package com.example.cf_sdk.changebankapi.parameter.account;


import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;

/**
 *
 * Parameters for Direct Deposit Form.
 */

public class DirectDepositParameters extends Parameters {
    private String mAccountId;

    public static DirectDepositParameters create(String cardId) {
        return new DirectDepositParameters(new HashMap<String, String>(), cardId);
    }

    private DirectDepositParameters(HashMap<String, String> headers, String accountId) {
        super(headers);

        mAccountId = accountId;
    }

    public String getAccountId() {
        return mAccountId;
    }

    public void setAccountId(String account){
        mAccountId=account;
    }
}
