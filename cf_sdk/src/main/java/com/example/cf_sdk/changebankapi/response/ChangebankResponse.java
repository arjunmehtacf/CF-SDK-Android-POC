package com.example.cf_sdk.changebankapi.response;


import com.example.cf_sdk.changebankapi.model.account.AchAccount;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Handles error return from Api.
 */

public class ChangebankResponse extends Throwable {
    @SerializedName("messages")
    private List<ResponseMessage> mMessageList = new ArrayList<>();

    @SerializedName("retry")
    private boolean mRetry = false;

    @SerializedName("idScanRequired")
    private boolean mIdScanRequired = false;

    @SerializedName("resubmitOOW")
    private boolean mResubmitOOW = false;

    @SerializedName("idScan")
    private boolean mIdScan = false;

    @SerializedName("idScanRef")
    private boolean mIdScanRef = false;

    @SerializedName("referenceNumber")
    private String mReferenceNumber = "";

    @SerializedName("errorMessage")
    private String mMessage = "";


    @SerializedName("httpCode")
    private int httpCode;

    public AchAccount getAchAccount() {
        return achAccount;
    }

    public void setAchAccount(AchAccount achAccount) {
        this.achAccount = achAccount;
    }

    @SerializedName("achAccount")
    private AchAccount achAccount;

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }


    public void setMessage(String message) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setDefaultMessage(message);
        mMessageList.add(0, responseMessage);
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getReferenceNumber(){
        return mReferenceNumber;
    }

    public String getMessage() {
        if (mMessageList != null && !mMessageList.isEmpty()) {
            return mMessageList.get(0).getDefaultMessage();
        }
        return "";
    }

    public String getKey() {
        if (mMessageList != null && !mMessageList.isEmpty()) {
            return mMessageList.get(0).getMessageKey();
        }
        return "";
    }

    public boolean isIdScanRef() {
        return mIdScanRef;
    }

    public boolean isIdScan() {
        return mIdScan;
    }

    public boolean isRetry() {
        return mRetry;
    }

    public boolean isResubmitOOW() {
        return mResubmitOOW;
    }

    public boolean isIdScanRequired(){
        return mIdScanRequired;
    }

    public String getmMessage(){return mMessage;}


}
