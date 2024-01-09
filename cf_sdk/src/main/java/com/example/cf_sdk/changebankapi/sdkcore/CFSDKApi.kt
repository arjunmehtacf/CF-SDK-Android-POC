package com.example.cf_sdk.changebankapi.sdkcore


import com.example.cf_sdk.changebankapi.ChangebankSingleObserver
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.defination.request.SettingsParameter
import com.example.cf_sdk.changebankapi.task.VersionConfigTask
import com.example.cf_sdk.defination.CFSDKConstant
import com.example.cf_sdk.defination.CFSDKErrorHandler
import com.example.cf_sdk.defination.CFSDKProvider
import com.example.cf_sdk.defination.CFSDKResponseCallback

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
}