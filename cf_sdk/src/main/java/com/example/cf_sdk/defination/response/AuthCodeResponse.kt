package com.example.cf_sdk.defination.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthCodeResponse {
    @SerializedName("authCode")
    @Expose
    private var authCode: String = ""


    fun getAuthCode(): String {
        return authCode
    }

    fun setAuthCode(alert: String) {
        this.authCode = alert
    }

}