package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.response.BankCredentialsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 *
 * Task for getting the MFA or New Bank Login Credentials.
 */

public class AchMfaOrNewBankCredentialsTask extends SingleUseCase<StringParameters, BankCredentialsApiResponse> {
    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public AchMfaOrNewBankCredentialsTask(@Named("repository") AccountDatasource accountDatasource,
                                          @Named("repository") AuthenticationDatasource authenticationDatasource,
                                          @Named("io") ExecutionThread backgroundExecutionThread,
                                          @Named("ui") ExecutionThread uiExecutionThread,
                                          @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<BankCredentialsApiResponse> buildUseCaseObservable(final StringParameters stringParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, Single<BankCredentialsApiResponse>>() {
                    @Override
                    public Single<BankCredentialsApiResponse> apply(Session session) throws Exception {
                        stringParameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.getMfaOrNewBankCredentials(stringParameters);
                    }
                });
    }
}
