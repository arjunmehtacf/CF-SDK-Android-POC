package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.response.BanksApiResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 *
 * Task to retrieve banks based on input text.
 */

public class SearchBanksTask extends SingleUseCase<StringParameters, BanksApiResponse> {
    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public SearchBanksTask(@Named("repository") AccountDatasource accountDatasource,
                           @Named("repository") AuthenticationDatasource authenticationDatasource,
                           @Named("io") ExecutionThread backgroundExecutionThread,
                           @Named("ui") ExecutionThread uiExecutionThread,
                           @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }


    @Override
    public Single<BanksApiResponse> buildUseCaseObservable(final StringParameters parameters) {
        return mAuthenticationDatasource.getSession()
                .toObservable()
                .switchMapSingle(new Function<Session, SingleSource<BanksApiResponse>>() {
                    @Override
                    public SingleSource<BanksApiResponse> apply(Session session) throws Exception {
                        parameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.getBanks(parameters)
                                .flatMapMaybe(new Function<Optional<BanksApiResponse>, MaybeSource<BanksApiResponse>>() {
                                    @Override
                                    public MaybeSource<BanksApiResponse> apply(Optional<BanksApiResponse> banksApiResponseOptional) throws Exception {
                                        if (banksApiResponseOptional.isPresent()) {
                                            return Maybe.just(banksApiResponseOptional.get());
                                        } else {
                                            return Maybe.empty();
                                        }
                                    }
                                })
                                .toSingle();
                    }
                })
                .switchMap(new Function<BanksApiResponse, ObservableSource<Bank>>() {
                    @Override
                    public ObservableSource<Bank> apply(BanksApiResponse banksApiResponse) throws Exception {
                        return Observable.fromIterable(banksApiResponse.getBanks())
                                .filter(new Predicate<Bank>() {
                                    @Override
                                    public boolean test(Bank bank) throws Exception {
                                        return bank.getName()
                                                .toLowerCase()
                                                .contains(parameters
                                                        .getFirstString()
                                                        .toLowerCase(Locale.US)); //filtering
                                    }
                                });
                    }
                })
                .toList()
                .flatMap(new Function<List<Bank>, SingleSource<? extends BanksApiResponse>>() {
                    @Override
                    public SingleSource<? extends BanksApiResponse> apply(List<Bank> banks) throws Exception {
                        BanksApiResponse filteredBankApiResponse = new BanksApiResponse(banks);
                        return Single.just(filteredBankApiResponse);
                    }
                });
    }
}
