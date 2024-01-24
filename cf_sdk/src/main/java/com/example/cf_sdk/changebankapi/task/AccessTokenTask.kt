package com.example.cf_sdk.changebankapi.task

import com.example.cf_sdk.changebankapi.ErrorHandler
import com.example.cf_sdk.changebankapi.log.LogManager
import com.example.cf_sdk.changebankapi.network.RetrofitFactory
import com.example.cf_sdk.changebankapi.network.api.AuthenticationApiCreator
import com.example.cf_sdk.changebankapi.network.api.MemberApiCreator
import com.example.cf_sdk.changebankapi.source.AuthenticationRepository
import com.example.cf_sdk.changebankapi.source.MemberRepository
import com.example.cf_sdk.changebankapi.source.cache.MemberCacheDatasource
import com.example.cf_sdk.changebankapi.source.remote.AuthenticationDatasource
import com.example.cf_sdk.changebankapi.source.remote.AuthenticationRemoteDatasource
import com.example.cf_sdk.changebankapi.source.remote.MemberDatasource
import com.example.cf_sdk.changebankapi.source.remote.MemberRemoteDatasource
import com.example.cf_sdk.changebankapi.usecase.ExecutionThread
import com.example.cf_sdk.changebankapi.usecase.IOThread
import com.example.cf_sdk.changebankapi.usecase.SingleUseCase
import com.example.cf_sdk.changebankapi.usecase.UIThread
import com.example.cf_sdk.changebankapi.util.ChangebankError
import com.example.cf_sdk.defination.request.AccessTokenParameter
import com.example.cf_sdk.defination.response.Session
import io.reactivex.Single
import java.io.File
import java.util.function.Consumer

class AccessTokenTask : SingleUseCase<AccessTokenParameter?, Session?>(
    createBackgroundExecutionThread(), createUiExecutionThread(), createErrorHandler()
) {
    private var mAuthenticationRepository: AuthenticationDatasource? = null

    init {
        initializeDependencies()
    }

    private fun initializeDependencies() {
        mAuthenticationRepository = AuthenticationRepository(
            AuthenticationRemoteDatasource(
                AuthenticationApiCreator().create(LogManager().logger,RetrofitFactory().getRetrofit())!!),
            AuthenticationRemoteDatasource(AuthenticationApiCreator().create(LogManager().logger,RetrofitFactory().getRetrofit())!!),
            AuthenticationRemoteDatasource(AuthenticationApiCreator().create(LogManager().logger,RetrofitFactory().getRetrofit())!!)
        )
    }

    override fun buildUseCaseObservable(parameters: AccessTokenParameter?): Single<Session?> {
        return mAuthenticationRepository?.getAccessToken(parameters)!!
    }

    companion object {
        private fun createBackgroundExecutionThread(): ExecutionThread {
            return IOThread()
        }

        private fun createUiExecutionThread(): ExecutionThread {
            return UIThread()
        }

        private fun createErrorHandler(): ChangebankError<*> {
            return ErrorHandler<Any?>(RetrofitFactory().getRetrofit())
        }
    }
}