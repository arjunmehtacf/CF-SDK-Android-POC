package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.ObservableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UsernameParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.sanitization.UsernameSanitizer;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.UsernameValidator;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 *
 * Use case for user login scenarios.
 */

public class CheckUsernameExistsTask extends ObservableUseCase<UsernameParameters, ChangebankResponse> {

    private final ExecutionThread mBackgroundExecutionThread;
    private final UsernameSanitizer mUsernameSanitizer;
    private final UsernameValidator mUsernameValidator;
    private AuthenticationDatasource mAuthenticationRepository;
    private final PublishSubject<UsernameParameters> mSubject;


    @Inject
    public CheckUsernameExistsTask(
            UsernameSanitizer usernameSanitizer,
            UsernameValidator usernameValidator,
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mUsernameValidator = usernameValidator;
        mUsernameSanitizer = usernameSanitizer;
        mBackgroundExecutionThread = backgroundExecutionThread;
        mAuthenticationRepository = authenticationRepository;
        mSubject = PublishSubject.create();
    }


    @Override
    public void execute(Observer<ChangebankResponse> changebankResponseObserver,
                        UsernameParameters parameters) {
        if (!mSubject.hasObservers()) {
            buildUseCaseObservable(parameters)
                    .observeOn(mUiExecutionThread.getScheduler())
                    .compose(mErrorHandler)
                    .subscribeWith(changebankResponseObserver);
        }

        mSubject.onNext(parameters);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Observable<ChangebankResponse> buildUseCaseObservable(final UsernameParameters parameters) {
        return mSubject.debounce(500, TimeUnit.MILLISECONDS, mBackgroundExecutionThread.getScheduler())
                .flatMapSingle(new Function<UsernameParameters, SingleSource<UsernameParameters>>() {
                    @Override
                    public SingleSource<UsernameParameters> apply(UsernameParameters usernameParameters) throws Exception {
                        return mUsernameSanitizer.sanitize(usernameParameters);
                    }
                })
                .flatMapSingle(new Function<UsernameParameters, SingleSource<UsernameParameters>>() {
                    @Override
                    public SingleSource<UsernameParameters> apply(UsernameParameters usernameParameters) throws Exception {
                        return mUsernameValidator.validate(usernameParameters);
                    }
                })
                .switchMapSingle(new Function<UsernameParameters, SingleSource<ChangebankResponse>>() {
                    @Override
                    public SingleSource<ChangebankResponse> apply(UsernameParameters usernameParameters) throws Exception {
                        return mAuthenticationRepository.checkUsernameExists(usernameParameters)
                                .subscribeOn(mBackgroundExecutionThread.getScheduler());
                    }
                });
    }
}
