package com.example.cf_sdk.logic.changebanksdk.task.transaction;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.exception.InvalidTransactionException;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.model.account.Card;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.Transaction;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.GetAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionByIdParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 *
 * Retrieves a transaction by id.
 */

public class TransactionByIdTask extends SingleUseCase<TransactionByIdParameters, Transaction> {
    private final AuthenticationDatasource mAuthenticationRepository;
    private final TransactionDatasource mTransactionRepository;
    private final AccountDatasource mAccountRepository;
    private Session mSession;

    @Inject
    TransactionByIdTask(
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("repository") TransactionDatasource transactionDatasource,
            @Named("repository") AccountDatasource accountRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
        mTransactionRepository = transactionDatasource;
        mAccountRepository = accountRepository;
    }

    @Override
    protected Single<Transaction> buildUseCaseObservable(TransactionByIdParameters parameters) {
        return Single.zip(Single.just(parameters), mAuthenticationRepository.getSession(),
                new BiFunction<TransactionByIdParameters, Session, Single<Transaction>>() {
                    @Override
                    public Single<Transaction> apply(
                            TransactionByIdParameters transactionByIdParameters,
                            Session sessionResponse) throws Exception {
                        mSession = sessionResponse;
                        transactionByIdParameters.addToken(mSession.getToken());
                        return mTransactionRepository.getTransactionById(
                                transactionByIdParameters.withMemberId(mSession.getMemberId()));
                    }
                })
                .flatMap(new Function<Single<Transaction>, SingleSource<? extends Transaction>>() {
                    @Override
                    public SingleSource<? extends Transaction> apply(
                            Single<Transaction> transactionSingle) throws Exception {
                        return transactionSingle;
                    }
                })
                .flatMap(new Function<Transaction, SingleSource<? extends Transaction>>() {
                    @Override
                    public SingleSource<? extends Transaction> apply(Transaction transaction) throws Exception {
                        CardParameters cardParameters = CardParameters.create(transaction.getCardRefId());
                        cardParameters.addToken(mSession.getToken());

                        Single<Optional<Card>> cardSingle = mAccountRepository
                                .getCard(cardParameters)
                                .onErrorReturn(new Function<Throwable, Optional<Card>>() {
                                    @Override
                                    public Optional<Card> apply(Throwable throwable) throws Exception {
                                        return Optional.absent();
                                    }
                                });

                        return Single.zip(
                                Single.just(transaction),
                                cardSingle,
                                new BiFunction<Transaction, Optional<Card>, Transaction>() {
                                    @Override
                                    public Transaction apply(Transaction transaction, Optional<Card> card) throws Exception {
                                        return transaction.withCard(card.orNull());
                                    }
                                });
                    }
                })
                .flatMap(new Function<Transaction, SingleSource<? extends Transaction>>() {
                    @Override
                    public SingleSource<? extends Transaction> apply(Transaction transaction) throws Exception {

                        GetAccountParameters accountParameters = GetAccountParameters.createById(transaction.getAccountId());
                        accountParameters.addToken(mSession.getToken());

                        Single<Optional<Account>> accountSingle = mAccountRepository
                                .getAccountById(accountParameters)
                                .onErrorReturn(new Function<Throwable, Optional<Account>>() {
                                    @Override
                                    public Optional<Account> apply(Throwable throwable) throws Exception {
                                        return Optional.absent();
                                    }
                                });

                        return Single.zip(
                                Single.just(transaction),
                                accountSingle,
                                new BiFunction<Transaction, Optional<Account>, Transaction>() {
                                    @Override
                                    public Transaction apply(Transaction transaction, Optional<Account> account) throws Exception {
                                        return transaction.withAccount(account.orNull());
                                    }
                                });
                    }
                })
                .filter(new Predicate<Transaction>() {
                    @Override
                    public boolean test(Transaction transaction) throws Exception {
                        return transaction != null;
                    }
                })
                .switchIfEmpty(Single.<Transaction>error(new InvalidTransactionException()));
    }
}
