package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.changebankapi.sdkcore.CFSDKApi


object CFSDKCall {
    private val cfsdkProvider: CFSDKProvider = CFSDKApi()


    fun getVersionConfig(
        baseUrl: String,
        appId: String,
        responseCallback: CFSDKResponseCallback<VersionConfig>,
    ) {
        return cfsdkProvider.callVersionConfigFunction(baseUrl, appId, responseCallback)
    }
}