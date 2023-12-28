package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.exception.UsernameNotFoundException;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.FingerprintResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to read Fingerprint for cached/logged in user.
 */

public class ReadCacheUserFingerprintTask extends SingleUseCase<Parameters, FingerprintResponse> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public ReadCacheUserFingerprintTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                        @Named("io") ExecutionThread backgroundExecutionThread,
                                        @Named("ui") ExecutionThread uiExecutionThread,
                                        @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<FingerprintResponse> buildUseCaseObservable(Parameters parameters) {
        return mAuthenticationRepository.canStoreFingerprintSecurely()
                .flatMap(new Function<Boolean, SingleSource<? extends FingerprintResponse>>() {
                    @Override
                    public SingleSource<? extends FingerprintResponse> apply(Boolean canStore) throws Exception {
                        if (canStore) {
                            return mAuthenticationRepository.getCachedUsername()
                                    .flatMap(new Function<Optional<String>, SingleSource<? extends FingerprintResponse>>() {
                                        @Override
                                        public SingleSource<? extends FingerprintResponse> apply(Optional<String> username) throws Exception {
                                            if (username.isPresent()) {
                                                return mAuthenticationRepository.readFingerprintInstant(username.get());
                                            } else {
                                                return Single.error(new UsernameNotFoundException());
                                            }
                                        }
                                    });
                        } else {
                            return Single.error(new UnsupportedOperationException());
                        }
                    }
                });
    }


}
