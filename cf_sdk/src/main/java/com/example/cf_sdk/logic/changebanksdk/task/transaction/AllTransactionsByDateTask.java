package com.example.cf_sdk.logic.changebanksdk.task.transaction;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.RecentTransactionParameter;
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
 * Task to get all transactions completed and pending by provided date.
 */

public class AllTransactionsByDateTask extends SingleUseCase<TransactionsByDateParameters, TransactionResponse> {
    private AuthenticationDatasource mAuthenticationRepository;
    private TransactionDatasource mTransactionRepository;

    @Inject
    AllTransactionsByDateTask(
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
                        updatedTransactionsByDateParameters.addToken(session.getTokenType()+" "+session.getToken());
                        RecentTransactionParameter transactionByStatusParameters = new RecentTransactionParameter(parameters.getHeaders());
                        transactionByStatusParameters.addToken(session.getTokenType()+" "+session.getToken());
                        RecentTransactionParameter recentTransactionParameter = RecentTransactionParameter.create(parameters.getCardToken(),parameters.getFromDate(),parameters.getToDate());
                        recentTransactionParameter.addToken(session.getTokenType()+" "+session.getToken());
                       /* if (parameters.getPage() == 1 && parameters.isToDateCurrentMonth()) {
                            // temporary commented for setting up transaction list on dashboard
                            return null;*//*Single.zip(
                                    mTransactionRepository.getTransactionsByDate(updatedTransactionsByDateParameters),
                                    mTransactionRepository.getTransactionsByStatusFilter(transactionByStatusParameters),
                                    new BiFunction<List<Transaction>, List<Transaction>, List<Transaction>>() {
                                        @Override
                                        public List<Transaction> apply(List<Transaction> transactionsCompleted, List<Transaction> transactionsPending) throws Exception {
                                            if (transactionsPending != null && !transactionsPending.isEmpty()) {
                                                List<Transaction> newList = new ArrayList<>(transactionsPending);
                                                newList.addAll(transactionsCompleted);
                                                return newList;
                                            }

                                            return transactionsCompleted;
                                        }
                                    });*//*
                        } else {*/
                            return mTransactionRepository.getTransactionsByStatusFilter(recentTransactionParameter);
//                        }
                    }
                });
    }
}
