package com.example.cf_sdk.changebankapi


import com.example.cf_sdk.changebankapi.exception.LoginTwoFactorTooManyAttemptsException
import com.example.cf_sdk.changebankapi.exception.UnauthorizedSessionException
import com.example.cf_sdk.changebankapi.network.api.ApiConfig
import com.example.cf_sdk.defination.response.ChangebankResponse
import com.example.cf_sdk.changebankapi.util.ChangebankError

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.net.HttpURLConnection

/**
 *
 * RxAndroid friendly ErrorHandler.
 */
class ErrorHandler<T>(private val mRetrofit: Retrofit?) : ChangebankError<T> {
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext(Function<Throwable, SingleSource<out T>> { throwable ->
            if (throwable is HttpException) {
                val body = throwable.response()!!.errorBody()
                try {
                    val converter = mRetrofit
                        ?.responseBodyConverter<ChangebankResponse>(
                            ChangebankResponse::class.java,
                            arrayOfNulls(0)
                        )
                    val error = converter?.convert(body)
                    error!!.httpCode = throwable.code()
                    val mappedError = mapErrorCode(error)
                    return@Function Single.error<T>(mappedError)
                } catch (e: IOException) {
                    val error =
                        ChangebankResponse()
                    error.httpCode = throwable.code()
                    error.setMessage(throwable.message)
                    return@Function Single.error<T>(error)
                } catch (e: IllegalArgumentException) {
                    val error =
                        ChangebankResponse()
                    error.httpCode = throwable.code()
                    error.setMessage(throwable.message)
                    return@Function Single.error<T>(error)
                }
            } else if (throwable is NoSuchElementException) {
                try {
                    val error =
                        ChangebankResponse()
                    error.httpCode = HttpURLConnection.HTTP_NO_CONTENT
                    error.setMessage(ApiConfig.RESPONSE_CODE_204)
                    return@Function Single.error<T>(error)
                } catch (e: Exception) {
                }
            }
            Single.error(throwable)
        })
    }

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.onErrorResumeNext(Function<Throwable, CompletableSource> { throwable ->
            if (throwable is HttpException) {
                val body = throwable.response()!!.errorBody()
                try {
                    val converter = mRetrofit
                        ?.responseBodyConverter<ChangebankResponse>(
                            ChangebankResponse::class.java,
                            arrayOfNulls(0)
                        )
                    val error = converter?.convert(body)
                    error!!.httpCode = throwable.code()
                    val mappedError = mapErrorCode(error)
                    return@Function Completable.error(mappedError)
                } catch (e: IOException) {
                    val error =
                        ChangebankResponse()
                    error.httpCode = throwable.code()
                    error.setMessage(throwable.message)
                    return@Function Completable.error(error)
                } catch (e: IllegalArgumentException) {
                    val error =
                        ChangebankResponse()
                    error.httpCode = throwable.code()
                    error.setMessage(throwable.message)
                    return@Function Completable.error(error)
                }
            }
            Completable.error(throwable)
        })
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.onErrorResumeNext(Function<Throwable, ObservableSource<out T>> { throwable ->
            if (throwable is HttpException) {
                val body = throwable.response()!!.errorBody()
                try {
                    val converter = mRetrofit?.responseBodyConverter<ChangebankResponse>(
                            ChangebankResponse::class.java,
                            arrayOfNulls(0)
                        )
                    val error = converter?.convert(body)
                    error!!.httpCode = throwable.code()
                    val mappedError = mapErrorCode(error)
                    return@Function Observable.error<T>(mappedError)
                } catch (e: IOException) {
                    val error =
                        ChangebankResponse()
                    error.httpCode = throwable.code()
                    error.setMessage(throwable.message)
                    return@Function Observable.error<T>(error)
                } catch (e: IllegalArgumentException) {
                    val error =
                        ChangebankResponse()
                    error.httpCode = throwable.code()
                    error.setMessage(throwable.message)
                    return@Function Observable.error<T>(error)
                }
            } else if (throwable is NoSuchElementException) {
                try {
                    val error =
                        ChangebankResponse()
                    error.httpCode = HttpURLConnection.HTTP_NO_CONTENT
                    error.setMessage(ApiConfig.RESPONSE_CODE_204)
                    return@Function Observable.error<T>(error)
                } catch (e: Exception) {
                }
            }
            Observable.error(throwable)
        })
    }

    private fun mapErrorCode(error: ChangebankResponse?): Throwable? {
        val mappedError: Throwable
        mappedError = when (error!!.key) {
            "authentication.code.failure" -> LoginTwoFactorTooManyAttemptsException(
                error.message
            )

            "UnAuthorized", "authentication.failure", "authentication.invalid.token" -> UnauthorizedSessionException()
            else -> error
        }
        return mappedError
    }
}