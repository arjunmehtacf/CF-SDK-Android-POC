package com.example.cf_sdk.logic.changebanksdk.parameter.transaction;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Parameters to get transactions by ID.
 */

public class TransactionByIdParameters extends Parameters {
    private transient final String mTransactionId;
    private transient long mMemberId;

    public static TransactionByIdParameters create(String transactionId) {
        return new TransactionByIdParameters(new HashMap<String, String>(), transactionId, 0);
    }

    private TransactionByIdParameters(Map<String, String> headers,
                                      String transactionId,
                                      long memberId) {
        super(headers);
        mTransactionId = transactionId;
        mMemberId = memberId;
    }

    public String getTransactionId() {
        return mTransactionId;
    }

    public TransactionByIdParameters withMemberId(long memberId) {
        return new TransactionByIdParameters(getHeaders(), mTransactionId, memberId);
    }

    public long getMemberId() {
        return mMemberId;
    }
}
