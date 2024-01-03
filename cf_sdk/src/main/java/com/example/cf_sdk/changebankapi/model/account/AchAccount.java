package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sdk_no_dagger.changebankapi.model.account.AccountType;
import com.example.sdk_no_dagger.changebankapi.model.account.AchAccountStatus;
import com.google.gson.annotations.SerializedName;



/**
 *
 * Contains fields that describe an Ach Account
 */

public class AchAccount {

    @SerializedName("uuid")
    private String mId="";

    @SerializedName("timestamp")
    private Long mTimestamp = 0L;

    @SerializedName("routing")
    private String mRoutingNumber = "";

    @SerializedName("bankAccountName")
    private String mAccountName = "";

    @SerializedName("balance")
    private Money mBalance = new Money(0);

    @SerializedName("status")
    private AchAccountStatus mStatus = AchAccountStatus.NONE;

    public void setmStatus(AchAccountStatus mStatus) {
        this.mStatus = mStatus;
    }

    @SerializedName("style")
    private AccountType mAccountType = AccountType.UNKNOWN;

    @SerializedName("bankAccountNumber")
    private String mAccountNumber = "";

    @SerializedName("memberId")
    private Long mMemberId = 0L;

    @SerializedName("mxId")
    private String mMxId = "";

    @SerializedName("bankId")
    private String mBankId = "";

    @SerializedName("referenceId")
    private String referenceId = "";

    @Nullable
    private Bank mBank;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @SerializedName("bankName")
    private String bankName="";

    public static AchAccount create(String id) {
        return new AchAccount(id);
    }

    private AchAccount(@NonNull String id) {
        mId = id;
    }

    public AchAccount(String id,
                      Long timestamp,
                      String routingNumber,
                      String accountName,
                      Money balance,
                      AchAccountStatus status,
                      AccountType accountType,
                      String accountNumber,
                      Long memberId,
                      String mxId,
                      String bankId,
                      @Nullable Bank bank) {
        mId = id;
        mTimestamp = timestamp;
        mRoutingNumber = routingNumber;
        mAccountName = accountName;
        mBalance = balance;
        mStatus = status;
        mAccountType = accountType;
        mAccountNumber = accountNumber;
        mMemberId = memberId;
        mMxId = mxId;
        mBankId = bankId;
        mBank = bank;

    }

    public AchAccount withBank(Bank bank) {
        return new AchAccount(
                mId,
                mTimestamp,
                mRoutingNumber,
                mAccountName,
                mBalance,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mMxId,
                mBankId,
                bank);
    }

    public AchAccount withAchAccount(Bank bank) {
        return new AchAccount(
                mId,
                mTimestamp,
                mRoutingNumber,
                mAccountName,
                mBalance,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mMxId,
                mBankId,
                bank);
    }

    public AchAccount withBankId(String bankId) {
        return new AchAccount(
                mId,
                mTimestamp,
                mRoutingNumber,
                mAccountName,
                mBalance,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mMxId,
                bankId,
                mBank);
    }

    public AchAccount withStatus(AchAccountStatus status) {
        return new AchAccount(
                mId,
                mTimestamp,
                mRoutingNumber,
                mAccountName,
                mBalance,
                status,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mMxId,
                mBankId,
                mBank);
    }

    public AchAccount withAccountNumber(String accountNumber) {
        return new AchAccount(
                mId,
                mTimestamp,
                mRoutingNumber,
                mAccountName,
                mBalance,
                mStatus,
                mAccountType,
                accountNumber,
                mMemberId,
                mMxId,
                mBankId,
                mBank);
    }

    public AchAccount withBalance(Money balance) {
        return new AchAccount(
                mId,
                mTimestamp,
                mRoutingNumber,
                mAccountName,
                balance,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mMxId,
                mBankId,
                mBank);
    }

    public AchAccount withAccountType(AccountType accountType) {
        return new AchAccount(
                mId,
                mTimestamp,
                mRoutingNumber,
                mAccountName,
                mBalance,
                mStatus,
                accountType,
                mAccountNumber,
                mMemberId,
                mMxId,
                mBankId,
                mBank);
    }

    public Bank getBank() {
        return mBank;
    }

    public Long getTimestamp() {
        return mTimestamp;
    }

    public String getRoutingNumber() {
        return mRoutingNumber;
    }

    public String getAccountName() {
        return mAccountName;
    }

    public Money getBalance() {
        return mBalance;
    }

    public String getId() {
        return mId;
    }

    public String getReferenceId(){ return  referenceId;}


    public AchAccountStatus getStatus() {
        return mStatus;
    }

    public AccountType getAccountType() {
        return mAccountType;
    }

    public String getAccountNumber() {
        if (mAccountNumber != null && mAccountNumber.length() > 4) {
            return mAccountNumber.substring(mAccountNumber.length() - 4);
        }
        return mAccountNumber;
    }

    public String getMaskedAccountNumber(){
        String lastFourAcct = getAccountNumber();
        if(lastFourAcct != null){
            return "**** " + lastFourAcct;
        }
        return "";
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public String getMxId() {
        return mMxId;
    }

    public String getBankId() {
        return mBankId;
    }

    public boolean hasTransitionalStatus() {
        return getStatus().isTransitional();
    }
}

