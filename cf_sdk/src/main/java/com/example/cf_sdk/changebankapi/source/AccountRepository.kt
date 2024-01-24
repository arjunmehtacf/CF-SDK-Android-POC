package com.example.cf_sdk.changebankapi.source

import com.example.cf_sdk.changebankapi.exception.AccountNotFoundException
import com.example.cf_sdk.changebankapi.parameter.StringParameters
import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.changebankapi.source.datasource.AccountDatasource
import io.reactivex.Single
import io.reactivex.functions.Consumer

/**
 *
 * Account repository is in charge of choosing the correct [AccountDatasource]
 */
class AccountRepository(
    private val mRemoteDatasource: AccountDatasource,
    private val mLocalDataSource: AccountDatasource,
    private val mCacheDatasource: AccountDatasource,
) : AccountDatasource {

    override fun getMemberAccounts(stringParameters: StringParameters?): Single<AccountsApiResponse?>? {
        return mRemoteDatasource.getMemberAccounts(stringParameters)
    }

    override fun saveAccountsApiResponse(accountsApiResponse: AccountsApiResponse?) {
        mCacheDatasource.saveAccountsApiResponse(accountsApiResponse)
    }
}