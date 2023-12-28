package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 *
 * Use case for user logout scenarios.
 */

public class LogoutTask extends SingleUseCase<Parameters, ChangebankResponse> {

    private final MemberDatasource mMemberDatasource;
    private final AccountDatasource mAccountDatasource;
    private final ZendeskDatasource mZendeskDatasource;
    private AuthenticationDatasource mAuthenticationRepository;


    @Inject
    public LogoutTask(
            @Named("repository") MemberDatasource memberRepository,
            @Named("repository") AccountDatasource accountRepository,
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mMemberDatasource = memberRepository;
        mAccountDatasource = accountRepository;
        mAuthenticationRepository = authenticationRepository;
        mZendeskDatasource = zendeskDatasource;
    }

    @Override
    protected Single<ChangebankResponse> buildUseCaseObservable(final Parameters parameters) {
        mMemberDatasource.clearAllMemberDatasourceCache();
        mAccountDatasource.clearAllAccountDatasourceCache();

        mZendeskDatasource.clearZendeskCache();
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, Single<ChangebankResponse>>() {
                    @Override
                    public Single<ChangebankResponse> apply(Session session) throws Exception {
                        parameters.addToken(session.getTokenType() + " " + session.getToken());
                        mAuthenticationRepository.clearAllAuthenticationDatasourceCache();
                        return mAuthenticationRepository.logout(parameters);
                    }
                });



    }
}
