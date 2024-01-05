package com.example.cf_sdk.changebankapi.usecase

import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.usecase.ExecutionThread
import com.example.cf_sdk.changebankapi.usecase.UseCase
import com.example.cf_sdk.changebankapi.util.ChangebankError

import io.reactivex.Single
import io.reactivex.SingleObserver
import org.xml.sax.ErrorHandler
import retrofit2.Retrofit


/**
 *
 * Use case to be used when there is only one result expected.
 */
abstract class SingleUseCase<P : Parameters?, RESULT>(
    private val mBackgroundExecutionThread: ExecutionThread,
    protected var mUiExecutionThread: ExecutionThread,
    protected var mErrorHandler: ChangebankError<*>
) : UseCase<P, Single<RESULT>, SingleObserver<RESULT>>() {

    abstract override fun buildUseCaseObservable(parameters: P): Single<RESULT>

    override fun execute(observer: SingleObserver<RESULT>, parameters: P) {
        buildUseCaseObservable(parameters)
            .subscribeOn(mBackgroundExecutionThread.scheduler)
            .observeOn(mUiExecutionThread.scheduler)
            ?.subscribe(observer)
    }
}