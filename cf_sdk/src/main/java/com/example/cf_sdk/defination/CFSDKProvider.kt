package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.defination.response.UserProfileResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse
import com.example.cf_sdk.defination.response.Session
import com.example.cf_sdk.defination.response.version.VersionConfig

interface CFSDKProvider {
    fun callVersionConfigFunction(baseUrl:String, appId:String,responseCallback: CFSDKResponseCallback<VersionConfig>)

    fun callAuthCode(cardHolderId:String, sdkVersion:String, sdkSessionId:String,responseCallback: CFSDKResponseCallback<AuthCodeResponse>)

    fun callAccessToken(authCode:String, sdkSessionId:String, responseCallback: CFSDKResponseCallback<Session>)

    fun callGetUserProfile(responseCallback: CFSDKResponseCallback<UserProfileResponse>)

    fun callGetMemberAccounts(responseCallback: CFSDKResponseCallback<AccountsApiResponse>)
}
