package com.example.sdk_no_dagger.changebankapi.network.api


import com.example.cf_sdk.changebankapi.log.Logger
import com.example.sdk_no_dagger.changebankapi.util.Creator
import retrofit2.Retrofit

/**
 *
 * Interface to abstract the creation of an API.
 */
interface ApiCreator<R> : Creator<Retrofit?, R> {
    override fun create(logger: Logger?, parameter: Retrofit?): R
}