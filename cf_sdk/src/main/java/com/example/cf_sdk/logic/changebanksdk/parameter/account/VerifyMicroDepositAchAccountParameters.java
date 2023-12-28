package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains parameters to verify Ach Account.
 */

public class VerifyMicroDepositAchAccountParameters extends Parameters {

    @SerializedName("amount1")
    private String mFirstAmount;

    @SerializedName("amount2")
    private String mSecondAmount;

    @SerializedName("bankAccID")
    private String mMemberId;


    private transient String mAchAccountId;

    private AchAccount achAccount;


    private VerifyMicroDepositAchAccountParameters(Map<String, String> headers, String firstAmount, String secondAmount, String achAccountId) {
        super(headers);

        mFirstAmount = firstAmount;
        mSecondAmount = secondAmount;
        mAchAccountId = achAccountId;
    }

    public String getMemberId() {
        return mMemberId;
    }

    public void setMemberId(String mMemberId) {
        this.mMemberId = mMemberId;
    }

    public String getAchAccountId() {
        return mAchAccountId;
    }

    public static VerifyMicroDepositAchAccountParameters create(
            String firstAmount, String secondAmount, String achAccountId) {
        return new VerifyMicroDepositAchAccountParameters(new HashMap<String, String>(), firstAmount, secondAmount, achAccountId);
    }
}
