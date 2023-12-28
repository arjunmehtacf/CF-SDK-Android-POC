package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Delete Ach Account Task
 */

public class DeleteAchAccountTask extends SingleUseCase<StringParameters, AchAccount> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public DeleteAchAccountTask(@Named("repository") AccountDatasource accountDatasource,
                                @Named("repository") AuthenticationDatasource authenticationDatasource,
                                @Named("io") ExecutionThread backgroundExecutionThread,
                                @Named("ui") ExecutionThread uiExecutionThread,
                                @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<AchAccount> buildUseCaseObservable(final StringParameters stringParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<AchAccount>>>() {
                    @Override
                    public SingleSource<? extends Optional<AchAccount>> apply(Session session) throws Exception {
                        stringParameters.addToken(session.getTokenType() + " " + session.getToken());
                        return mAccountDatasource.deleteAchAccount(stringParameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<AchAccount>, MaybeSource<AchAccount>>() {
                    @Override
                    public MaybeSource<AchAccount> apply(Optional<AchAccount> achAccountOptional) throws Exception {
                        if (achAccountOptional.isPresent()) {
                            return Maybe.just(achAccountOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
