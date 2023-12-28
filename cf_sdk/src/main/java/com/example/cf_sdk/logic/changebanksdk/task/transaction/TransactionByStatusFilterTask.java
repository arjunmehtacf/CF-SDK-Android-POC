package com.example.cf_sdk.logic.changebanksdk.task.transaction;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.RecentTransactionParameter;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.TransactionResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to get transactions by status filter.
 */

public class TransactionByStatusFilterTask extends SingleUseCase<RecentTransactionParameter, TransactionResponse> {
    private AuthenticationDatasource mAuthenticationRepository;
    private TransactionDatasource mTransactionRepository;

    @Inject
    TransactionByStatusFilterTask(
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("repository") TransactionDatasource transactionRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationRepository = authenticationRepository;
        mTransactionRepository = transactionRepository;
    }

    @Override
    protected Single<TransactionResponse> buildUseCaseObservable(
            final RecentTransactionParameter parameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends TransactionResponse>>() {
                    @Override
                    public SingleSource<? extends TransactionResponse> apply(Session session) throws Exception {
                        parameters.addToken(session.getTokenType()+" "+session.getToken());
                        return mTransactionRepository.getTransactionsByStatusFilter(parameters);
                    }
                });
    }
}
