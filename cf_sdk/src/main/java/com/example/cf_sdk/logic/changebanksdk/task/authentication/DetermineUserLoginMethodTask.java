package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.CheckPasscodeEnabledParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for determining the best user login method based on if fingerprint and passcode are setup.
 */

public class DetermineUserLoginMethodTask extends SingleUseCase<StringParameters, LoginParameters.LoginMethodType> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public DetermineUserLoginMethodTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                        @Named("io") ExecutionThread backgroundExecutionThread,
                                        @Named("ui") ExecutionThread uiExecutionThread,
                                        @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<LoginParameters.LoginMethodType> buildUseCaseObservable(final StringParameters parameters) {

        return mAuthenticationRepository.isUserFingerprintSaved(parameters.getFirstString())
                .flatMap(new Function<Boolean, SingleSource<? extends LoginParameters.LoginMethodType>>() {
                    @Override
                    public SingleSource<? extends LoginParameters.LoginMethodType> apply(Boolean aBoolean) throws Exception {

                        if (aBoolean) {
                            return Single.just(LoginParameters.LoginMethodType.FINGERPRINT_LOGIN);
                        } else {
                            CheckPasscodeEnabledParameters checkPasscodeEnabledParameters = CheckPasscodeEnabledParameters.CreateWithUsername(parameters.getFirstString());
                            return mAuthenticationRepository.checkPasscodeEnabled(checkPasscodeEnabledParameters)
                                    .flatMap(new Function<Boolean, SingleSource<? extends LoginParameters.LoginMethodType>>() {
                                        @Override
                                        public SingleSource<? extends LoginParameters.LoginMethodType> apply(Boolean aBoolean) throws Exception {
                                            if (aBoolean) {
                                                return Single.just(LoginParameters.LoginMethodType.PIN_LOGIN);
                                            }

                                            return Single.just(LoginParameters.LoginMethodType.PASSWORD_LOGIN);
                                        }
                                    });
                        }
                    }
                })
                .onErrorReturn(new Function<Throwable, LoginParameters.LoginMethodType>() {
                    @Override
                    public LoginParameters.LoginMethodType apply(Throwable throwable) throws Exception {
                        return LoginParameters.LoginMethodType.PASSWORD_LOGIN;
                    }
                });

    }
}
