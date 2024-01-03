package com.example.sdk_no_dagger.changebankapi.task


import com.example.cf_sdk.changebankapi.log.LogManager
import com.example.cf_sdk.changebankapi.model.member.version.VersionConfig
import com.example.cf_sdk.changebankapi.network.api.ApiConfig
import com.example.cf_sdk.changebankapi.parameter.member.SettingsParameter
import com.example.cf_sdk.changebankapi.source.cache.MemberCacheDatasource
import com.example.cf_sdk.changebankapi.usecase.ExecutionThread
import com.example.cf_sdk.changebankapi.usecase.IOThread
import com.example.cf_sdk.changebankapi.usecase.UIThread
import com.example.sdk_no_dagger.changebankapi.ErrorHandler
import com.example.sdk_no_dagger.changebankapi.network.RetrofitFactory
import com.example.sdk_no_dagger.changebankapi.network.api.MemberApiCreator
import com.example.sdk_no_dagger.changebankapi.source.MemberRepository
import com.example.sdk_no_dagger.changebankapi.source.remote.MemberDatasource
import com.example.sdk_no_dagger.changebankapi.source.remote.MemberRemoteDatasource
import com.example.sdk_no_dagger.changebankapi.usecase.SingleUseCase
import com.example.sdk_no_dagger.changebankapi.util.ChangebankError
import io.reactivex.Single
import retrofit2.Retrofit
import java.io.File

/**
 *
 * Task for version config.
 */
class VersionConfigTask : SingleUseCase<SettingsParameter?, VersionConfig?>(
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

    override fun buildUseCaseObservable(parameters: SettingsParameter?): Single<VersionConfig?> {
        return mMemberRepository?.getVersionConfig(parameters)!!
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
}