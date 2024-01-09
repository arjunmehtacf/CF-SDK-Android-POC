package com.example.cf_sdk.defination

data class CFSDKErrorResponse(
    val dateTime: String,
    val errorNumber: Int,
    val errorMessage: String
)