package com.example.cf_sdk.changebankapi.parameter.transaction;

import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Parameters to build the request for PDF Statments
 */

public class PdfStatementsParameters extends Parameters {

    private String mMemberId;

    private String mMonth;

    private int mYear;

    private String mAccountId;


    public static PdfStatementsParameters create(String month, int year, String accountId) {
        return new PdfStatementsParameters(new HashMap<String, String>(), month, year, accountId, "");
    }

    private PdfStatementsParameters(Map<String, String> headers,
                                    String month,
                                    int year,
                                    String accountId,
                                    String memberId) {
        super(headers);
        mMonth = month;
        mYear = year;
        mAccountId = accountId;
        mMemberId = memberId;
    }


    public PdfStatementsParameters withMemberId(String memberId) {
        return new PdfStatementsParameters(getHeaders(), mMonth, mYear, mAccountId, memberId);
    }

    public String getMemberId() {
        return mMemberId;
    }

    public String getMonth() {
        return mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public String getAccountId() {
        return mAccountId;
    }



}
