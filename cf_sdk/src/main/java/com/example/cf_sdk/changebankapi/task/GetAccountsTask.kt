package com.example.cf_sdk.changebankapi.task

import com.example.cf_sdk.changebankapi.ErrorHandler
import com.example.cf_sdk.changebankapi.log.LogManager
import com.example.cf_sdk.changebankapi.model.Header
import com.example.cf_sdk.changebankapi.network.RetrofitFactory
import com.example.cf_sdk.changebankapi.network.api.AccountApiCreator
import com.example.cf_sdk.changebankapi.network.api.ApiConfig
import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.parameter.StringParameters
import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.changebankapi.source.AccountRepository
import com.example.cf_sdk.changebankapi.source.cache.AccountCacheDatasource
import com.example.cf_sdk.changebankapi.source.datasource.AccountDatasource
import com.example.cf_sdk.changebankapi.source.remote.AccountRemoteDatasource
import com.example.cf_sdk.changebankapi.source.remote.AuthenticationDatasource
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


class GetAccountsTask (val session: Session) :
    SingleUseCase<Parameters, AccountsApiResponse?>(
        createBackgroundExecutionThread(), createUiExecutionThread(), createErrorHandler()
    ) {
    private var mAccountRepository: AccountDatasource? = null
    private var mAuthenticationRepository: AuthenticationDatasource? = null

    init {
        initializeDependencies()
    }

    private fun initializeDependencies() {
        // Manually create instances of your classes here
        val cacheDirectory = createCacheDirectoryInstance()
        mAccountRepository = AccountRepository(AccountRemoteDatasource(AccountApiCreator().create(LogManager().logger,RetrofitFactory().getRetrofit()),cacheDirectory),AccountRemoteDatasource(AccountApiCreator().create(LogManager().logger,RetrofitFactory().getRetrofit()),cacheDirectory),AccountCacheDatasource())
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

    override fun buildUseCaseObservable(parameters: Parameters): Single<AccountsApiResponse?> {
        val stringParameters: StringParameters = StringParameters.create("001000001261")
        stringParameters.addHeader(Header.TOKEN, CFSDKConstant.TOEKN_TYPE + " " + session.token)
        return mAccountRepository?.getMemberAccounts(stringParameters)!!
    }
}