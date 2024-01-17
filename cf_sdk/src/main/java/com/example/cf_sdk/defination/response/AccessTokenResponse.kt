package com.example.cf_sdk.defination.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccessTokenResponse {
    @SerializedName("accessToken")
    @Expose
    private var accessToken: String = ""


    fun getAccessToken(): String {
        return accessToken
    }

    fun setAccessToken(token: String) {
        this.accessToken = token
    }

}