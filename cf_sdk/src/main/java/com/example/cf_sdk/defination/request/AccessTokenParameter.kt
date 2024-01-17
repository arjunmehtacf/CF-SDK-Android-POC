package com.example.cf_sdk.defination.request


import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.google.gson.annotations.SerializedName

class AccessTokenParameter(headers: Map<String?, String?>?) :
    Parameters(headers) {
    @SerializedName("authCode")
    public var authCode = ""

    @SerializedName("sdkSessionId")
    public var sdkSessionId = ""

}