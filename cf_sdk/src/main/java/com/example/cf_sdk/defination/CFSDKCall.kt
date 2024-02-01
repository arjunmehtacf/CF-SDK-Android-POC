package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.changebankapi.sdkcore.CFSDKApi
import com.example.cf_sdk.defination.response.AccessTokenResponse
import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse
import com.example.cf_sdk.defination.response.Session
import com.example.cf_sdk.defination.response.UserProfileResponse

/**
 * CFSDKCall is singleton class for calling functions of ChangeFinancials SDK.
 */
object CFSDKCall {
    private val cfsdkProvider: CFSDKProvider = CFSDKApi()

    // To get the mobile settings related information
    fun getVersionConfig(
        responseCallback: CFSDKResponseCallback<VersionConfig>,
    ) {
        return cfsdkProvider.callVersionConfigFunction(responseCallback)
    }

    // To authenticate user for authentication
    fun getAuthCode(
        baseUrl:String,
        cardHolderId: String,
        sdkVersion: String,
        responseCallback: CFSDKResponseCallback<AuthCodeResponse>,
    ) {
        return cfsdkProvider.callAuthCode(baseUrl,cardHolderId, sdkVersion, responseCallback)
    }

    // To get Access Token from auth code
    fun getAccessToken(
        authCode: String,
        responseCallback: CFSDKResponseCallback<Session>,
    ) {
        return cfsdkProvider.callAccessToken(authCode, responseCallback)
    }

    // To get user profile data
    fun getUserProfileInfo(responseCallback: CFSDKResponseCallback<UserProfileResponse>){
        return cfsdkProvider.callGetUserProfile(responseCallback)
    }

    // To get account list
    fun getAccountsData(customerNumber:String, responseCallback: CFSDKResponseCallback<AccountsApiResponse>){
        return cfsdkProvider.callGetMemberAccounts(customerNumber, responseCallback)
    }
}