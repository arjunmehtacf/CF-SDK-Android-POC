package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.response.BanksApiResponse;
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
 * Retrieves banks available
 */

public class GetBanksTask extends SingleUseCase<Parameters, BanksApiResponse> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetBanksTask(@Named("repository") AccountDatasource accountDatasource,
                        @Named("repository") AuthenticationDatasource authenticationDatasource,
                        @Named("io") ExecutionThread backgroundExecutionThread,
                        @Named("ui") ExecutionThread uiExecutionThread,
                        @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<BanksApiResponse> buildUseCaseObservable(final Parameters parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<BanksApiResponse>>>() {
                    @Override
                    public SingleSource<? extends Optional<BanksApiResponse>> apply(Session session) throws Exception {
                        parameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.getBanks(parameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<BanksApiResponse>, MaybeSource<BanksApiResponse>>() {
                    @Override
                    public MaybeSource<BanksApiResponse> apply(Optional<BanksApiResponse> banksApiResponseOptional) throws Exception {
                        if (banksApiResponseOptional.isPresent()) {
                            return Maybe.just(banksApiResponseOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
