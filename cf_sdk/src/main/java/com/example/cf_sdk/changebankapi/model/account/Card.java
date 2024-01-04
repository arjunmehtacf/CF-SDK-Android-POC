package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Card object describes the Debit card information.
 */

public class Card {

    @SerializedName("card_id")
    private String mId = "";

    @SerializedName("memberId")
    private Long mMemberId = 0L;

    @SerializedName("accountNumber")
    private String mAccountNumber = "";

    @SerializedName("last4Pan")
    private String mLast4Pan = "";

    @SerializedName("expirationDate")
    private String mExpirationDate = "";

    @SerializedName("adminNumber")
    private String mAdminNumber = "";

    @SerializedName("status")
    private CardStatus mStatus;

    @SerializedName("carrier")
    private String mCarrier = "";

    @SerializedName("timestamp")
    private Long mTimestamp = 0L;

    @SerializedName("isCCProcessor")
    private Boolean mIsCCProcessor = false;

    @SerializedName("accountId")
    private String mAccountId = "";

    @SerializedName("CardType")
    private String mCardType = "V";

    @SerializedName("currency")
    private String currency = "";

    @SerializedName("openDay")
    private String openingDate = "";


    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    @SerializedName("routing")
    private String routing = "";

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    @SerializedName("cardToken")
    private String cardToken = "";

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    private transient String fullCardNumber = "";

    public static Card create(String id,
                              String last4Pan,
                              String expirationDate,
                              CardStatus cardStatus) {
        return new Card(
                id,
                last4Pan,
                expirationDate,
                cardStatus);
    }

    public Card() {
    }

    private Card(String id,
                 String last4Pan,
                 String expirationDate,
                 CardStatus cardStatus) {
        mId = id;
        mLast4Pan = last4Pan;
        mExpirationDate = expirationDate;
        mStatus = cardStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return mId != null ? mId.equals(card.mId) : card.mId == null;
    }

    @Override
    public int hashCode() {
        return mId != null ? mId.hashCode() : 0;
    }


    public long getOpeningDateInLong() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(getOpeningDate());
            if (date != null) {
                return date.getTime(); // Returns the date in milliseconds since January 1, 1970.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public String getOpeningDateDisplayValue() {
        Long openingDate = getOpeningDateInLong();
        if (openingDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
            return simpleDateFormat.format(openingDate);
        }
        return "";
    }

    private Card(String id,
                 Long memberId,
                 String accountNumber,
                 String last4Pan,
                 String expirationDate,
                 String adminNumber,
                 CardStatus cardStatus,
                 String carrier,
                 Long timestamp,
                 Boolean isCCProcessor,
                 String accountId,
                 String cardType) {
        mId = id;
        mMemberId = memberId;
        mAccountNumber = accountNumber;

        mLast4Pan = last4Pan;
        mExpirationDate = expirationDate;
        mAdminNumber = adminNumber;
        mStatus = cardStatus;
        mCarrier = carrier;
        mTimestamp = timestamp;
        mIsCCProcessor = isCCProcessor;
        mAccountId = accountId;
        mCardType = cardType;
    }

    public String getId() {
        return mId;
    }


    public Long getMemberId() {
        return mMemberId;
    }

    public Card withMemberId(Long memberId) {
        return new Card(
                mId,
                memberId,
                mAccountNumber,
                mLast4Pan,
                mExpirationDate,
                mAdminNumber,
                mStatus,
                mCarrier,
                mTimestamp,
                mIsCCProcessor,
                mAccountId,
                mCardType);
    }

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public Card withAccountNumber(String accountNumber) {
        return new Card(
                mId,
                mMemberId,
                accountNumber,
                mLast4Pan,
                mExpirationDate,
                mAdminNumber,
                mStatus,
                mCarrier,
                mTimestamp,
                mIsCCProcessor,
                mAccountId,
                mCardType);
    }

    public String getLast4Pan() {
        return mLast4Pan;
    }

    public Card withLast4Pan(String last4Pan) {
        return new Card(
                mId,
                mMemberId,
                mAccountNumber,
                last4Pan,
                mExpirationDate,
                mAdminNumber,
                mStatus,
                mCarrier,
                mTimestamp,
                mIsCCProcessor,
                mAccountId,
                mCardType);
    }


    public String getExpirationDate() {
        return mExpirationDate == null ? "" : mExpirationDate;
    }

    public Card withExpirationDate(String expirationDate) {
        return new Card(
                mId,
                mMemberId,
                mAccountNumber,
                mLast4Pan,
                expirationDate,
                mAdminNumber,
                mStatus,
                mCarrier,
                mTimestamp,
                mIsCCProcessor,
                mAccountId,
                mCardType);
    }

    public String getAdminNumber() {
        return mAdminNumber;
    }

    public Card withAdminNumber(String adminNumber) {
        return new Card(
                mId,
                mMemberId,
                mAccountNumber,
                mLast4Pan,
                mExpirationDate,
                adminNumber,
                mStatus,
                mCarrier,
                mTimestamp,
                mIsCCProcessor,
                mAccountId,
                mCardType);
    }


    public CardStatus getStatus() {
        return mStatus;
    }

    public Card withStatus(CardStatus cardStatus) {
        return new Card(
                mId,
                mMemberId,
                mAccountNumber,
                mLast4Pan,
                mExpirationDate,
                mAdminNumber,
                cardStatus,
                mCarrier,
                mTimestamp,
                mIsCCProcessor,
                mAccountId,
                mCardType);
    }

    public String getCarrier() {
        return mCarrier;
    }


    public Card withCarrier(String carrier) {
        return new Card(
                mId,
                mMemberId,
                mAccountNumber,
                mLast4Pan,
                mExpirationDate,
                mAdminNumber,
                mStatus,
                carrier,
                mTimestamp,
                mIsCCProcessor,
                mAccountId,
                mCardType);
    }

    public Long getTimestamp() {
        return mTimestamp;
    }


    public Boolean getIsCCProcessor() {
        return mIsCCProcessor;
    }

    public Card withIsCCProcessor(Boolean isCCProcessor) {
        return new Card(
                mId,
                mMemberId,
                mAccountNumber,
                mLast4Pan,
                mExpirationDate,
                mAdminNumber,
                mStatus,
                mCarrier,
                mTimestamp,
                isCCProcessor,
                mAccountId,
                mCardType);
    }

    public void setFullCardNumber(String cardNumber) {
        this.fullCardNumber = cardNumber;
    }

    public String getFullCardNumber() {
        return fullCardNumber;
    }

    public String getCardType() {
        return mCardType;
    }

    public void setCardType(String cardType) {
        this.mCardType = cardType;
    }

    public String getAccountId() {
        return mAccountId;
    }

    public Card withAccountId(String accountId) {
        return new Card(
                mId,
                mMemberId,
                mAccountNumber,
                mLast4Pan,
                mExpirationDate,
                mAdminNumber,
                mStatus,
                mCarrier,
                mTimestamp,
                mIsCCProcessor,
                accountId,
                mCardType);
    }

    public String getLast4AccountNumber() {
        if (mAccountNumber != null && mAccountNumber.length() > 4) {
            return mAccountNumber.substring(mAccountNumber.length() - 4);
        }
        return mAccountNumber;
    }

    public String getMaskedAccountNumber() {
        String lastFourAcct = getLast4AccountNumber();
        if (lastFourAcct != null) {
            return "**** " + lastFourAcct;
        }
        return "";
    }
}
