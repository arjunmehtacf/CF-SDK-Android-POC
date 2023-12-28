package com.example.cf_sdk.logic.changebanksdk.response;

import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Response to fetch a list of accounts.
 */

public class AccountsApiResponse {


    @SerializedName("title")
    private String title;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("middleName")
    private String middleName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("cards")
    private List<Account> mAccounts = null;

    public static AccountsApiResponse create(List<Account> accounts) {
        return new AccountsApiResponse(accounts);
    }

    private AccountsApiResponse(List<Account> accounts) {
        mAccounts = accounts;
    }

    public List<Account> getAccounts() {
        return mAccounts;
    }
}
