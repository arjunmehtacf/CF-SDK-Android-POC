package com.example.cf_sdk.changebankapi.parameter.account;

import com.example.cf_sdk.changebankapi.model.account.AchTransferType;
import com.example.cf_sdk.changebankapi.parameter.Parameters;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Contains parameters to make a successful request to do an Ach Transfer.
 */

public class AchHistoryParameters extends Parameters {

    private transient AchTransferType mAchTransferType;
    private String mMemberId;

    @SerializedName("dateTo")
    private String dateTo;

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    @SerializedName("dateFrom")
    private String dateFrom;

    public static AchHistoryParameters create(Map<String, String> headers, AchTransferType achTransferType){
        return new AchHistoryParameters(headers, achTransferType);
    }

    private AchHistoryParameters(Map<String, String> headers, AchTransferType achTransferType) {
        super(headers);
        mAchTransferType = achTransferType;
    }

    public AchTransferType getAchTransferType() {
        return mAchTransferType;
    }

    public String getMemberId() {
        return mMemberId;
    }

    public void setMemberId(String memberId) {
        mMemberId = memberId;
    }
}
