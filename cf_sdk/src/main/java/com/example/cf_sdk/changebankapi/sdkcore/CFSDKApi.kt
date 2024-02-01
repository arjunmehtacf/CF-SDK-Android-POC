package com.example.cf_sdk.changebankapi.sdkcore


import com.example.cf_sdk.changebankapi.ChangebankSingleObserver
import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.task.AccessTokenTask
import com.example.cf_sdk.changebankapi.task.AuthCodeTask
import com.example.cf_sdk.changebankapi.task.GetAccountsTask
import com.example.cf_sdk.changebankapi.task.UserProfileTask
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.defination.request.SettingsParameter
import com.example.cf_sdk.changebankapi.task.VersionConfigTask
import com.example.cf_sdk.defination.CFSDKConstant
import com.example.cf_sdk.defination.CFSDKErrorHandler
import com.example.cf_sdk.defination.CFSDKProvider
import com.example.cf_sdk.defination.CFSDKResponseCallback
import com.example.cf_sdk.defination.request.AccessTokenParameter
import com.example.cf_sdk.defination.request.AuthCodeParameter
import com.example.cf_sdk.defination.request.UserProfileParameter
import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse
import com.example.cf_sdk.defination.response.Session
import com.example.cf_sdk.defination.response.UserProfileResponse

import io.reactivex.disposables.Disposable

class CFSDKApi : CFSDKProvider {

    lateinit var session: Session
    lateinit var sessionId: String
    lateinit var sdkURL: String

    // This function is used to get mobile settings data from server
    override fun callVersionConfigFunction(
        responseCallback: CFSDKResponseCallback<VersionConfig>,
    ) {
        val settingsHeader = HashMap<String, String>()
        settingsHeader.put(CFSDKConstant.KEY_BASE_URL, sdkURL)

        val settingsParameter = SettingsParameter(settingsHeader)
        settingsParameter.applicationId = CFSDKConstant.APP_ID
        VersionConfigTask().execute(object :
            ChangebankSingleObserver<VersionConfig> {
            override fun onSubscribe(d: Disposable) {}

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
        baseUrl: String,
        cardHolderId: String,
        sdkVersion: String,
        responseCallback: CFSDKResponseCallback<AuthCodeResponse>,
    ) {
        sessionId = CFSDKConstant.generateRandomString(32)
        sdkURL = baseUrl
        val settingsHeader = HashMap<String?, String?>()
        settingsHeader.put(CFSDKConstant.KEY_BASE_URL, sdkURL)
        settingsHeader.put(CFSDKConstant.INSTANCE_NAME_KEY, CFSDKConstant.INSTANCE_NAME_VALUE)
        val authCodeParameter = AuthCodeParameter(settingsHeader)
        authCodeParameter.cardholderId = cardHolderId
        authCodeParameter.sdkVersion = sdkVersion
        authCodeParameter.sdkSessionId = sessionId
        authCodeParameter.addToken(CFSDKConstant.ACCESS_TOKEN_FOR_EX_USER_AUTHORIZATION)
        AuthCodeTask().execute(object : ChangebankSingleObserver<AuthCodeResponse> {
            override fun onSubscribe(d: Disposable) {}

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
        responseCallback: CFSDKResponseCallback<Session>,
    ) {
        val settingsHeader = HashMap<String?, String?>()
        settingsHeader.put(CFSDKConstant.KEY_BASE_URL, sdkURL)
        val accessTokenParameter = AccessTokenParameter(settingsHeader)
        accessTokenParameter.authCode = authCode
        accessTokenParameter.sdkSessionId = sessionId
        accessTokenParameter.addToken(CFSDKConstant.ACCESS_TOKEN_FOR_EX_USER_AUTHORIZATION)
        accessTokenParameter.headers.put(CFSDKConstant.X_APPLICATION_ID, CFSDKConstant.APP_ID)
        AccessTokenTask().execute(object : ChangebankSingleObserver<Session> {
            override fun onSubscribe(d: Disposable) {}

            override fun onError(error: Throwable) {
                responseCallback.onFailure(CFSDKErrorHandler.handleAPIError(error))
            }

            override fun onSuccess(t: Session) {
                session = t
                responseCallback.onSuccess(t)
            }
        }, accessTokenParameter)
    }

    // To get user profile data
    override fun callGetUserProfile(responseCallback: CFSDKResponseCallback<UserProfileResponse>) {
        val parameters = UserProfileParameter.create()
        parameters.headers.put(CFSDKConstant.KEY_BASE_URL,sdkURL)
        parameters.headers.put(CFSDKConstant.X_APPLICATION_ID, CFSDKConstant.APP_ID)
        UserProfileTask(session).execute(
            object : ChangebankSingleObserver<UserProfileResponse> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    responseCallback.onFailure(CFSDKErrorHandler.handleAPIError(e))
                }

                override fun onSuccess(t: UserProfileResponse) {
                    responseCallback.onSuccess(t)
                }
            },
            parameters
        )
    }

    // To get member accounts list
    override fun callGetMemberAccounts(customerNumber:String, responseCallback: CFSDKResponseCallback<AccountsApiResponse>) {
        val parameters = Parameters.getEmptyParameters(false)
        parameters.headers.put(CFSDKConstant.KEY_BASE_URL, sdkURL)
        parameters.headers.put(CFSDKConstant.X_APPLICATION_ID, CFSDKConstant.APP_ID)
        GetAccountsTask(session,customerNumber).execute(
            object : ChangebankSingleObserver<AccountsApiResponse> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    responseCallback.onFailure(CFSDKErrorHandler.handleAPIError(e))
                }

                override fun onSuccess(t: AccountsApiResponse) {
                    responseCallback.onSuccess(t)
                }
            },
            parameters
        )
    }
}