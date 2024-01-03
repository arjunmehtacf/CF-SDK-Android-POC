package com.example.cf_sdk.changebankapi.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Changebank available features for the {@link Session}.
 */

public class Features {
    @SerializedName("LINK_ACCOUNT")
    private boolean mLinkAccount=false;

    @SerializedName("HISTORY")
    private boolean mHistory=false;

    @SerializedName("BANK_LOAD_MONEY")
    private boolean mBankLoadMoney=false;

    @SerializedName("BANK_UNLOAD_MONEY")
    private boolean mBankUnloadMoney=false;

    @SerializedName("SEND_MONEY")
    private boolean mSendMoney=false;

    @SerializedName("REFER_A_FRIEND")
    private boolean mReferAFriend=false;

    @SerializedName("UPDATE_PROFILE")
    private boolean mUpdateProfile=false;

    @SerializedName("ACCOUNT_TRANSFER_CONSENT")
    private boolean mAccountTransferConsent=false;

    @SerializedName("BUDGETING")
    private boolean mBudgeting=false;

    @SerializedName("CHECK_DEPOSIT")
    private boolean mCheckDeposit=false;

    @SerializedName("DEBUG_LOGGING_ENABLED")
    private boolean mDebugLogging=false;

    @SerializedName("CASHBACK_ELIGIBLE")
    private boolean mCashBack=false;

    public static Features createWithAllFeatures() {
        return new Features(true, true, false, true, true, true, true, true, true, false, true, true);
    }

    public static Features createWithoutAllFeatures() {
        return new Features(false, false, false, false, false, false, false, false, false, false, false, false);
    }

    private Features(boolean linkAccount,
                     boolean history,
                     boolean bankLoadMoney,
                     boolean bankUnloadMoney,
                     boolean sendMoney,
                     boolean referAFriend,
                     boolean updateProfile,
                     boolean accountTransferConsent,
                     boolean budgeting,
                     boolean checkDeposit,
                     boolean debugLogging,
                     boolean cashBack) {
        mLinkAccount = linkAccount;
        mHistory = history;
        mBankLoadMoney = bankLoadMoney;
        mBankUnloadMoney = bankUnloadMoney;
        mSendMoney = sendMoney;
        mReferAFriend = referAFriend;
        mUpdateProfile = updateProfile;
        mAccountTransferConsent = accountTransferConsent;
        mBudgeting = budgeting;
        mCheckDeposit = checkDeposit;
        mDebugLogging = debugLogging;
        mCashBack = cashBack;
    }

    @Override
    public String toString() {
        return "Features{" +
                "mLinkAccount=" + mLinkAccount +
                ", mHistory=" + mHistory +
                ", mBankLoadMoney=" + mBankLoadMoney +
                ", mBankUnloadMoney=" + mBankUnloadMoney +
                ", mSendMoney=" + mSendMoney +
                ", mReferAFriend=" + mReferAFriend +
                ", mUpdateProfile=" + mUpdateProfile +
                ", mAccountTransferConsent=" + mAccountTransferConsent +
                ", mBudgeting=" + mBudgeting +
                ", mCheckDeposit=" + mCheckDeposit +
                ", mDebugLogging=" + mDebugLogging +
                ", mCashBack=" + mCashBack +
                '}';
    }

    public boolean hasLinkAccount() {
        return mLinkAccount;
    }

    public boolean hasHistory() {
        return mHistory;
    }

    public boolean hasBankLoadMoney() {
        return mBankLoadMoney;
    }

    public boolean hasBankUnloadMoney() {
        return mBankUnloadMoney;
    }

    public boolean hasSendMoney() {
        return mSendMoney;
    }

    public boolean hasReferAFriend() {
        return mReferAFriend;
    }

    public boolean hasUpdateProfile() {
        return mUpdateProfile;
    }

    public boolean hasAccountTransferConsent() {
        return mAccountTransferConsent;
    }

    public boolean hasBudgeting() {
        return mBudgeting;
    }

    public boolean hasCheckDeposit() {
        return mCheckDeposit;
    }

    public boolean hasDebugLogging() {
        return mDebugLogging;
    }

    public boolean hasCashBack() {
        return mCashBack;
    }

}