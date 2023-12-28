package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.SecurityProfile;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.GetSecurityProfileParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case to get security profile for member.
 */

public class GetSecurityProfileTask extends SingleUseCase<GetSecurityProfileParameters, SecurityProfile> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public GetSecurityProfileTask(
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<SecurityProfile> buildUseCaseObservable(final GetSecurityProfileParameters getSecurityProfileParameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends SecurityProfile>>() {
                    @Override
                    public SingleSource<? extends SecurityProfile> apply(Session session) throws Exception {
                        getSecurityProfileParameters.addToken(session.getToken());
                        getSecurityProfileParameters.setMemberId(session.getMemberId());
                        return mAuthenticationRepository.getSecurityProfile(getSecurityProfileParameters);
                    }
                });
    }
}
