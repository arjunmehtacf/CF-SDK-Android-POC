package com.example.cf_sdk.logic.changebanksdk.task.ingo;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.ingo.VerifyIngoSSNParameters;
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
 * Use case for verify user ssn for ingo.
 */

public class VerifyIngoSSNTask extends SingleUseCase<VerifyIngoSSNParameters, IngoResponse> {

    private final AuthenticationDatasource mAuthenticationRepository;
    private final IngoDatasource mIngoDatasource;

    @Inject
    public VerifyIngoSSNTask(
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
    protected Single<IngoResponse> buildUseCaseObservable(final VerifyIngoSSNParameters verifyIngoSSNParameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends IngoResponse>>() {
                    @Override
                    public SingleSource<? extends IngoResponse> apply(Session session) throws Exception {
                        verifyIngoSSNParameters.addHeader(Header.TOKEN, session.getToken());
                        return mIngoDatasource.verifyIngoSSSN(verifyIngoSSNParameters);
                    }
                });
    }
}
