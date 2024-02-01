package com.example.cf_sdk.changebankapi.network.api

import com.example.cf_sdk.changebankapi.log.Logger
import retrofit2.Retrofit

class AuthenticationApiCreator : ApiCreator<AuthenticationApi?> {
    override fun create(logger: Logger?, retrofit: Retrofit?): AuthenticationApi {
        return retrofit!!.create(AuthenticationApi::class.java)
    }
}