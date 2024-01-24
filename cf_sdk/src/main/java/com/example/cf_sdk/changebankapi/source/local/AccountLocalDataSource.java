package com.example.cf_sdk.changebankapi.source.local;

import com.example.cf_sdk.changebankapi.parameter.StringParameters;
import com.example.cf_sdk.defination.response.AccountsApiResponse;
import com.example.cf_sdk.changebankapi.source.datasource.AccountDatasource;

import io.reactivex.Single;

/**
 * Local stored data related to Account.
 */

public class AccountLocalDataSource implements AccountDatasource {

    @Override
    public Single<AccountsApiResponse> getMemberAccounts(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getMemberAccounts");
    }

    @Override
    public void saveAccountsApiResponse(AccountsApiResponse accountsApiResponse) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveAccountsApiResponse");
    }


}
