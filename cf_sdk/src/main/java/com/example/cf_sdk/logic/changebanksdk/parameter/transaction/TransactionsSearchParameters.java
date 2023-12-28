package com.example.cf_sdk.logic.changebanksdk.parameter.transaction;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

/**
 *
 * Parameters for Searching Transactions.
 */

public class TransactionsSearchParameters extends Parameters {
    private static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_RECORDS_PER_PAGE = 20;

    private transient String mSearchText;

    private transient Integer mPage = DEFAULT_PAGE;

    private transient Integer mRecordsPerPage = DEFAULT_RECORDS_PER_PAGE;

    private transient DateTime mFromDate;

    private transient DateTime mToDate;

    @SerializedName("minAmount")
    private String  mMin;

    @SerializedName("maxAmount")
    private String mMax;

    private String mMemberId;

    public static TransactionsSearchParameters create(@Nonnull String textSearch) {
        return new TransactionsSearchParameters(
                new HashMap<String, String>(),
                textSearch,
                null,
                null,
                DEFAULT_PAGE,
                DEFAULT_RECORDS_PER_PAGE,
                null,
                null,
                "");
    }

    public static TransactionsSearchParameters create(@Nonnull String textSearch,
                                                      DateTime from,
                                                      DateTime to,
                                                      String min,
                                                      String max,String cardToken) {
        return new TransactionsSearchParameters(
                new HashMap<String, String>(),
                textSearch,
                from,
                to,
                DEFAULT_PAGE,
                DEFAULT_RECORDS_PER_PAGE,
                min,
                max,
                cardToken);
    }


    public static TransactionsSearchParameters create(@Nonnull String textSearch,
                                                      @Nonnull DateTime from,
                                                      @Nonnull DateTime to,
                                                      @Nonnull Integer page,
                                                      @Nonnull Integer recordsPerPage) {
        return new TransactionsSearchParameters(
                new HashMap<String, String>(),
                textSearch,
                from,
                to,
                page,
                recordsPerPage,
                null,
                null,
                "");
    }

    private TransactionsSearchParameters(Map<String, String> headers,
                                         String textSearch,
                                         DateTime from,
                                         DateTime to,
                                         @Nonnull Integer page,
                                         @Nonnull Integer recordsPerPage,
                                         String min,
                                         String max,
                                         String memberId) {
        super(headers);

        mSearchText = textSearch;
        mFromDate = from;
        mToDate = to;
        mPage = page;
        mRecordsPerPage = recordsPerPage;
        mMin = min;
        mMax = max;
        mMemberId = memberId;
    }

    public TransactionsSearchParameters withNextPage() {
        return new TransactionsSearchParameters(
                getHeaders(),
                mSearchText,
                mFromDate,
                mToDate,
                ++mPage,
                mRecordsPerPage,
                mMin,
                mMax,
                mMemberId);
    }

    public TransactionsSearchParameters withMemberId(String memberId) {
        return new TransactionsSearchParameters(
                getHeaders(),
                mSearchText,
                mFromDate,
                mToDate,
                mPage,
                mRecordsPerPage,
                mMin,
                mMax,
                memberId);
    }

    public String getFromDate() {
        if (mFromDate == null) {
            mFromDate = DateTime.now().minusYears(50);
        }
        return mFromDate.toString("yyyy-MM-dd");
    }

    public String getToDate() {
        if (mToDate == null) {
            mToDate = DateTime.now().plusYears(1);
        }
        return mToDate.toString("yyyy-MM-dd");
    }

    public Integer getPage() {
        return mPage;
    }

    public Integer getRecordsPerPage() {
        return mRecordsPerPage;
    }

    public String getMemberId() {
        return mMemberId;
    }

    public String getSearchText() {
        return mSearchText;
    }

    public String getMinMoney(){
        return mMin;
    }

    public String getMaxMoney(){
        return mMax;
    }
}
