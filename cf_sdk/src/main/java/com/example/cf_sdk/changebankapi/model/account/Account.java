package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


import com.google.gson.annotations.SerializedName;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Months;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Account model.
 */

public class Account {
    @SerializedName("account_id")
    private String mId = "";

    @SerializedName("openingDate")
    private Long mOpeningDate = 0L;

    @SerializedName("timestamp")
    private Long mTimestamp = 0L;

    @SerializedName("isCCProcessor")
    private Boolean mIsCCProcessor = false;

    @SerializedName("status")
    private AccountStatus mStatus = AccountStatus.UNKNOWN;

    @SerializedName("accountType")
    private AccountType mAccountType = AccountType.UNKNOWN;

    @SerializedName("accountNumber")
    private String mAccountNumber = "";

    @SerializedName("memberId")
    private Long mMemberId = 0L;

    @SerializedName("bankId")
    private String mBankId = "";

    @SerializedName("closingDate")
    private Long mClosingDate = Long.MAX_VALUE;

    @SerializedName("accounts")
    private List<Card> mCardsList = new ArrayList<>();

    @SerializedName("accountCurrentBalance")
    private String mBalance = "";

    @SerializedName("routingNumber")
    private String mRoutingNumber = "123";

    public String getCardnumbermasked() {
        return cardNumberMasked;
    }

    public void setCardnumbermasked(String cardNumberMasked) {
        this.cardNumberMasked = cardNumberMasked;
    }

    @SerializedName("cardNumberMasked")
    private String cardNumberMasked = "";


    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    @SerializedName("cardToken")
    private String cardToken = "";

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    @SerializedName("virtual")
    private boolean virtual = false;

    public boolean isallowSubcards() {
        return allowSubcards;
    }

    public void setallowSubcards(boolean subcard) {
        this.allowSubcards = subcard;
    }

    @SerializedName("allowSubcards")
    private boolean allowSubcards = false;

    @SerializedName("subcard")
    private boolean subcard = false;

    public boolean isSubcard() {
        return subcard;
    }

    public void setSubcard(boolean subcard) {
        this.subcard = subcard;
    }

    public boolean isallowTransfer() {
        return allowTransfer;
    }

    public void setallowTransfer(boolean allowTransfer) {
        this.allowTransfer = allowTransfer;
    }

    @SerializedName("allowTransfer")
    private boolean allowTransfer = false;

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @SerializedName("expiryDate")
    private String expiryDate = "";

    public String getValidFromDate() {
        return validFromDate + "01";
    }

    public void setValidFromDate(String validFromDate) {
        this.validFromDate = validFromDate;
    }

    @SerializedName("validFromDate")
    private String validFromDate = "";

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @SerializedName("cardStatus")
    private String cardStatus = "";

    @SerializedName("cardType")
    private String cardType="";

    @SerializedName("accountCurrencyCode")
    private String accountCurrencyCode="";

    @SerializedName("accountAvailableWithdrawal")
    private String accountCurrentBalance="";

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    @SerializedName("programID")
    private String programID="";

    public static Account create(String id, String accountNumber, String balance) {
        return new Account(id, accountNumber, balance);
    }

    private Account(Long openingDate,
                    Long timestamp,
                    Boolean isCCProcessor,
                    String id,
                    AccountStatus status,
                    AccountType accountType,
                    String accountNumber,
                    Long memberId,
                    String bankId,
                    Long closingDate,
                    String balance,
                    List<Card> cardsList,
                    String routingNumber, String currencyCode, String currencyBalance, boolean mVirtual, boolean mAllowTransfer, boolean isSubcard, String productCode, boolean mSubcard) {
        mOpeningDate = openingDate;
        mTimestamp = timestamp;
        mIsCCProcessor = isCCProcessor;
        mId = id;
        mStatus = status;
        mAccountType = accountType;
        mAccountNumber = accountNumber;
        mMemberId = memberId;
        mBankId = bankId;
        mClosingDate = closingDate;
        mBalance = balance;
        mCardsList = cardsList;
        mRoutingNumber = routingNumber;
        accountCurrencyCode = currencyCode;
        accountCurrentBalance = currencyBalance;
        virtual = mVirtual;
        allowTransfer = mAllowTransfer;
        allowSubcards = isSubcard;
        programID = productCode;
        subcard = mSubcard;
    }

    private Account(String id,
                    String accountNumber,
                    String balance) {
        mId = id;
        mAccountNumber = accountNumber;
        mBalance = balance;
    }


    public Account withBalance(String balance) {
        return new Account(
                mOpeningDate,
                mTimestamp,
                mIsCCProcessor,
                mId,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mBankId,
                mClosingDate,
                balance,
                mCardsList,
                mRoutingNumber, accountCurrencyCode, accountCurrentBalance, virtual, allowTransfer, allowSubcards, programID, subcard);
    }

    public Account withAccountType(@NonNull AccountType accountType) {
        return new Account(
                mOpeningDate,
                mTimestamp,
                mIsCCProcessor,
                mId,
                mStatus,
                accountType,
                mAccountNumber,
                mMemberId,
                mBankId,
                mClosingDate,
                mBalance,
                mCardsList,
                mRoutingNumber, accountCurrencyCode, accountCurrentBalance, virtual, allowTransfer, allowSubcards, programID, subcard);
    }

    public Account withOpeningDate(long openingDate) {
        return new Account(
                openingDate,
                mTimestamp,
                mIsCCProcessor,
                mId,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mBankId,
                mClosingDate,
                mBalance,
                mCardsList,
                mRoutingNumber, accountCurrencyCode, accountCurrentBalance, virtual, allowTransfer, allowSubcards, programID, subcard);
    }

    public Account withIsCCProcessor(boolean isCCProcessor) {
        return new Account(
                mOpeningDate,
                mTimestamp,
                isCCProcessor,
                mId,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mBankId,
                mClosingDate,
                mBalance,
                mCardsList,
                mRoutingNumber, accountCurrencyCode, accountCurrentBalance, virtual, allowTransfer, allowSubcards, programID, subcard);
    }

    public Account withAccountStatus(AccountStatus accountStatus) {
        return new Account(
                mOpeningDate,
                mTimestamp,
                mIsCCProcessor,
                mId,
                accountStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mBankId,
                mClosingDate,
                mBalance,
                mCardsList,
                mRoutingNumber, accountCurrencyCode, accountCurrentBalance, virtual, allowTransfer, allowSubcards, programID, subcard);
    }

    public String getBalance() {
        return mBalance;
    }

    public void setBalance(String balance) {
        mBalance = balance;
    }

    public String getBalanceString() {
        if (mBalance != null) {
            return mBalance;
        }
        return "";
    }

    public Long getOpeningDate() {
        return mOpeningDate;
    }

    public Long getValiedOpeningDate() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd", Locale.US);
        try {
            Date date = inputFormat.parse(getValidFromDate());
            mOpeningDate = date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return mOpeningDate;
    }

    public String getOpeningDateDisplayValue() {
        getValiedOpeningDate();
        if (mOpeningDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
            return simpleDateFormat.format(mOpeningDate);
        }
        return "";
    }

    public Long getTimestamp() {
        return mTimestamp;
    }

    public Boolean getIsCCProcessor() {
        return mIsCCProcessor;
    }

    public String getId() {
        return mId;
    }

  /*  public String getAccountRefId() {
        return mAccountRefId;
    }*/

    public String getRoutingNumber() {
        return mRoutingNumber;
    }

    public AccountStatus getStatus() {
        return mStatus;
    }

    public AccountType getAccountType() {
        return mAccountType;
    }

    public String getAccountCurrencyCode() {
        return accountCurrencyCode;
    }

    public void setAccountCurrencyCode(String cCode) {
        accountCurrencyCode = cCode;
    }

    public String getAccountCurrentBalance() {
        return accountCurrentBalance;
    }

    public List<Card> getCardsList() {
        return mCardsList == null ? new ArrayList<Card>() : mCardsList;
    }

    public String getFullAccountNumber() {
        return mAccountNumber;
    }

    public String getAccountNumber() {
        return mAccountNumber;
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

    public Long getMemberId() {
        return mMemberId;
    }

    public String getBankId() {
        return mBankId;
    }

    public Long getClosingDate() {
        return mClosingDate;
    }

    public List<DateTime> getAvailableMonths() {
        DateTime accountCreated = new DateTime(
                getValiedOpeningDate(),
                DateTimeZone.forID("America/Los_Angeles"));
        return getAvailableMonths(accountCreated);
    }

    public boolean hasCards() {
        return mCardsList != null && !mCardsList.isEmpty();
    }

    private List<DateTime> getAvailableMonths(DateTime createdAt) {
        ArrayList<DateTime> months = new ArrayList<>();
        DateTime startDateTime = createdAt
                .dayOfMonth().withMinimumValue()
                .millisOfDay().withMaximumValue();

     /*   for (DateTime now = DateTime.now();
             now.isAfter(startDateTime);
             now = now.minusMonths(1)) {
            months.add(now);
        }*/

        int monthsBetween = Months.monthsBetween(startDateTime, DateTime.now().dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue()).getMonths();

        for (int i = 0; i <= monthsBetween; i++) {
            months.add(startDateTime.plusMonths(i));
        }


        if (months.size() == 0) {
            months.add(startDateTime);
        }

        Collections.sort(months);
        Collections.reverse(months);
        return months;
    }


    public Account withCardsList(List<Card> cardList) {
        return new Account(
                mOpeningDate,
                mTimestamp,
                mIsCCProcessor,
                mId,
                mStatus,
                mAccountType,
                mAccountNumber,
                mMemberId,
                mBankId,
                mClosingDate,
                mBalance,
                cardList,
                mRoutingNumber, accountCurrencyCode, accountCurrentBalance, virtual, allowTransfer, allowSubcards, programID, subcard);
    }

    @Override
    public String toString() {
        return "\n\naccounts {" +
                ", \ncardNumberMasked='" + cardNumberMasked + '\'' +
                ", \ncardToken='" + cardToken + '\'' +
                ", \nvirtual=" + virtual +
                ", \nallowSubcards=" + allowSubcards +
                ", \nsubcard=" + subcard +
                ", \nallowTransfer=" + allowTransfer +
                ", \nexpiryDate='" + expiryDate + '\'' +
                ", \nvalidFromDate='" + validFromDate + '\'' +
                ", \ncardStatus='" + cardStatus + '\'' +
                ", \ncardType='" + cardType + '\'' +
                ", \nprogramID='" + programID + '\'' +
                '}';
    }
}

