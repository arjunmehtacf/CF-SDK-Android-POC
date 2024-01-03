package com.example.cf_sdk.changebankapi.response;

import com.example.cf_sdk.changebankapi.model.account.Money;
import com.google.gson.annotations.SerializedName;


/**
 *
 * Response for Account balance api.
 */

public class AccountBalanceResponse {

    @SerializedName("balance_id")
    private String mId;

    @SerializedName("balance")
    private Money mBalance;

    public static AccountBalanceResponse create(String id, Money balance) {
        return new AccountBalanceResponse(id, balance);
    }

    public AccountBalanceResponse() {

    }

    private AccountBalanceResponse(String id, Money balance) {
        mId = id;
        mBalance = balance;
    }

    public String getId() {
        return mId;
    }

    public Money getBalance() {
        return mBalance;
    }

}