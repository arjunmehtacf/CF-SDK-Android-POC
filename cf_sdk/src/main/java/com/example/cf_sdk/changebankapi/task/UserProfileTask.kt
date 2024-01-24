package com.example.cf_sdk.changebankapi.task

import com.example.cf_sdk.changebankapi.ErrorHandler
import com.example.cf_sdk.changebankapi.log.LogManager
import com.example.cf_sdk.defination.response.UserProfileResponse
import com.example.cf_sdk.changebankapi.network.RetrofitFactory
import com.example.cf_sdk.changebankapi.network.api.ApiConfig
import com.example.cf_sdk.changebankapi.network.api.AuthenticationApiCreator
import com.example.cf_sdk.changebankapi.network.api.MemberApiCreator
import com.example.cf_sdk.defination.request.UserProfileParameter
import com.example.cf_sdk.changebankapi.source.AuthenticationRepository
import com.example.cf_sdk.changebankapi.source.MemberRepository
import com.example.cf_sdk.changebankapi.source.cache.AuthenticationCacheDatasource
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
import com.example.cf_sdk.defination.CFSDKConstant
import com.example.cf_sdk.defination.response.Session
import io.reactivex.Single
import retrofit2.Retrofit
import java.io.File

class UserProfileTask(val session: Session) :
    SingleUseCase<UserProfileParameter, UserProfileResponse?>(
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

    companion object {
        private fun createCacheDirectoryInstance(): File {
            // Implement the creation of cache directory instance
            // Example: return new File("your_cache_directory_path");
            return File("com.example.sdk_no_dagger") // Replace with your implementation
        }

        private fun createBackgroundExecutionThread(): ExecutionThread {
            return IOThread()
        }

        private fun createUiExecutionThread(): ExecutionThread {
            return UIThread()
        }

        private fun createErrorHandler(): ChangebankError<*> {
            val retrofit =
                Retrofit.Builder().baseUrl(ApiConfig.AUTHENTICATION_BASE_ENDPOINT).build()
            return ErrorHandler<Any?>(retrofit)
        }
    }

    override fun buildUseCaseObservable(parameters: UserProfileParameter): Single<UserProfileResponse?> {
        parameters.addToken(CFSDKConstant.TOEKN_TYPE+ " " + session?.token)
        return mMemberRepository?.getUserProfile(parameters)!!
    }
}