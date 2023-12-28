package com.example.cf_sdk.logic.changebanksdk.parameter.transaction;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

/**
 *
 * Parameters for fetching Transactions by date.
 */

public class TransactionsByDateParameters extends Parameters {
    private static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_RECORDS_PER_PAGE = 20;

    private transient Integer mPage = DEFAULT_PAGE;

    private transient Integer mRecordsPerPage = DEFAULT_RECORDS_PER_PAGE;

    private transient DateTime mFromDate;

    private transient DateTime mToDate;

    private transient long mMemberId;

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    private transient String cardToken;

    public static TransactionsByDateParameters create(@Nonnull DateTime from,
                                                      @Nonnull DateTime to,String cardToken) {
        return new TransactionsByDateParameters(
                new HashMap<String, String>(),
                from,
                to,
                DEFAULT_PAGE,
                DEFAULT_RECORDS_PER_PAGE,
                -1,cardToken);
    }

    public static TransactionsByDateParameters create(@Nonnull DateTime from,
                                                      @Nonnull DateTime to,
                                                      @Nonnull Integer page,
                                                      @Nonnull Integer recordsPerPage,String cardToken) {
        return new TransactionsByDateParameters(
                new HashMap<String, String>(),
                from,
                to,
                page,
                recordsPerPage,
                -1,cardToken);
    }

    private TransactionsByDateParameters(Map<String, String> headers,
                                         DateTime from,
                                         DateTime to,
                                         @Nonnull Integer page,
                                         @Nonnull Integer recordsPerPage,
                                         long memberId,String cardToken) {
        super(headers);

        mFromDate = from;
        mToDate = to;
        mPage = page;
        mRecordsPerPage = recordsPerPage;
        mMemberId = memberId;
        this.cardToken = cardToken;
    }

    public TransactionsByDateParameters withNextPage() {
        return new TransactionsByDateParameters(
                getHeaders(),
                mFromDate,
                mToDate,
                ++mPage,
                mRecordsPerPage,
                mMemberId,cardToken);
    }

    public TransactionsByDateParameters withMemberId(long memberId) {
        return new TransactionsByDateParameters(
                getHeaders(),
                mFromDate,
                mToDate,
                mPage,
                mRecordsPerPage,
                memberId,cardToken);
    }

    public String getFromDate() {
        return mFromDate.toString("yyyy-MM-dd");
    }

    public boolean isToDateCurrentMonth() {
        return mToDate.getMonthOfYear() == DateTime.now().getMonthOfYear() && mToDate.getYear() == DateTime.now().getYear();
    }

    public String getToDate() {
        return mToDate.toString("yyyy-MM-dd");
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
