package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.DirectDepositParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to get member direct deposit form for a particular account.
 */

public class GetDirectDepositFormTask extends SingleUseCase<DirectDepositParameters, File> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetDirectDepositFormTask(@Named("repository") AccountDatasource accountDatasource,
                                    @Named("repository") AuthenticationDatasource authenticationDatasource,
                                    @Named("io") ExecutionThread backgroundExecutionThread,
                                    @Named("ui") ExecutionThread uiExecutionThread,
                                    @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<File> buildUseCaseObservable(final DirectDepositParameters directDepositParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<File>>>() {
                    @Override
                    public SingleSource<? extends Optional<File>> apply(Session session) throws Exception {
                        directDepositParameters.addToken(session.getTokenType()+" "+session.getToken());
                        return mAccountDatasource.getDirectDepositForm(directDepositParameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<File>, MaybeSource<File>>() {
                    @Override
                    public MaybeSource<File> apply(Optional<File> fileOptional) throws Exception {
                        if (fileOptional.isPresent()) {
                            return Maybe.just(fileOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
