package com.example.cf_sdk.changebankapi.network.api


import com.example.cf_sdk.changebankapi.log.Logger
import com.example.cf_sdk.changebankapi.network.api.MemberApi
import retrofit2.Retrofit

/**
 *
 * Network [MemberApi] creator.
 */
class MemberApiCreator : ApiCreator<MemberApi?> {
    override fun create(logger: Logger?, retrofit: Retrofit?): MemberApi? {
        return retrofit?.create(MemberApi::class.java)
    }
}