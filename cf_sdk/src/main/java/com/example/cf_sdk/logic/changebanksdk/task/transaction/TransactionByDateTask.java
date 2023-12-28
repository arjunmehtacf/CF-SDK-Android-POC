package com.example.cf_sdk.logic.changebanksdk.task.transaction;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.TransactionResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionsByDateParameters;
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
 * Task to get transactions by provided date.
 */

public class TransactionByDateTask extends SingleUseCase<TransactionsByDateParameters, TransactionResponse> {
    private AuthenticationDatasource mAuthenticationRepository;
    private TransactionDatasource mTransactionRepository;

    @Inject
    TransactionByDateTask(
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
            final TransactionsByDateParameters parameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends TransactionResponse>>() {
                    @Override
                    public SingleSource<? extends TransactionResponse> apply(Session session) throws Exception {
                        TransactionsByDateParameters updatedTransactionsByDateParameters = parameters.withMemberId(session.getMemberId());
                        updatedTransactionsByDateParameters.addToken(session.getToken());
                        return mTransactionRepository.getTransactionsByDate(updatedTransactionsByDateParameters);
                    }
                });
    }
}
