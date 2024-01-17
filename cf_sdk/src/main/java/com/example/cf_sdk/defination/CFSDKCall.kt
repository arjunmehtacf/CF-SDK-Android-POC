package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.changebankapi.sdkcore.CFSDKApi
import com.example.cf_sdk.defination.response.AccessTokenResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse


object CFSDKCall {
    private val cfsdkProvider: CFSDKProvider = CFSDKApi()

    // To get the mobile settings related information
    fun getVersionConfig(
        baseUrl: String,
        appId: String,
        responseCallback: CFSDKResponseCallback<VersionConfig>,
    ) {
        return cfsdkProvider.callVersionConfigFunction(baseUrl, appId, responseCallback)
    }

    // To authenticate user for authentication
    fun getAuthCode(
        cardHolderId: String,
        sdkVersion: String,
        sdkSessionId: String,
        responseCallback: CFSDKResponseCallback<AuthCodeResponse>,
    ) {
        return cfsdkProvider.callAuthCode(cardHolderId, sdkVersion, sdkSessionId, responseCallback)
    }

    // To get Access Token from auth code
    fun getAccessToken(
        authCode: String,
        sdkSessionId: String,
        responseCallback: CFSDKResponseCallback<AccessTokenResponse>,
    ) {
        return cfsdkProvider.callAccessToken(authCode, sdkSessionId, responseCallback)
    }
}