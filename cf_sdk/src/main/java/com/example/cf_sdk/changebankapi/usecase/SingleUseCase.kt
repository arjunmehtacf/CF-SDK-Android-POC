package com.example.sdk_no_dagger.changebankapi.usecase

import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.usecase.ExecutionThread
import com.example.cf_sdk.changebankapi.usecase.UseCase
import com.example.sdk_no_dagger.changebankapi.util.ChangebankError
import io.reactivex.Single
import io.reactivex.SingleObserver
import org.xml.sax.ErrorHandler
import retrofit2.Retrofit


/**
 * Created by victorojeda on 5/29/18.
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