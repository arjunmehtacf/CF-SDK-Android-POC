package com.example.cf_sdk.changebankapi.parameter.account;

import androidx.annotation.NonNull;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * Contains request parameter for deposited check
 */
public class CheckDepositedParameters extends Parameters {
    @SerializedName("memberId")
    private Long mMemberId;

    @SerializedName("page")
    private int mPage = 1;

    @SerializedName("recordsPerPage")
    private int mRecordsPerPage = 20;



    public static CheckDepositedParameters create() {
        return new CheckDepositedParameters(
                new HashMap<String, String>(),
                1,
                20,
                -1);
    }

    public static CheckDepositedParameters create(@NonNull Integer page,
                                                  @NonNull Integer recordsPerPage) {
        return new CheckDepositedParameters(
                new HashMap<String, String>(),
                page,
                recordsPerPage,
                -1);
    }

    private CheckDepositedParameters(Map<String, String> headers,
                                     @NonNull Integer page,
                                     @NonNull Integer recordsPerPage,
                                     long memberId) {
        super(headers);
        mPage = page;
        mRecordsPerPage = recordsPerPage;
        mMemberId = memberId;
    }

    public CheckDepositedParameters withNextPage() {
        return new CheckDepositedParameters(
                getHeaders(),
                ++mPage,
                mRecordsPerPage,
                mMemberId);
    }

    public CheckDepositedParameters withMemberId(long memberId) {
        return new CheckDepositedParameters(
                getHeaders(),
                mPage,
                mRecordsPerPage,
                memberId);
    }


    public Integer getPage() {
        return mPage;
    }

    public Integer getRecordsPerPage() {
        return mRecordsPerPage;
    }

    public Long getMemberId() {
        return mMemberId;
    }
}
