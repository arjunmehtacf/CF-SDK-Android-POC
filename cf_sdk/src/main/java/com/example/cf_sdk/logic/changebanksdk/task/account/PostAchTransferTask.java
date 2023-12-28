package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AchTransferResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.AchTransferValidator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/*
 *
 * Creates an achTransfer
 */

public class PostAchTransferTask extends SingleUseCase<AchTransferParameters, AchTransferResponse> {

    private final AchTransferValidator mAchTransferValidator;
    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public PostAchTransferTask(
            AchTransferValidator achTransferValidator,
            @Named("repository") AccountDatasource accountDatasource,
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
        mAchTransferValidator = achTransferValidator;
    }

    @Override
    protected Single<AchTransferResponse> buildUseCaseObservable(final AchTransferParameters parameters) {
        return mAchTransferValidator.validate(parameters)
                .flatMap(new Function<AchTransferParameters, SingleSource<Session>>() {
                    @Override
                    public SingleSource<Session> apply(AchTransferParameters achTransferParameters) throws Exception {
                        return mAuthenticationDatasource.getSession();
                    }
                })
                .flatMap(new Function<Session, SingleSource<? extends AchTransferResponse>>() {
                    @Override
                    public SingleSource<? extends AchTransferResponse> apply(Session session) throws Exception {
                        AchTransferParameters newParameters = parameters.withMemberId(session.getMemberId())
                                .withAccountId(parameters.getAccount().getId())
                                .withAchAccountId(parameters.getAchAccount().getId());
                        newParameters.addToken(session.getTokenType() + " " + session.getToken());
                        return mAccountDatasource.postAchTransfer(newParameters);
                    }
                });
    }
}
