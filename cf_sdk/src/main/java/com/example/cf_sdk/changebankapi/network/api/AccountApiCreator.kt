package com.example.cf_sdk.changebankapi.network.api

import com.example.cf_sdk.changebankapi.log.Logger
import retrofit2.Retrofit

/**
 * Created by victorojeda on 1/29/18.
 *
 * Network [AccountApi] creator.
 */
class AccountApiCreator : ApiCreator<AccountApi?> {
    override fun create(logger: Logger?, retrofit: Retrofit?): AccountApi {
        return retrofit!!.create(AccountApi::class.java)
    }
}