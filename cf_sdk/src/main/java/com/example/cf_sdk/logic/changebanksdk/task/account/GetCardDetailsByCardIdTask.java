package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.Card;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 *
 * Use case for get card ddetails from their ID.
*/
class GetCardDetailsByCardIdTask extends SingleUseCase<CardParameters, Card> {
    private final AuthenticationDatasource mAuthenticationRepository;

    private final AccountDatasource mAccountRepository;

    @Inject
    GetCardDetailsByCardIdTask(
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("repository")AccountDatasource accountRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
        mAccountRepository = accountRepository;
    }

    @Override
    protected Single<Card> buildUseCaseObservable(CardParameters parameters) {
        return Single.zip(Single.just(parameters), mAuthenticationRepository.getSession(),
                new BiFunction<CardParameters, Session, Single<Optional<Card>>>() {
                    @Override
                    public Single<Optional<Card>> apply(CardParameters cardParameters, Session session) throws Exception {
                        cardParameters.addToken(session.getToken());
                        return mAccountRepository.getCardByCardId(cardParameters);
                    }
                })
                .flatMap(new Function<Single<Optional<Card>>, SingleSource<? extends Optional<Card>>>() {
                    @Override
                    public SingleSource<? extends Optional<Card>> apply(Single<Optional<Card>> cardSingle) throws Exception {
                        return cardSingle;
                    }
                })
                .flatMapMaybe(new Function<Optional<Card>, MaybeSource<Card>>() {
                    @Override
                    public MaybeSource<Card> apply(Optional<Card> cardOptional) throws Exception {
                        if (cardOptional.isPresent()) {
                            return Maybe.just(cardOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
