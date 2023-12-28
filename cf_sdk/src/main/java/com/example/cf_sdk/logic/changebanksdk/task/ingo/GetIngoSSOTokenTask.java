package com.example.cf_sdk.logic.changebanksdk.task.ingo;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.response.IngoResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.IngoDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;


/**
 *
 * Use case for User get Ingo SSO token.
 */

public class GetIngoSSOTokenTask extends SingleUseCase<StringParameters, IngoResponse> {

    private final AuthenticationDatasource mAuthenticationRepository;
    private final IngoDatasource mIngoDatasource;

    @Inject
    public GetIngoSSOTokenTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") IngoDatasource ingoDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationDatasource;
        mIngoDatasource = ingoDatasource;
    }

    @Override
    protected Single<IngoResponse> buildUseCaseObservable(final StringParameters stringParameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends IngoResponse>>() {
                    @Override
                    public SingleSource<? extends IngoResponse> apply(Session session) throws Exception {
                        stringParameters.addHeader(Header.TOKEN, session.getToken());
                        return mIngoDatasource.getIngoSSOToken(stringParameters);
                    }
                });
    }
}
