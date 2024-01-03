package com.example.cf_sdk.changebankapi.parameter.account;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains request parameter for card to card transfer
 */
public class CardToCardTransferParameters extends Parameters {
    @SerializedName("account")
    private String account;

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    @SerializedName("toAccount")
    private String toAccount;
    @SerializedName("toAdminIdId")
    private String mAdminNumber;

    @SerializedName("amount")
    private String amount;

    @SerializedName("last4Phone")
    private String mLast4Phone;

    @SerializedName("name")
    private String name;

    @SerializedName("currencyCode")
    private String currencyCode;

    @SerializedName("date")
    private String date;

    @SerializedName("transactionId")
    private String transactionId;

    @SerializedName("description")
    private String description;

    @SerializedName("isRepeat")
    private boolean isRepeat = false;


    public static CardToCardTransferParameters create(String mAmount,
                                                      String mCurrencyCode,
                                                      String mDate,
                                                      String mTransactionId,
                                                      String mDescription,
                                                      String fromAccount,
                                                      String mToAccount) {

        Map<String,String> mHeader = new HashMap<>();
        return new CardToCardTransferParameters(mHeader, mAmount, mCurrencyCode, mDate, mTransactionId,mDescription,fromAccount,mToAccount);
    }

    public static CardToCardTransferParameters createWithSearch(String mAdminNumber,String mReceipientName,String mLast4Phone){
        Map<String,String> mHeader = new HashMap<>();
        return new CardToCardTransferParameters(mHeader, mAdminNumber, mLast4Phone,mReceipientName);
    }

    private CardToCardTransferParameters(Map<String, String> headers,String mAdminNumber,String mLast4Phone,String mReceipientName)
    {
        super(headers);
        this.mAdminNumber = mAdminNumber;
        this.mLast4Phone = mLast4Phone;
        this.name = mReceipientName;
    }
    private CardToCardTransferParameters(Map<String, String> headers,
                                         String mAmount,
                                         String mCurrencyCode,
                                         String mDate,
                                         String mTransactionId,
                                         String mDescription,
                                         String fromAccount,
                                         String mToAccount) {
        super(headers);
        this.amount = mAmount;
        currencyCode = mCurrencyCode;
        date = mDate;
        transactionId = mTransactionId;
        description = mDescription;
        account = fromAccount;
        toAccount = mToAccount;
        isRepeat = false;
    }


    public String getAccountNumber() {
        return account;
    }

    public String getmAdminNumber() {
        return mAdminNumber;
    }

    public String getAmount() {
        return amount;
    }

    public String getLast4Phone() {
        return mLast4Phone;
    }
    public String getName() {
        return name;
    }

}
