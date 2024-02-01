package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.defination.response.UserProfileResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse
import com.example.cf_sdk.defination.response.Session
import com.example.cf_sdk.defination.response.version.VersionConfig

interface CFSDKProvider {
    fun callVersionConfigFunction(responseCallback: CFSDKResponseCallback<VersionConfig>)

    fun callAuthCode(baseUrl: String,cardHolderId:String, sdkVersion:String, responseCallback: CFSDKResponseCallback<AuthCodeResponse>)

    fun callAccessToken(authCode:String, responseCallback: CFSDKResponseCallback<Session>)

    fun callGetUserProfile(responseCallback: CFSDKResponseCallback<UserProfileResponse>)

    fun callGetMemberAccounts(customerNumber:String, responseCallback: CFSDKResponseCallback<AccountsApiResponse>)
}
