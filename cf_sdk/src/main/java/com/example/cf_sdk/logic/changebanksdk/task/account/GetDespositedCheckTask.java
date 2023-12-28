package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.DepositedCheck;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CheckDepositedParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
* Use case for Get Deposit Check Data.
*/

public class GetDespositedCheckTask extends SingleUseCase<CheckDepositedParameters, List<DepositedCheck>> {
    private AuthenticationDatasource mAuthenticationRepository;
    private AccountDatasource mAccountDatasource;

    @Inject
    public GetDespositedCheckTask(
            @Named("repository") AccountDatasource accountDatasource,
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationRepository = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<List<DepositedCheck>> buildUseCaseObservable(
            final CheckDepositedParameters parameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends List<DepositedCheck>>>() {
                    @Override
                    public SingleSource<? extends List<DepositedCheck>> apply(Session session) throws Exception {
                        CheckDepositedParameters updatedTransactionsByDateParameters = parameters.withMemberId(session.getMemberId());
                        updatedTransactionsByDateParameters.addToken(session.getToken());
                        return mAccountDatasource.getDepositedCheck(updatedTransactionsByDateParameters);
                    }
                });
    }

}
