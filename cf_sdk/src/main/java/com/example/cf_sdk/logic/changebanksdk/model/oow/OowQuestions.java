package com.example.cf_sdk.logic.changebanksdk.model.oow;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Out of wallet set of questions.
 */

public class OowQuestions {
    @SerializedName("memberId")
    private Long mMemberId;

    @SerializedName("timeout")
    private Long mTimeout = Long.MAX_VALUE;

    @SerializedName("referenceNumber")
    private String mReferenceNumber = "";

    @SerializedName("questions")
    private List<Question> mQuestions = new ArrayList<>();

    @SerializedName("answer")
    private Integer mAnswer;

    @SerializedName("socialSecurity")
    private String mSsn;

    @SerializedName("answers")
    private Integer answers = -1;

    private transient boolean mIsExpired;

    public static OowQuestions create(Long id) {
        return new OowQuestions(id);
    }

    private OowQuestions(Long id) {
        mMemberId = id;
    }

    private OowQuestions(Long memberId,
                         Long timeout,
                         String referenceNumber,
                         List<Question> questions,
                         boolean isExpired) {
        mMemberId = memberId;
        mTimeout = timeout;
        mReferenceNumber = referenceNumber;
        mQuestions = questions;
        mIsExpired = isExpired;
    }

    public Integer getAnswers(){return answers;}

    public Integer getAnswer() {
        return mAnswer;
    }

    public String getSsn() {
        return mSsn;
    }

    public void setSsn(String mSsn) {
        this.mSsn = mSsn;
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public Long getTimeout() {
        return mTimeout;
    }

    public String getReferenceNumber() {
        return mReferenceNumber;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public boolean isExpired() {
        return mIsExpired;
    }

    public OowQuestions withIsExpired(boolean isExpired) {
        return new OowQuestions(mMemberId, mTimeout, mReferenceNumber, mQuestions, isExpired);
    }

    public OowQuestions withQuestions(List<Question> questions) {
        return new OowQuestions(mMemberId, mTimeout, mReferenceNumber, questions, mIsExpired);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OowQuestions oowQuestions = (OowQuestions) o;

        return getReferenceNumber() != null ?
                getReferenceNumber().equals(oowQuestions.getReferenceNumber()) :
                oowQuestions.getReferenceNumber() == null;
    }

    @Override
    public int hashCode() {
        return getReferenceNumber() != null ? getReferenceNumber().hashCode() : 0;
    }
}
