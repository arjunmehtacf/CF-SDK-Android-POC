package com.example.cf_sdk.defination

import com.example.cf_sdk.defination.response.ChangebankResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

object CFSDKErrorHandler {
    fun handleAPIError(error: Throwable): ChangebankResponse {
        val changebankResponse = ChangebankResponse()
        if (error is HttpException) {
            val errorBody = (error as HttpException).response()?.errorBody()?.string()
            val errorResponse: CFSDKErrorResponse =
                Gson().fromJson(errorBody, CFSDKErrorResponse::class.java)
            changebankResponse.setMessage(errorResponse.errorMessage)
            changebankResponse.httpCode = error.code()
        } else if (error is IOException) {
            changebankResponse.setMessage(error.message)
        } else {
            changebankResponse.setMessage("Something went wrong")
        }
        return changebankResponse
    }
}