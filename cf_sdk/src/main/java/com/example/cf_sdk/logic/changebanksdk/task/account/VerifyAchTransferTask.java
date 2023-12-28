package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransfer;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.VerifyAchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Verifies TwoFactor code for AchTransfer.
 */

public class VerifyAchTransferTask extends SingleUseCase<VerifyAchTransferParameters, AchTransfer> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public VerifyAchTransferTask(@Named("repository") AccountDatasource accountDatasource,
                                 @Named("repository") AuthenticationDatasource authenticationDatasource,
                                 @Named("io") ExecutionThread backgroundExecutionThread,
                                 @Named("ui") ExecutionThread uiExecutionThread,
                                 @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<AchTransfer> buildUseCaseObservable(final VerifyAchTransferParameters verifyAchTransferParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends AchTransfer>>() {
                    @Override
                    public SingleSource<? extends AchTransfer> apply(Session session) throws Exception {
                        VerifyAchTransferParameters newParameters = verifyAchTransferParameters.withMemberId(session.getMemberId());
                        newParameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.verifyAchTransfer(newParameters);
                    }
                });
    }
}
