package com.example.cf_sdk.logic.changebanksdk.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * Contains fields that describe an Ach Account
 */

public class AchAccount implements Parcelable {

    @SerializedName("uuid")
    private String mId;

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
    private String bankName;

    public static AchAccount create(String id) {
        return new AchAccount(id);
    }

    private AchAccount(@Nonnull String id) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mTimestamp);
        dest.writeString(this.mRoutingNumber);
        dest.writeString(this.mAccountName);
        dest.writeSerializable(this.mBalance);
        dest.writeString(this.mId);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
        dest.writeInt(this.mAccountType == null ? -1 : this.mAccountType.ordinal());
        dest.writeString(this.mAccountNumber);
        dest.writeValue(this.mMemberId);
        dest.writeString(this.mMxId);
        dest.writeString(this.mBankId);
        dest.writeParcelable(this.mBank, flags);
    }

    protected AchAccount(Parcel in) {
        this.mTimestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.mRoutingNumber = in.readString();
        this.mAccountName = in.readString();
        this.mBalance = (Money) in.readSerializable();
        this.mId = in.readString();
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : AchAccountStatus.values()[tmpMStatus];
        int tmpMAccountType = in.readInt();
        this.mAccountType = tmpMAccountType == -1 ? null : AccountType.values()[tmpMAccountType];
        this.mAccountNumber = in.readString();
        this.mMemberId = (Long) in.readValue(Long.class.getClassLoader());
        this.mMxId = in.readString();
        this.mBankId = in.readString();
        this.mBank = in.readParcelable(Bank.class.getClassLoader());
    }

    public static final Creator<AchAccount> CREATOR = new Creator<AchAccount>() {
        @Override
        public AchAccount createFromParcel(Parcel source) {
            return new AchAccount(source);
        }

        @Override
        public AchAccount[] newArray(int size) {
            return new AchAccount[size];
        }
    };
}

