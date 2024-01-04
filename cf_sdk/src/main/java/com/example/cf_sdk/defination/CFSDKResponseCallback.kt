package com.example.cf_sdk.defination

interface CFSDKResponseCallback<T> {
    fun onSuccess(var1: T)
    fun onFailure(var1: Throwable?)
}