package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 *
 * Task to get Saved Username
 */

public class GetSavedUsernameTask extends SingleUseCase<Parameters, String> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public GetSavedUsernameTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                @Named("io") ExecutionThread backgroundExecutionThread,
                                @Named("ui") ExecutionThread uiExecutionThread,
                                @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<String> buildUseCaseObservable(Parameters parameters) {
        return mAuthenticationRepository.getSavedUsername()
                .flatMapMaybe(new Function<Optional<String>, MaybeSource<String>>() {
                    @Override
                    public MaybeSource<String> apply(Optional<String> stringOptional) throws Exception {
                        if (stringOptional.isPresent()) {
                            return Maybe.just(stringOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }

}
