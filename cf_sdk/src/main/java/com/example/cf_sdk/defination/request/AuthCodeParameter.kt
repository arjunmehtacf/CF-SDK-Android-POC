package com.example.cf_sdk.defination.request

import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.google.gson.annotations.SerializedName

class AuthCodeParameter(headers: Map<String?, String?>?) :
    Parameters(headers) {
    @SerializedName("cardholderId")
    public var cardholderId = ""

    @SerializedName("sdkVersion")
    public var sdkVersion = ""

    @SerializedName("sdkSessionId")
    public var sdkSessionId = ""

}