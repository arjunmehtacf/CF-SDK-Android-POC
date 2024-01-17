package com.example.cf_sdk.changebankapi.task

import com.example.cf_sdk.changebankapi.ErrorHandler
import com.example.cf_sdk.changebankapi.log.LogManager
import com.example.cf_sdk.changebankapi.network.RetrofitFactory
import com.example.cf_sdk.changebankapi.network.api.MemberApiCreator
import com.example.cf_sdk.changebankapi.source.MemberRepository
import com.example.cf_sdk.changebankapi.source.cache.MemberCacheDatasource
import com.example.cf_sdk.changebankapi.source.remote.MemberDatasource
import com.example.cf_sdk.changebankapi.source.remote.MemberRemoteDatasource
import com.example.cf_sdk.changebankapi.usecase.ExecutionThread
import com.example.cf_sdk.changebankapi.usecase.IOThread
import com.example.cf_sdk.changebankapi.usecase.SingleUseCase
import com.example.cf_sdk.changebankapi.usecase.UIThread
import com.example.cf_sdk.changebankapi.util.ChangebankError
import com.example.cf_sdk.defination.request.AuthCodeParameter
import com.example.cf_sdk.defination.response.AuthCodeResponse
import io.reactivex.Single
import java.io.File

class AuthCodeTask : SingleUseCase<AuthCodeParameter?, AuthCodeResponse?>(
    createBackgroundExecutionThread(), createUiExecutionThread(), createErrorHandler()
) {
    private var mMemberRepository: MemberDatasource? = null

    init {
        initializeDependencies()
    }

    private fun initializeDependencies() {
        // Manually create instances of your classes here
        val cacheDirectory = createCacheDirectoryInstance()

        // Create instances of your data sources
        val memberRepository: MemberDatasource = MemberCacheDatasource()

        // Create an instance of MemberRepository
        mMemberRepository = MemberRepository(
            MemberRemoteDatasource(
                MemberApiCreator().create(LogManager().logger, RetrofitFactory().getRetrofit())!!,
                cacheDirectory
            ),
            (memberRepository as MemberCacheDatasource)
        )
    }

    override fun buildUseCaseObservable(parameters: AuthCodeParameter?): Single<AuthCodeResponse?> {
        return mMemberRepository?.getAuthCode(parameters)!!
    }

    companion object {
        private fun createCacheDirectoryInstance(): File {
            return File("com.example.sdk_no_dagger") // Replace with your implementation
        }

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