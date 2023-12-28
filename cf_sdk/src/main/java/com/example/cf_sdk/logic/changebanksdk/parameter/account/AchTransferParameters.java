package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransferType;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Contains parameters to make a successful request to do an Ach Transfer.
 */

public class AchTransferParameters extends Parameters {

    @SerializedName("achAccountId")
    private String mAchAccountId;

    @SerializedName("transferType")
    private AchTransferType mTransferType;

    @SerializedName("bankAccID")
    private String mAccountId;

    @SerializedName("amount")
    private String mAmount;

    @SerializedName("memberId")
    private Long mMemberId;

    private transient AchAccount mAchAccount;

    private transient Account mAccount;


    public static AchTransferParameters create(Map<String, String> headers,
                                               AchAccount achAccount,
                                               AchTransferType transferType,
                                               Account account,
                                               String amount) {
        return new AchTransferParameters(headers, null, transferType, null, amount, null, achAccount, account);
    }

    public AchTransferParameters withAchAccountId(String achAccountId) {
        return new AchTransferParameters(getHeaders(), achAccountId, mTransferType, mAccountId, mAmount, mMemberId, mAchAccount, mAccount);
    }

    public AchTransferParameters withAccountId(String accountId) {
        return new AchTransferParameters(getHeaders(), mAchAccountId, mTransferType, accountId, mAmount, mMemberId, mAchAccount, mAccount);
    }

    public AchTransferParameters withMemberId(Long memberId) {
        return new AchTransferParameters(getHeaders(), mAchAccountId, mTransferType, mAccountId, mAmount, memberId, mAchAccount, mAccount);
    }

    private AchTransferParameters(Map<String, String> headers,
                                  String achAccountId,
                                  AchTransferType transferType,
                                  String accountId,
                                  String amount,
                                  Long memberId,
                                  AchAccount achAccount,
                                  Account account) {
        super(headers);

        mAchAccountId = achAccountId;
        mTransferType = transferType;
        mAccountId = accountId;
        mAmount = amount;
        mMemberId = memberId;
        mAchAccount = achAccount;
        mAccount = account;
    }


    public String getAchAccountId() {
        return mAchAccountId;
    }

    public AchTransferType getTransferType() {
        return mTransferType;
    }

    public String getAccountId() {
        return mAccountId;
    }

    public String getAmount() {
        return mAmount;
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public AchAccount getAchAccount() {
        return mAchAccount;
    }

    public Account getAccount() {
        return mAccount;
    }
}
