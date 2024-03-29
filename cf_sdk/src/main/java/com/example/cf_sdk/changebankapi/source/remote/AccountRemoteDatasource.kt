package com.example.cf_sdk.changebankapi.source.remote


import com.example.cf_sdk.changebankapi.network.api.AccountApi
import com.example.cf_sdk.changebankapi.parameter.StringParameters
import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.changebankapi.source.datasource.AccountDatasource
import com.example.cf_sdk.defination.CFSDKConstant
import io.reactivex.Single
import java.io.File

/**
 *
 * Remote datasource to call account endpoints.
 */
class AccountRemoteDatasource(
    private val mAccountApi: AccountApi,
    private val mCacheDirectory: File,
) : AccountDatasource {

    override fun getMemberAccounts(stringParameters: StringParameters?): Single<AccountsApiResponse?>? {
        return mAccountApi.getMemberAccounts(
            stringParameters?.headers?.get(CFSDKConstant.KEY_BASE_URL) + "customer/"+stringParameters?.firstString+"/cards",
            stringParameters?.headers
        )
    }

    override fun saveAccountsApiResponse(accountsApiResponse: AccountsApiResponse?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveAccountsApiResponse"
        )
    }
}