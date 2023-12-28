package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

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

    public static CheckDepositedParameters create(@Nonnull Integer page,
                                                  @Nonnull Integer recordsPerPage) {
        return new CheckDepositedParameters(
                new HashMap<String, String>(),
                page,
                recordsPerPage,
                -1);
    }

    private CheckDepositedParameters(Map<String, String> headers,
                                     @Nonnull Integer page,
                                     @Nonnull Integer recordsPerPage,
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
