package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.version.VersionConfig

interface CFSDKProvider {
    fun callVersionConfigFunction(baseUrl:String, appId:String,responseCallback: CFSDKResponseCallback<VersionConfig>)
}