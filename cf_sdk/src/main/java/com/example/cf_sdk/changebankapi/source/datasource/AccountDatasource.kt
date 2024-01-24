package com.example.cf_sdk.changebankapi.source.datasource


import com.example.cf_sdk.changebankapi.parameter.StringParameters
import com.example.cf_sdk.defination.response.AccountsApiResponse
import io.reactivex.Single

/**
 *
 *
 * Datasource to provide account data.
 */
interface AccountDatasource {

    fun getMemberAccounts(stringParameters: StringParameters?): Single<AccountsApiResponse?>?


    fun saveAccountsApiResponse(accountsApiResponse: AccountsApiResponse?)

}