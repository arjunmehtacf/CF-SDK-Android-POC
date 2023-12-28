package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Upload document request parameter model class.
 */
public class UploadDocumentsParameters extends Parameters {

    @SerializedName("image")
    private String mFronImage;

    @SerializedName("backImage")
    private String mBackImage;

    @SerializedName("faceImage")
    private String mSelfieImage;

    @SerializedName("countryCode")
    private String mContryCode;

    @SerializedName("scanDocumentType")
    private String mScanType;

    @SerializedName("queryId")
    private String mQueryId;

    @SerializedName("idScanRefId")
    private String mIdScanRefId;

    private long mMemberId;


    private UploadDocumentsParameters(Map<String, String> headers,
                                      String frontImage,
                                      String backImage,
                                      String selfieImage,
                                      String countryCode,
                                      String scanType,
                                      String queryId) {
        super(headers);

        mFronImage = frontImage;
        mBackImage = backImage;
        mSelfieImage = selfieImage;
        mContryCode = countryCode;
        mScanType = scanType;
        mQueryId = queryId;

    }


    public String getmFronImage() {
        return mFronImage;
    }

    public String getmBackImage() {
        return mBackImage;
    }

    public String getmSelfieImage() {
        return mSelfieImage;
    }

    public String getContryCodes() {
        return mContryCode;
    }

    public String getmScanType() { return mScanType; }

    public String getmQuiryId() { return mQueryId; }
    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }
    public void ScanRefId(String scanRefId){
        mIdScanRefId = scanRefId;
    }

    public long getMemberId() {
        return mMemberId;
    }
    public static UploadDocumentsParameters create(
            HashMap<String, String> loggedInHeader,
            String fronImage,
            String mBackImage,
            String mSelfieImage,
            String scanType,
            String mQueryId ) {
        return new UploadDocumentsParameters(loggedInHeader, fronImage, mBackImage, mSelfieImage, "USA",scanType,mQueryId);
    }
}
