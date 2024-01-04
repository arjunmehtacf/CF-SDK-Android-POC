package com.example.cf_sdk.changebankapi.sdkcore


import com.example.cf_sdk.changebankapi.ChangebankSingleObserver
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.defination.request.SettingsParameter
import com.example.cf_sdk.changebankapi.task.VersionConfigTask
import com.example.cf_sdk.defination.CFSDKProvider
import com.example.cf_sdk.defination.CFSDKResponseCallback

import io.reactivex.disposables.Disposable

class CFSDKApi : CFSDKProvider {


    override fun callVersionConfigFunction(
        baseUrl: String,
        appId: String,
        responseCallback: CFSDKResponseCallback<VersionConfig>,
    ) {
        VersionConfigTask().execute(object :
            ChangebankSingleObserver<VersionConfig> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                responseCallback.onFailure(e)

            }

            override fun onSuccess(t: VersionConfig) {
                responseCallback.onSuccess(t)
            }
        }, SettingsParameter(null))
    }
}