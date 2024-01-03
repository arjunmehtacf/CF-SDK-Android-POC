package com.example.cf_sdk.changebankapi.model.transaction;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Response class for recent transaction parameter.
 */
public class RecentTransactionParameter extends Parameters {
    public String getmCardNumber() {
        return mCardNumber;
    }

    public void setmCardNumber(String mCardNumber) {
        this.mCardNumber = mCardNumber;
    }

    public String getmDateFrom() {
        return mDateFrom;
    }

    public void setmDateFrom(String mDateFrom) {
        this.mDateFrom = mDateFrom;
    }

    public String getmDateTo() {
        return mDateTo;
    }

    public void setmDateTo(String mDateTo) {
        this.mDateTo = mDateTo;
    }

    @SerializedName("cardNumber")
    private String mCardNumber = "";

    @SerializedName("dateFrom")
    private String mDateFrom = "";

    @SerializedName("dateTo")
    private String mDateTo = "";

    private RecentTransactionParameter(Map<String, String> headers, String cardNumber, String dateFrom, String dateTo) {
        super(headers);
        mCardNumber = cardNumber;
        mDateFrom = dateFrom;
        mDateTo = dateTo;
    }

    public static RecentTransactionParameter create(String mCardNumber, String mDateFrom, String mDateTo) {
        Map<String,String> mHeader = new HashMap<>();
        return new RecentTransactionParameter(mHeader, mCardNumber, mDateFrom, mDateTo);
    }

    public RecentTransactionParameter(Map<String, String> headers){
        super(headers);
    }

}