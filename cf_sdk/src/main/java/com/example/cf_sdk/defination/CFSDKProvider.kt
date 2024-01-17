package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.AccessTokenResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse
import com.example.cf_sdk.defination.response.version.VersionConfig

interface CFSDKProvider {
    fun callVersionConfigFunction(baseUrl:String, appId:String,responseCallback: CFSDKResponseCallback<VersionConfig>)

    fun callAuthCode(cardHolderId:String, sdkVersion:String, sdkSessionId:String,responseCallback: CFSDKResponseCallback<AuthCodeResponse>)

    fun callAccessToken(authCode:String, sdkSessionId:String, responseCallback: CFSDKResponseCallback<AccessTokenResponse>)
}
