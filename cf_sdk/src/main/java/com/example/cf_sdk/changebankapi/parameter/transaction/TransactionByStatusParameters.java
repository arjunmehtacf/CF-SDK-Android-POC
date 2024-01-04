package com.example.cf_sdk.changebankapi.parameter.transaction;

import com.example.cf_sdk.changebankapi.model.transaction.TransactionFilter;
import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Parameters to get transactions by status.
 */

public class TransactionByStatusParameters extends Parameters {
    private transient final TransactionFilter mTransactionStatus;
    private transient long mMemberId;

    public static TransactionByStatusParameters Create(TransactionFilter transactionStatus) {
        return new TransactionByStatusParameters(new HashMap<String, String>(), transactionStatus, 0);
    }

    private TransactionByStatusParameters(Map<String, String> headers,
                                          TransactionFilter transactionStatus,
                                          long memberId) {
        super(headers);
        mTransactionStatus = transactionStatus;
        mMemberId = memberId;
    }

    public TransactionFilter getTransactionStatus() {
        return mTransactionStatus;
    }

    public TransactionByStatusParameters withMemberId(long memberId) {
        return new TransactionByStatusParameters(getHeaders(), mTransactionStatus, memberId);
    }

    public long getMemberId() {
        return mMemberId;
    }
}
