package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.exception.AccountNotFoundException;
import com.example.cf_sdk.logic.changebanksdk.exception.BanksNotFoundException;
import com.example.cf_sdk.logic.changebanksdk.exception.CardNotFoundException;
import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.model.account.AccountType;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccountStatus;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransfer;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.model.account.Card;
import com.example.cf_sdk.logic.changebanksdk.model.account.CardToCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.account.CreateNewSubCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.account.DepositedCheck;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchHistoryParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.BankParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardActivationParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CheckDepositedParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.ConfirmAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateMicroDepositAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateSubCardParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.DeleteAchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.DirectDepositParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.GetAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.SubmitMfaOrNewBankCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.UpdateCardStatusParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.VerifyAchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.VerifyMicroDepositAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AccountBalanceResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchAccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchHistoryResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchTransferResponse;
import com.example.cf_sdk.logic.changebanksdk.response.BankCredentialsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.BanksApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.google.common.base.Optional;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 *
 * Account repository is in charge of choosing the correct {@link AccountDatasource}
 */

public class AccountRepository implements AccountDatasource {
    private final AccountDatasource mLocalDataSource;
    private final AccountDatasource mRemoteDatasource;
    private final AccountDatasource mCacheDatasource;

    @Inject
    public AccountRepository(@Named("remote") AccountDatasource remoteDataSource,
                             @Named("local") AccountDatasource localDataSource,
                             @Named("cache") AccountDatasource cacheDataSource) {
        mRemoteDatasource = remoteDataSource;
        mLocalDataSource = localDataSource;
        mCacheDatasource = cacheDataSource;
    }

    @Override
    public Single<Optional<Account>> getAccountById(GetAccountParameters getAccountParameters) {
        Single<Optional<Account>> cache = Single.just(Optional.<Account>absent());

        if (!getAccountParameters.isFromRemoteOnly()) {
            cache = mCacheDatasource.getAccountById(getAccountParameters);
        }

        Single<Optional<Account>> remote = mRemoteDatasource.getAccountById(getAccountParameters)
                .doOnSuccess(new Consumer<Optional<Account>>() {
                    @Override
                    public void accept(Optional<Account> accountOptional) throws Exception {
                        if (accountOptional.isPresent()) {
                            saveAccount(accountOptional.get()).subscribe();
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<Account>>() {
                    @Override
                    public boolean test(Optional<Account> accountOptional) throws Exception {
                        return accountOptional.isPresent()
                                && accountOptional.get().getCardsList() != null
                                /*&& !accountOptional.get().getCardsList().isEmpty()*/;
                    }
                })
                .firstElement()
                // Return cache if neither cache or remote meets conditions.
                .switchIfEmpty(cache.flatMap(new Function<Optional<Account>, SingleSource<? extends Optional<Account>>>() {
                    @Override
                    public SingleSource<? extends Optional<Account>> apply(Optional<Account> accountOptional) throws Exception {
                        if (accountOptional.isPresent()) {
                            return Single.just(accountOptional);
                        } else {
                            return Single.error(new AccountNotFoundException());
                        }
                    }
                }));

    }

    @Override
    public Single<Optional<AccountsApiResponse>> getMemberAccounts(StringParameters stringParameters) {
        Single<Optional<AccountsApiResponse>> cache = mCacheDatasource
                .getMemberAccounts(stringParameters);

        Single<Optional<AccountsApiResponse>> remote = mRemoteDatasource
                .getMemberAccounts(stringParameters)
                .doOnSuccess(new Consumer<Optional<AccountsApiResponse>>() {
                    @Override
                    public void accept(Optional<AccountsApiResponse> accountsApiResponseOptional) throws Exception {
                        if (accountsApiResponseOptional.isPresent()) {
                            saveAccountsApiResponse(accountsApiResponseOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<AccountsApiResponse>>() {
                    @Override
                    public boolean test(Optional<AccountsApiResponse> accountsApiResponseOptional) throws Exception {
                        return accountsApiResponseOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<AccountsApiResponse>>error(new AccountNotFoundException()));
    }

    @Override
    public Completable saveAccount(Account account) {
        return mCacheDatasource.saveAccount(account);
    }

    @Override
    public Single<Optional<AchAccountsApiResponse>> getMemberAchAccounts(final AchAccountParameters achAccountParameters) {
//        return mRemoteDatasource.getMemberAchAccounts(achAccountParameters);
        Single<Optional<AchAccountsApiResponse>> cache = mCacheDatasource
                .getMemberAchAccounts(achAccountParameters);

        Single<Optional<AchAccountsApiResponse>> remote = mRemoteDatasource
                .getMemberAchAccounts(achAccountParameters)
                .doOnSuccess(new Consumer<Optional<AchAccountsApiResponse>>() {
                    @Override
                    public void accept(Optional<AchAccountsApiResponse> achAccountsApiResponseOptional) throws Exception {
                        if (achAccountsApiResponseOptional.isPresent()) {
                            saveAchAccountsApiResponse(achAccountsApiResponseOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<AchAccountsApiResponse>>() {
                    @Override
                    public boolean test(Optional<AchAccountsApiResponse> achAccountsApiResponseOptional) throws Exception {
                        return achAccountsApiResponseOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<AchAccountsApiResponse>>error(new AccountNotFoundException()));

    }

    @Override
    public void saveBanksApiResponse(BanksApiResponse banksApiResponse) {
        mCacheDatasource.saveBanksApiResponse(banksApiResponse);
    }

    @Override
    public void saveAchAccountsApiResponse(AchAccountsApiResponse achAccountsApiResponse) {
        mCacheDatasource.saveAchAccountsApiResponse(achAccountsApiResponse);
    }

    @Override
    public void saveAchAccount(AchAccount achAccount) {
        mCacheDatasource.saveAchAccount(achAccount);
    }

    @Override
    public void saveBankResponse(Bank bank) {
        mCacheDatasource.saveBankResponse(bank);
    }

    @Override
    public void clearAchAccountsCache() {
        mCacheDatasource.clearAchAccountsCache();
    }

    @Override
    public void saveBankCredentialsApiResponse(BankCredentialsApiResponse bankCredentialsApiResponse, String bankId) {
        mCacheDatasource.saveBankCredentialsApiResponse(bankCredentialsApiResponse, bankId);
    }

    @Override
    public Single<AccountBalanceResponse> getAccountBalance(StringParameters stringParameters) {
        return mRemoteDatasource.getAccountBalance(stringParameters);
    }

    @Override
    public Single<List<DepositedCheck>> getDepositedCheck(CheckDepositedParameters checkDepositedParameters) {
        return mRemoteDatasource.getDepositedCheck(checkDepositedParameters);
    }

    @Override
    public Single<AchTransferResponse> postAchTransfer(AchTransferParameters achTransferParameters) {
        return mRemoteDatasource.postAchTransfer(achTransferParameters);
    }

    @Override
    public Single<AchHistoryResponse> getMemberAchHistory(AchHistoryParameters parameters) {
        return mRemoteDatasource.getMemberAchHistory(parameters).doOnSuccess(new Consumer<AchHistoryResponse>() {
            @Override
            public void accept(AchHistoryResponse achHistoryResponse) throws Exception {
                for (int i=0;i<achHistoryResponse.getAchTransferHistory().size();i++) {
                    achHistoryResponse.getAchTransferHistory().get(i).setmAccountNumber(parameters.getMemberId());
                }
            }
        });
    }

    @Override
    public Single<AchTransfer> deleteAchTransfer(DeleteAchTransferParameters deleteAchTransferParameters) {
        return mRemoteDatasource.deleteAchTransfer(deleteAchTransferParameters);
    }

    @Override
    public Single<Optional<BanksApiResponse>> getBanks(Parameters parameters) {
        Single<Optional<BanksApiResponse>> cache = mCacheDatasource.getBanks(parameters);

        Single<Optional<BanksApiResponse>> remote = mRemoteDatasource.getBanks(parameters)
                .doOnSuccess(new Consumer<Optional<BanksApiResponse>>() {
                    @Override
                    public void accept(Optional<BanksApiResponse> banksApiResponseOptional) throws Exception {
                        if (banksApiResponseOptional.isPresent()) {
                            saveBanksApiResponse(banksApiResponseOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<BanksApiResponse>>() {
                    @Override
                    public boolean test(Optional<BanksApiResponse> banksApiResponseOptional) throws Exception {
                        return banksApiResponseOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<BanksApiResponse>>error(new BanksNotFoundException()));
    }

    @Override
    public Single<Optional<Bank>> getBank(BankParameters parameters) {
        Single<Optional<Bank>> cache = mCacheDatasource.getBank(parameters);

        Single<Optional<Bank>> remote = mRemoteDatasource.getBank(parameters)
                .doOnSuccess(new Consumer<Optional<Bank>>() {
                    @Override
                    public void accept(Optional<Bank> bankOptional) throws Exception {
                        if (bankOptional.isPresent()) {
                            saveBankResponse(bankOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<Bank>>() {
                    @Override
                    public boolean test(Optional<Bank> bankOptional) throws Exception {
                        return bankOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<Bank>>error(new BanksNotFoundException()));
    }

    @Override
    public Single<AchTransfer> verifyAchTransfer(VerifyAchTransferParameters verifyAchTransferParameters) {
        return mRemoteDatasource.verifyAchTransfer(verifyAchTransferParameters);
    }

    @Override
    public Single<Optional<BankCredentialsApiResponse>> getBankCredentials(final StringParameters stringParameters) {
        Single<Optional<BankCredentialsApiResponse>> cache = mCacheDatasource.getBankCredentials(stringParameters);

        Single<Optional<BankCredentialsApiResponse>> remote = mRemoteDatasource.getBankCredentials(stringParameters)
                .doOnSuccess(new Consumer<Optional<BankCredentialsApiResponse>>() {
                    @Override
                    public void accept(Optional<BankCredentialsApiResponse> bankCredentialsApiResponseOptional) throws Exception {
                        if (bankCredentialsApiResponseOptional.isPresent()) {
                            saveBankCredentialsApiResponse(
                                    bankCredentialsApiResponseOptional.get(),
                                    stringParameters.getFirstString());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<BankCredentialsApiResponse>>() {
                    @Override
                    public boolean test(Optional<BankCredentialsApiResponse> bankCredentialsApiResponseOptional) throws Exception {
                        return bankCredentialsApiResponseOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<BankCredentialsApiResponse>>error(new BanksNotFoundException()));
    }

    @Override
    public Single<AchAccount> createAchAccount(CreateAchAccountParameters createAchAccountParameters) {
        return mRemoteDatasource.createAchAccount(createAchAccountParameters)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        saveAchAccount(achAccount);
                    }
                });
    }

    @Override
    public Single<AchAccount> getAchAccount(StringParameters stringParameters) {
        return mRemoteDatasource.getAchAccount(stringParameters)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        saveAchAccount(achAccount);
                    }
                });
    }

    @Override
    public Single<Optional<AchAccount>> deleteAchAccount(final StringParameters stringParameters) {
        return mRemoteDatasource.deleteAchAccount(stringParameters).onErrorResumeNext(new Function<Throwable, SingleSource<? extends Optional<AchAccount>>>() {
                    @Override
                    public SingleSource<? extends Optional<AchAccount>> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof NoSuchElementException) {
                            ChangebankResponse error = new ChangebankResponse();
                            error.setHttpCode(HttpURLConnection.HTTP_NO_CONTENT);
                            error.setMessage("No Content");
                            mCacheDatasource.deleteAchAccount(stringParameters);
                            return Single.error(error);
                        }
                        return Single.error(throwable);
                    }
                })
                .doOnSuccess(new Consumer<Optional<AchAccount>>() {
                    @Override
                    public void accept(Optional<AchAccount> achAccountOptional) throws Exception {
                        mCacheDatasource.deleteAchAccount(stringParameters);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> confirmAchAccount(ConfirmAchAccountParameters confirmAchAccountParameters) {
        return mRemoteDatasource.confirmAchAccount(confirmAchAccountParameters);
    }

    @Override
    public Single<BankCredentialsApiResponse> getMfaOrNewBankCredentials(StringParameters stringParameters) {
        return mRemoteDatasource.getMfaOrNewBankCredentials(stringParameters);
    }

    @Override
    public Single<AchAccount> submitMfaOrNewBankCredentials(SubmitMfaOrNewBankCredentialsParameters submitMfaOrNewBankCredentialsParameters) {
        return mRemoteDatasource.submitMfaOrNewBankCredentials(submitMfaOrNewBankCredentialsParameters)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        saveAchAccount(achAccount);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> refreshAchAccounts(StringParameters stringParameters) {
        return mRemoteDatasource.refreshAchAccounts(stringParameters)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse changebankResponse) throws Exception {
                        mCacheDatasource.clearAchAccountsCache();
                    }
                });
    }

    @Override
    public Single<AchAccount> reLinkAchAccount(StringParameters stringParameters) {
        return mRemoteDatasource.reLinkAchAccount(stringParameters)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        saveAchAccount(achAccount);
                    }
                });
    }


    @Override
    public Single<CardToCardResponse> cardToCardTransfer(CardToCardTransferParameters cardToCardTransferParameters) {
        return mRemoteDatasource.cardToCardTransfer(cardToCardTransferParameters);
    }

    @Override
    public Single<AchAccount> unLinkAchAccount(StringParameters stringParameters) {
        return mRemoteDatasource.unLinkAchAccount(stringParameters)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        saveAchAccount(achAccount);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> activateCard(final CardActivationParameters cardActivationParameters) {
        return mRemoteDatasource.activateCard(cardActivationParameters)
                .flatMap(new Function<ChangebankResponse, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(ChangebankResponse changebankResponse) throws Exception {
                        mCacheDatasource.activateCard(cardActivationParameters);
                        return Single.just(changebankResponse);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateCardPin(final CardActivationParameters cardActivationParameters) {
        return mRemoteDatasource.updateCardPin(cardActivationParameters)
                .flatMap(new Function<ChangebankResponse, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(ChangebankResponse changebankResponse) throws Exception {
                        mCacheDatasource.updateCardPin(cardActivationParameters);
                        return Single.just(changebankResponse);
                    }
                });
    }

    @Override
    public Single<Card> updateCardStatus(final UpdateCardStatusParameters params) {
//        return mRemoteDatasource.updateCardStatus(params)
//                .doOnSuccess(new Consumer<Card>() {
//                    @Override
//                    public void accept(Card card) throws Exception {
//                        mCacheDatasource.updateCardStatus(params);
//                    }
//                });


        return Single.zip(
                mRemoteDatasource.updateCardStatus(params),
                Single.just(params),
                new BiFunction<Card, UpdateCardStatusParameters, SingleSource<? extends Card>>() {
                    @Override
                    public SingleSource<? extends Card> apply(Card card, UpdateCardStatusParameters parameters) throws Exception {
                        return mCacheDatasource.updateCardStatus(parameters
                                .withAccountNumber(card.getAccountNumber()))
                                .toMaybe()
                                .toSingle(card);
                    }
                })
                .flatMap(new Function<SingleSource<? extends Card>, SingleSource<? extends Card>>() {
                    @Override
                    public SingleSource<? extends Card> apply(SingleSource<? extends Card> singleSource) throws Exception {
                        return singleSource;
                    }
                });
    }

    @Override
    public void clearAllAccountDatasourceCache() {
        mCacheDatasource.clearAllAccountDatasourceCache();
    }

    @Override
    public Single<Optional<File>> getDirectDepositForm(DirectDepositParameters directDepositParameters) {
        Single<Optional<File>> cache = mCacheDatasource.getDirectDepositForm(directDepositParameters);

        Single<Optional<File>> remote = mRemoteDatasource.getDirectDepositForm(directDepositParameters)
                .doOnSuccess(new Consumer<Optional<File>>() {
                    @Override
                    public void accept(Optional<File> fileOptional) throws Exception {
                        if (fileOptional.isPresent()) {
                            saveDirectDepositForm(fileOptional.get());
                        }
                    }
                });

        return remote;/*Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<File>>() {
                    @Override
                    public boolean test(Optional<File> fileOptional) throws Exception {
                        return fileOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<File>>error(new DocumentNotFoundException()));*/
    }

    @Override
    public void saveDirectDepositForm(File file) {
        mCacheDatasource.saveDirectDepositForm(file);
    }

    @Override
    public Single<Optional<Card>> getCard(CardParameters cardParameters) {
        Single<Optional<Card>> cache = mCacheDatasource.getCard(cardParameters);

        Single<Optional<Card>> remote = mRemoteDatasource.getCard(cardParameters)
                .doOnSuccess(new Consumer<Optional<Card>>() {
                    @Override
                    public void accept(Optional<Card> cardOptional) throws Exception {
                        if (cardOptional.isPresent()) {
                            saveCard(cardOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<Card>>() {
                    @Override
                    public boolean test(Optional<Card> cardOptional) throws Exception {
                        return cardOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<Card>>error(new CardNotFoundException()));
    }


    @Override
    public Single<ChangebankResponse> requestPhysicalCard(CardParameters cardParameters) {
        return mRemoteDatasource.requestPhysicalCard(cardParameters)
                .flatMap(new Function<ChangebankResponse, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(ChangebankResponse changebankResponse) throws Exception {
                        return Single.just(changebankResponse);
                    }
                });
    }

    @Override
    public Single<Optional<Card>> getCardByCardId(CardParameters cardParameters) {
        Single<Optional<Card>> cache = Single.just(Optional.<Card>absent());

        Single<Optional<Card>> remote = mRemoteDatasource.getCardByCardId(cardParameters)
                .doOnSuccess(new Consumer<Optional<Card>>() {
                    @Override
                    public void accept(Optional<Card> cardOptional) throws Exception {
                        if (cardOptional.isPresent()) {
//                            saveCard(cardOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<Card>>() {
                    @Override
                    public boolean test(Optional<Card> cardOptional) throws Exception {
                        return cardOptional.isPresent();
                    }
                })
                .firstElement()
                .switchIfEmpty(Single.<Optional<Card>>error(new CardNotFoundException()));
    }

    @Override
    public void saveCard(Card card) {
        mCacheDatasource.saveCard(card);
    }

    @Override
    public Map<String, AchAccount> getAchAccountMap() {
        return mCacheDatasource.getAchAccountMap();
    }

    @Override
    public Single<AchAccount> createMicroDepositAccount(CreateMicroDepositAchAccountParameters parameters) {
        return mRemoteDatasource.createMicroDepositAccount(parameters)
                .flatMap(new Function<AchAccount, SingleSource<? extends AchAccount>>() {
                    @Override
                    public SingleSource<? extends AchAccount> apply(AchAccount achAccount) throws Exception {
                        AchAccount account = new AchAccount(achAccount.getId(),null,parameters.getRoutingNumber(),parameters.getBankAccountName(),null, AchAccountStatus.valueOf("PENDING"),AccountType.valueOf(parameters.mAccountType),parameters.getAccountNumber(),null,null,null,null);
                        mCacheDatasource.saveAchAccount(account);
                        return Single.just(account);
                    }
                });
    }

    @Override
    public Single<AchAccount> verifyMicroDepositAccount(VerifyMicroDepositAchAccountParameters verifyMicroDepositAchAccountParameters) {
        return mRemoteDatasource.verifyMicroDepositAccount(verifyMicroDepositAchAccountParameters).onErrorResumeNext(new Function<Throwable, SingleSource<? extends AchAccount>>() {
            @Override
            public SingleSource<? extends AchAccount> apply(Throwable throwable) throws Exception {
                if (throwable instanceof NoSuchElementException) {
                    ChangebankResponse changebankResponse = new ChangebankResponse();
                    changebankResponse.setHttpCode(204);
                    AchAccount account = mCacheDatasource.getAchAccountMap().get(verifyMicroDepositAchAccountParameters.getAchAccountId());
                    AchAccountStatus achAccountStatus = AchAccountStatus.ACTIVE;
                    AchAccount account2 = AchAccount.create(account.getId());
                    account2.setmStatus(achAccountStatus);
                    account2 = account;
                    account2.setmStatus(achAccountStatus);
                    mCacheDatasource.saveAchAccount(account2);
                    changebankResponse.setAchAccount(account2);
                    return Single.error(changebankResponse);
                }
                return Single.error(throwable);
            }
        });
    }

    @Override
    public Single<CreateNewSubCardResponse> createSubCard(CreateSubCardParameter createSubCardParameter) {
        return mRemoteDatasource.createSubCard(createSubCardParameter);
    }

    @Override
    public void saveAccountsApiResponse(AccountsApiResponse accountsApiResponse) {
        mCacheDatasource.saveAccountsApiResponse(accountsApiResponse);
    }
}
