package com.example.cf_sdk.changebankapi.parameter.account;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import com.example.cf_sdk.changebankapi.model.account.AchAccountStatus;
import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Ach Account parameters.
 */

public class AchAccountParameters extends Parameters {
    private AchAccountStatus mAchAccountFilter;
    private String accountNumber;

    public static AchAccountParameters create(@NonNull AchAccountStatus filterStatus) {
        return new AchAccountParameters(new HashMap<String, String>(), filterStatus, null);
    }

    public static AchAccountParameters create() {
        return new AchAccountParameters(new HashMap<String, String>(), AchAccountStatus.NONE, null);
    }

    private AchAccountParameters(Map<String, String> headers,
                                 AchAccountStatus achAccountStatus,
                                 String memberId) {
        super(headers);

        mAchAccountFilter = achAccountStatus;
        accountNumber = memberId;
    }

    public AchAccountParameters withMemberId(String memberId) {
        return new AchAccountParameters(getHeaders(), mAchAccountFilter, memberId);
    }


    public AchAccountStatus getAchAccountStatus() {
        return mAchAccountFilter;
    }

    public String getMemberId(){
        return accountNumber;
    }

    public AchAccountParameters(Map<String, String> headers,String customerNumber){
        super(headers);
        accountNumber = customerNumber;
    }

    public AchAccountParameters createByAccountNumber(String accountNumber)
    {
        return new AchAccountParameters(getHeaders(),accountNumber);
    }
}
