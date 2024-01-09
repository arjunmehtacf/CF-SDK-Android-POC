package com.example.sdk_no_dagger.changebankapi.sdkcore

data class CFSDKErrorResponse(
    val dateTime: String,
    val errorNumber: Int,
    val errorMessage: String
)