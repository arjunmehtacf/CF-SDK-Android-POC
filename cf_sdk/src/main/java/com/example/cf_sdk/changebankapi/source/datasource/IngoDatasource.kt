package com.example.cf_sdk.changebankapi.source.datasource

import com.example.cf_sdk.changebankapi.parameter.StringParameters
import com.example.cf_sdk.changebankapi.parameter.ingo.VerifyIngoSSNParameters
import com.example.cf_sdk.changebankapi.response.IngoResponse
import io.reactivex.Single

/**
 *
 *
 * Datasource for Ingo API.
 */
interface IngoDatasource {
    fun verifyIngoSSSN(verifyIngoSSNParameters: VerifyIngoSSNParameters?): Single<IngoResponse?>?
    fun getIngoSSOToken(stringParameters: StringParameters?): Single<IngoResponse?>?
}