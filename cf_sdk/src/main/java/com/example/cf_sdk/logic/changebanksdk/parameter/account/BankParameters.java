package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains request parameter for bank parameter
 */

public class BankParameters extends Parameters {


    private String mBankId;

    public BankParameters(Map<String, String> headers, String bankId) {
        super(headers);
        mBankId = bankId;
    }



    public String getBankId() {
        return mBankId;
    }

    public static BankParameters create(HashMap<String, String> headers, String bankId) {
        return new BankParameters(headers, bankId);
    }
}
