package com.example.cf_sdk.logic.changebanksdk.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Preconditions;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.Random;

import javax.annotation.Nonnull;

/**
 *
 * Ach Transfer object that contains fields describing a transfer event.
 */

public class AchTransfer implements Parcelable {
    @SerializedName("account_id")
    private String mId;

    @SerializedName("timestamp")
    private Long mTimestamp;

    @SerializedName("achAccountId")
    private String mAchAccountId;

    @SerializedName("typeTrans")
    private AchTransferType mTransferType;

    @SerializedName("accountId")
    private String mAccountId;

    @SerializedName("reason")
    private String mReason;

    @SerializedName("amount")
    private String mAmount;

    @SerializedName("completionTime")
    private Long mCompletionTime;

    @SerializedName("traceNumber")
    private Long mTraceNumber;

    @SerializedName("status")
    private AchTransferStatus mStatus;

    @SerializedName("memberId")
    private Long mMemberId;

    @SerializedName("memo")
    private String mMemo;

    @SerializedName("creationTime")
    private Long mCreationTime;

    @SerializedName("transactionDate")
    private String transactionDate;


    @SerializedName("bankAccountNumber")
    private String mAchAccountNumber;

    public void setmAccountNumber(String mAccountNumber) {
        this.mAccountNumber = mAccountNumber;
    }

    private String mAccountNumber;



    public static AchTransfer create(String id,
                                     Long memberId,
                                     AchTransferType achTransferType,
                                     String amount,
                                     String achAccountId,
                                     String accountId,
                                     String achAccountNumber,
                                     String accountNumber) {
        long now = DateTime.now().getMillis();
        return new AchTransfer(
                id,
                memberId,
                achTransferType,
                amount,
                achAccountId,
                accountId,
                achAccountNumber,
                accountNumber,
                AchTransferStatus.CREATED,
                now,
                "",
                Long.MAX_VALUE,
                new Random().nextLong(),
                "",
                now);
    }

    public static AchTransfer createPending(String id,
                                     Long memberId,
                                     AchTransferType achTransferType,
                                     String amount,
                                     String achAccountId,
                                     String accountId,
                                     String achAccountNumber,
                                     String accountNumber) {
        long now = DateTime.now().getMillis();
        return new AchTransfer(
                id,
                memberId,
                achTransferType,
                amount,
                achAccountId,
                accountId,
                achAccountNumber,
                accountNumber,
                AchTransferStatus.PENDING,
                now,
                "",
                Long.MAX_VALUE,
                new Random().nextLong(),
                "",
                now);
    }

    private AchTransfer(
            String id,
            Long memberId,
            AchTransferType transferType,
            String amount,
            String achAccountId,
            String accountId,
            String achAccountNumber,
            String accountNumber,
            AchTransferStatus status,
            Long timestamp,
            String reason,
            Long completionTime,
            Long traceNumber,
            String memo,
            Long creationTime) {
        mId = id;
        mMemberId = memberId;
        mTransferType = transferType;
        mAmount = amount;
        mAchAccountId = achAccountId;
        mAccountId = accountId;
        mAchAccountNumber = achAccountNumber;
        mAccountNumber = accountNumber;
        mStatus = status;
        mTimestamp = timestamp;
        mReason = reason;
        mCompletionTime = completionTime;
        mTraceNumber = traceNumber;
        mMemo = memo;
        mCreationTime = creationTime;
    }


    public String getAchAccountNumber() {
        if (mAchAccountNumber != null && mAchAccountNumber.length() > 4) {
            return mAchAccountNumber.substring(mAchAccountNumber.length() - 4);
        }
        return mAchAccountNumber;
    }

    public AchTransfer withAchAccountNumber(String achAccountNumber) {
        return new AchTransfer(
                mId,
                mMemberId,
                mTransferType,
                mAmount,
                mAchAccountId,
                mAccountId,
                achAccountNumber,
                mAccountNumber,
                mStatus,
                mTimestamp,
                mReason,
                mCompletionTime,
                mTraceNumber,
                mMemo,
                mCreationTime);
    }

    public AchTransfer withUpdatedDataAfterTransferCanceled(
            AchTransferStatus status,
            Long completionTime,
            Long timeStamp) {
        return new AchTransfer(
                mId,
                mMemberId,
                mTransferType,
                mAmount,
                mAchAccountId,
                mAccountId,
                mAchAccountNumber,
                mAccountNumber,
                status,
                timeStamp,
                mReason,
                completionTime,
                mTraceNumber,
                mMemo,
                mCreationTime);
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

    public String getMaskedAchAccountNumber(){
        String lastFourAcct = getAchAccountNumber();
        if(lastFourAcct != null){
            return "**** " + lastFourAcct;
        }
        return "";
    }

    public AchTransfer withAccountNumber(String accountNumber) {
        return new AchTransfer(
                mId,
                mMemberId,
                mTransferType,
                mAmount,
                mAchAccountId,
                mAccountId,
                mAchAccountNumber,
                accountNumber,
                mStatus,
                mTimestamp,
                mReason,
                mCompletionTime,
                mTraceNumber,
                mMemo,
                mCreationTime);
    }

    public Long getTimestamp() {
        return mTimestamp;
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

    public String getReason() {
        return mReason;
    }

    public String getAmount() {
        return mAmount;
    }

    public Long getCompletionTime() {
        return mCompletionTime;
    }

    public String getId() {
        return mId;
    }

    public Long getTraceNumber() {
        return mTraceNumber;
    }

    public AchTransferStatus getStatus() {
        return mStatus;
    }

    public AchTransfer withStatus(@Nonnull AchTransferStatus achTransferStatus) {
        Preconditions.checkNotNull(achTransferStatus);
        return new AchTransfer(
                mId,
                mMemberId,
                mTransferType,
                mAmount,
                mAchAccountId,
                mAccountId,
                mAchAccountNumber,
                mAccountNumber,
                achTransferStatus,
                mTimestamp,
                mReason,
                mCompletionTime,
                mTraceNumber,
                mMemo,
                mCreationTime);
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public String getMemo() {
        return mMemo;
    }

    public Long getCreationTime() {
        return mCreationTime;
    }

    public String getTransactionDate() {
        return transactionDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mTimestamp);
        dest.writeString(this.mAchAccountId);
        dest.writeInt(this.mTransferType == null ? -1 : this.mTransferType.ordinal());
        dest.writeString(this.mAccountId);
        dest.writeString(this.mReason);
        dest.writeSerializable(this.mAmount);
        dest.writeValue(this.mCompletionTime);
        dest.writeString(this.mId);
        dest.writeValue(this.mTraceNumber);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
        dest.writeValue(this.mMemberId);
        dest.writeString(this.mMemo);
        dest.writeValue(this.mCreationTime);
        dest.writeString(this.mAchAccountNumber);
        dest.writeString(this.mAccountNumber);
    }

    protected AchTransfer(Parcel in) {
        this.mTimestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.mAchAccountId = in.readString();
        int tmpMTransferType = in.readInt();
        this.mTransferType = tmpMTransferType == -1 ? null : AchTransferType.values()[tmpMTransferType];
        this.mAccountId = in.readString();
        this.mReason = in.readString();
        this.mAmount = in.readString();
        this.mCompletionTime = (Long) in.readValue(Long.class.getClassLoader());
        this.mId = in.readString();
        this.mTraceNumber = (Long) in.readValue(Long.class.getClassLoader());
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : AchTransferStatus.values()[tmpMStatus];
        this.mMemberId = (Long) in.readValue(Long.class.getClassLoader());
        this.mMemo = in.readString();
        this.mCreationTime = (Long) in.readValue(Long.class.getClassLoader());
        this.mAchAccountNumber = in.readString();
        this.mAccountNumber = in.readString();
    }

    public static final Creator<AchTransfer> CREATOR = new Creator<AchTransfer>() {
        @Override
        public AchTransfer createFromParcel(Parcel source) {
            return new AchTransfer(source);
        }

        @Override
        public AchTransfer[] newArray(int size) {
            return new AchTransfer[size];
        }
    };
}
