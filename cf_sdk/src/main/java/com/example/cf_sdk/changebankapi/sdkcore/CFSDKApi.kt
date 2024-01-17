package com.example.cf_sdk.changebankapi.sdkcore


import com.example.cf_sdk.changebankapi.ChangebankSingleObserver
import com.example.cf_sdk.changebankapi.task.AccessTokenTask
import com.example.cf_sdk.changebankapi.task.AuthCodeTask
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.defination.request.SettingsParameter
import com.example.cf_sdk.changebankapi.task.VersionConfigTask
import com.example.cf_sdk.defination.CFSDKConstant
import com.example.cf_sdk.defination.CFSDKErrorHandler
import com.example.cf_sdk.defination.CFSDKProvider
import com.example.cf_sdk.defination.CFSDKResponseCallback
import com.example.cf_sdk.defination.request.AccessTokenParameter
import com.example.cf_sdk.defination.request.AuthCodeParameter
import com.example.cf_sdk.defination.response.AccessTokenResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse

import io.reactivex.disposables.Disposable

class CFSDKApi : CFSDKProvider {


    /**
     * This function is used to get mobile settings data from server
     */
    override fun callVersionConfigFunction(
        baseUrl: String,
        appId: String,
        responseCallback: CFSDKResponseCallback<VersionConfig>,
    ) {
        val settingsHeader = HashMap<String, String>()
        settingsHeader.put(CFSDKConstant.KEY_BASE_URL, baseUrl)

        val settingsParameter = SettingsParameter(settingsHeader)
        settingsParameter.applicationId = appId
        VersionConfigTask().execute(object :
            ChangebankSingleObserver<VersionConfig> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                responseCallback.onFailure(CFSDKErrorHandler.handleAPIError(e))
            }

            override fun onSuccess(t: VersionConfig) {
                responseCallback.onSuccess(t)
            }
        }, settingsParameter)
    }

    // To authenticate user for authentication
    override fun callAuthCode(
        cardHolderId: String,
        sdkVersion: String,
        sdkSessionId: String,
        responseCallback: CFSDKResponseCallback<AuthCodeResponse>,
    ) {

        val authCodeParameter =
            AuthCodeParameter(HashMap())
        authCodeParameter.cardholderId = cardHolderId
        authCodeParameter.sdkVersion = sdkVersion
        authCodeParameter.sdkSessionId = sdkSessionId
        authCodeParameter.addToken("Bearer xL5I9DCkb5jqd9o5iJ2a2MVSkm+OP7IwtrVABfElC9dphvQLWPgkuQEdSRhi0dU0")
        AuthCodeTask().execute(object : ChangebankSingleObserver<AuthCodeResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(error: Throwable) {
                responseCallback.onFailure(CFSDKErrorHandler.handleAPIError(error))
            }

            override fun onSuccess(t: AuthCodeResponse) {
                responseCallback.onSuccess(t)
            }
        }, authCodeParameter)
    }

    // To get Access Token from auth code
    override fun callAccessToken(
        authCode: String,
        sdkSessionId: String,
        responseCallback: CFSDKResponseCallback<AccessTokenResponse>,
    ) {
        val accessTokenParameter =
            AccessTokenParameter(HashMap())
        accessTokenParameter.authCode = authCode
        accessTokenParameter.sdkSessionId = sdkSessionId
        accessTokenParameter.addToken("Bearer xL5I9DCkb5jqd9o5iJ2a2MVSkm+OP7IwtrVABfElC9dphvQLWPgkuQEdSRhi0dU0")
        accessTokenParameter.headers.put(CFSDKConstant.X_APPLICATION_ID,"f4665ee1-f8c1-4111-baa5-e755a2e83320")
        AccessTokenTask().execute(object : ChangebankSingleObserver<AccessTokenResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(error: Throwable) {
                responseCallback.onFailure(CFSDKErrorHandler.handleAPIError(error))
            }

            override fun onSuccess(t: AccessTokenResponse) {
                responseCallback.onSuccess(t)
            }
        }, accessTokenParameter)
    }
}