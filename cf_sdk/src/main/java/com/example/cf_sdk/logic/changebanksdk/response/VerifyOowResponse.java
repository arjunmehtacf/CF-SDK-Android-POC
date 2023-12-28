package com.example.cf_sdk.logic.changebanksdk.response;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Api response for out of wallet answers submission.
 */

public class VerifyOowResponse {
    @SerializedName("referenceNumber")
    private String mReferenceNumber;

    @SerializedName("answers")
    private Integer mAnswers;

    public static VerifyOowResponse create(String summaryResult) {
        return new VerifyOowResponse(summaryResult);
    }

    private VerifyOowResponse(String summaryResult) {
        mReferenceNumber = summaryResult;
    }

    private VerifyOowResponse(String summaryResult, Integer answers) {
        mReferenceNumber = summaryResult;
        mAnswers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerifyOowResponse that = (VerifyOowResponse) o;

        return mReferenceNumber != null ? mReferenceNumber.equals(that.mReferenceNumber) : that.mReferenceNumber == null;

    }

    @Override
    public int hashCode() {
        return mReferenceNumber != null ? mReferenceNumber.hashCode() : 0;
    }

    public String getReferenceNumber() {
        return mReferenceNumber;
    }

    public Integer getAnswers() {
        return mAnswers;
    }
}
