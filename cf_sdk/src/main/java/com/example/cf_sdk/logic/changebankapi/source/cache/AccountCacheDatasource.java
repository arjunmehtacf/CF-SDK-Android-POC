package com.example.cf_sdk.logic.changebankapi.source.cache;

import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccountStatus;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransfer;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.model.account.Card;
import com.example.cf_sdk.logic.changebanksdk.model.account.CardStatus;
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
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 *
 * Account cache datasource to provide in-memory data.
 */

public class AccountCacheDatasource implements AccountDatasource {
    private Map<String, Bank> mBanks;
    private Map<String, BankCredentialsApiResponse> mBankCredentialsApiResponse;
    private Map<String, Account> mAccountsCacheMap;
    private Multimap<String, Card> mCardsCacheMultimap;
    private Map<String, AchAccount> mAchAccounts;
    private File mDirectDepositForm;

    public AccountCacheDatasource() {
        mBanks = new HashMap<>();
        mBankCredentialsApiResponse = new HashMap<>();
        mAccountsCacheMap = new HashMap<>();
        mAchAccounts = new HashMap<>();
        mCardsCacheMultimap = HashMultimap.create();
    }

    @Override
    public Single<Optional<Account>> getAccountById(GetAccountParameters getAccountParameters) {
        if (getAccountParameters.getShouldClearCache()) {
            mAccountsCacheMap = new HashMap<>();
        }

        if (mAccountsCacheMap.containsKey(getAccountParameters.getAccountId())) {
            return Maybe.just(mAccountsCacheMap.get(getAccountParameters.getAccountId()))
                    .map(new Function<Account, Optional<Account>>() {
                        @Override
                        public Optional<Account> apply(Account account) throws Exception {
                            Collection<Card> cards = mCardsCacheMultimap.get(account.getAccountNumber());
                            return Optional.of(account.withCardsList(new ArrayList<>(cards)));
                        }
                    })
                    .toSingle(Optional.<Account>absent());
        } else {
            return Single.just(Optional.<Account>absent());
        }

    }

    @Override
    public Single<Optional<AccountsApiResponse>> getMemberAccounts(StringParameters stringParameters) {
        // temporary commented for getting latest card data from the server
//        if (mAccountsCacheMap == null || mAccountsCacheMap.isEmpty()) {
            return Single.just(Optional.<AccountsApiResponse>absent());
//        }
        /*return Single.just(Optional.of(AccountsApiResponse
                .create(new ArrayList<>(mAccountsCacheMap.values()))));*/
    }

    @Override
    public Completable saveAccount(final Account account) {
        Observable cardRecorder = Observable.fromIterable(account.getCardsList())
                .doOnNext(new Consumer<Card>() {
                    @Override
                    public void accept(Card card) throws Exception {
                        saveCard(card);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mAccountsCacheMap.put(account.getId(), account);
                    }
                });
        return Completable.fromObservable(cardRecorder);
    }

    @Override
    public Single<Optional<AchAccountsApiResponse>> getMemberAchAccounts(final AchAccountParameters achAccountParameters) {
        if (mAchAccounts.isEmpty() ||
                hasAccountsInTransitionStatus(new ArrayList<>(mAchAccounts.values()))) {
            return Single.just(Optional.<AchAccountsApiResponse>absent());
        }

        return Observable.fromIterable(mAchAccounts.values())
                .filter(new Predicate<AchAccount>() {
                    @Override
                    public boolean test(AchAccount achAccount) throws Exception {
                        return achAccountParameters.getAchAccountStatus() == AchAccountStatus.NONE ||
                                achAccount.getStatus() == achAccountParameters.getAchAccountStatus();
                    }
                })
                .toList()
                .flatMap(new Function<List<AchAccount>, SingleSource<? extends Optional<AchAccountsApiResponse>>>() {
                    @Override
                    public SingleSource<? extends Optional<AchAccountsApiResponse>> apply(List<AchAccount> achAccounts) throws Exception {
                        return Single.just(Optional.of(AchAccountsApiResponse.create(achAccounts)));
                    }
                });
    }

    @Override
    public void saveBanksApiResponse(BanksApiResponse banksApiResponse) {
        mBanks.clear();
        for (Bank bank : banksApiResponse.getBanks()) {
            mBanks.put(bank.getId(), bank);
        }
    }



    @Override
    public Single<CardToCardResponse> cardToCardTransfer(CardToCardTransferParameters cardToCardTransferParameters) {
        return null;
    }

    @Override
    public Single<List<DepositedCheck>> getDepositedCheck(CheckDepositedParameters checkDepositedParameters) {
        return null;
    }

    @Override
    public void saveAccountsApiResponse(AccountsApiResponse accountsApiResponse) {
        List<Account> accounts = accountsApiResponse.getAccounts();
        if (accounts != null) {
            for (Account account : accounts) {
                mAccountsCacheMap.put(account.getId(), account);
                if (!account.getCardsList().isEmpty()) {
                    mCardsCacheMultimap.putAll(account.getLast4AccountNumber(), account.getCardsList());
                }
            }
        }
    }

    @Override
    public void saveAchAccountsApiResponse(AchAccountsApiResponse achAccountsApiResponse) {
        mAchAccounts.clear();
        for (AchAccount achAccount : achAccountsApiResponse.getAchAccounts()) {
            saveAchAccount(achAccount);
        }
    }

    @Override
    public void saveAchAccount(AchAccount achAccount) {
        mAchAccounts.put(achAccount.getId(), achAccount);
    }

    @Override
    public void saveBankResponse(Bank bank) {
        mBanks.put(bank.getId(), bank);
    }

    @Override
    public void clearAchAccountsCache() {
        mAchAccounts.clear();
    }

    @Override
    public void saveBankCredentialsApiResponse(BankCredentialsApiResponse bankCredentialsApiResponse, String bankId) {
        mBankCredentialsApiResponse.put(bankId, bankCredentialsApiResponse);
    }

    @Override
    public Single<AccountBalanceResponse> getAccountBalance(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support getAccountBalance");
    }

    @Override
    public Single<AchTransferResponse> postAchTransfer(AchTransferParameters achTransferParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support postAchTransfer");
    }

    @Override
    public Single<AchHistoryResponse> getMemberAchHistory(AchHistoryParameters parameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support getMemberAchHistory");
    }

    @Override
    public Single<AchTransfer> deleteAchTransfer(DeleteAchTransferParameters deleteAchTransferParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support deleteAchTransfer");
    }

    @Override
    public Single<Optional<BanksApiResponse>> getBanks(Parameters parameters) {
        if (mBanks.isEmpty()) {
            return Single.just(Optional.<BanksApiResponse>absent());
        }
        return Single.just(Optional.of(BanksApiResponse.create(new ArrayList<>(mBanks.values()))));
    }

    @Override
    public Single<Optional<Bank>> getBank(BankParameters parameters) {
        return Single.just(Optional.fromNullable(mBanks.get(parameters.getBankId())));
    }

    @Override
    public Single<AchTransfer> verifyAchTransfer(VerifyAchTransferParameters verifyAchTransferParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support verifyAchTransfer");
    }

    @Override
    public Single<Optional<BankCredentialsApiResponse>> getBankCredentials(StringParameters stringParameters) {
        return Single.just(Optional.fromNullable(
                mBankCredentialsApiResponse.get(stringParameters.getFirstString())));
    }

    @Override
    public Single<AchAccount> createAchAccount(CreateAchAccountParameters createAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support createAchAccount");
    }

    @Override
    public Single<AchAccount> getAchAccount(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support getAchAccount");
    }

    @Override
    public Single<Optional<AchAccount>> deleteAchAccount(StringParameters stringParameters) {
        AchAccount achAccount = null;
        if (mAchAccounts.containsKey(stringParameters.getFirstString())) {
            achAccount = mAchAccounts.remove(stringParameters.getFirstString());
        }
        return Single.just(Optional.fromNullable(achAccount));
    }

    @Override
    public Single<ChangebankResponse> confirmAchAccount(ConfirmAchAccountParameters confirmAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support linkAchAccount");
    }

    @Override
    public Single<BankCredentialsApiResponse> getMfaOrNewBankCredentials(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support getMfaOrNewBankCredentials");
    }

    @Override
    public Single<AchAccount> submitMfaOrNewBankCredentials(SubmitMfaOrNewBankCredentialsParameters submitMfaOrNewBankCredentialsParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support submitMfaOrNewBankCredentials");
    }

    @Override
    public Single<ChangebankResponse> refreshAchAccounts(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support submitMfaOrNewBankCredentials");
    }

    @Override
    public Single<AchAccount> reLinkAchAccount(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support reLinkAchAccount");
    }

    @Override
    public Single<AchAccount> unLinkAchAccount(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support unLinkAchAccount");
    }

    @Override
    public Single<ChangebankResponse> activateCard(final CardActivationParameters cardActivationParameters) {
        return getCard(CardParameters.create(cardActivationParameters.getCardId()))
                .map(new Function<Optional<Card>, ChangebankResponse>() {
                    @Override
                    public ChangebankResponse apply(Optional<Card> cardOptional) throws Exception {

                        if (cardOptional.isPresent()) {
                            Card result = cardOptional.get().withStatus(CardStatus.ACTIVE);
                            mCardsCacheMultimap.replaceValues(
                                    cardOptional.get().getAccountNumber(),
                                    ImmutableList.of(result));
                        }

                        ChangebankResponse response = new ChangebankResponse();
                        response.setHttpCode(200);
                        response.setMessage("OK");

                        return response;
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateCardPin(CardActivationParameters cardActivationParameters) {
        resetCards();
        return Single.just(new ChangebankResponse());
    }

    @Override
    public Single<Card> updateCardStatus(final UpdateCardStatusParameters updateCardStatusParameters) {
//        resetCards();
//        throw new UnsupportedOperationException(
//                "AccountCacheDatasource not support updateCardStatus");
        return Single.just(new Card());
        /*return Observable.fromIterable(mCardsCacheMultimap.values())
                .filter(new Predicate<Card>() {
                    @Override
                    public boolean test(Card card) throws Exception {
                        return card.getId().equals(updateCardStatusParameters.getCardId())
                                && updateCardStatusParameters.getAccountNumber() != null
                                && card.getAccountNumber().equals(updateCardStatusParameters.getAccountNumber());
                    }
                })
                .map(new Function<Card, Card>() {
                    @Override
                    public Card apply(Card card) throws Exception {
                        Card result = card.withStatus(updateCardStatusParameters.getStatus());
                        mCardsCacheMultimap.replaceValues(
                                card.getAccountNumber(),
                                ImmutableList.of(result));
                        return result;
                    }
                })
                .singleOrError();*/ // Cache should not contain repeated cards
    }

    @Override
    public void clearAllAccountDatasourceCache() {
        mBanks = new HashMap<>();
        mBankCredentialsApiResponse = new HashMap<>();
        mAccountsCacheMap = new HashMap<>();
        mAchAccounts = new HashMap<>();
        mDirectDepositForm = null;
        resetCards();
    }

    @Override
    public Single<Optional<File>> getDirectDepositForm(DirectDepositParameters directDepositParameters) {
        return Single.just(Optional.fromNullable(mDirectDepositForm));
    }

    @Override
    public void saveDirectDepositForm(File file) {
        mDirectDepositForm = file;
    }

    @Override
    public Single<Optional<Card>> getCard(final CardParameters cardParameters) {
        if (cardParameters.getShouldClearCache()) {
            resetCards();
        }

        return Observable.fromIterable(mCardsCacheMultimap.values())
                .filter(new Predicate<Card>() {
                    @Override
                    public boolean test(Card card) throws Exception {
                        return card.getId().equals(cardParameters.getCardId());
                    }
                })
                .map(new Function<Card, Optional<Card>>() {
                    @Override
                    public Optional<Card> apply(Card card) throws Exception {
                        return Optional.of(card);
                    }
                })
                .singleElement()
                .defaultIfEmpty(Optional.<Card>absent())
                .toSingle();
    }

    @Override
    public Single<Optional<Card>> getCardByCardId(CardParameters cardParameters) {
        return Observable.fromIterable(mCardsCacheMultimap.values())
                .filter(new Predicate<Card>() {
                    @Override
                    public boolean test(Card card) throws Exception {
                        return card.getId().equals(cardParameters.getCardId());
                    }
                })
                .map(new Function<Card, Optional<Card>>() {
                    @Override
                    public Optional<Card> apply(Card card) throws Exception {
                        return Optional.of(card);
                    }
                })
                .singleElement()
                .defaultIfEmpty(Optional.<Card>absent())
                .toSingle();
    }

    @Override
    public void saveCard(Card card) {
        if (mCardsCacheMultimap.containsValue(card)) {
            mCardsCacheMultimap.remove(card.getAccountNumber(), card);
        }
        mCardsCacheMultimap.put(card.getAccountNumber(), card);
    }

    @Override
    public Map<String, AchAccount> getAchAccountMap() {
        return mAchAccounts;
    }

    @Override
    public Single<AchAccount> createMicroDepositAccount(CreateMicroDepositAchAccountParameters createMicroDepositAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support createMicroDepositAccount");
    }

    @Override
    public Single<AchAccount> verifyMicroDepositAccount(VerifyMicroDepositAchAccountParameters verifyMicroDepositAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support createMicroDepositAccount");
    }

    private boolean hasAccountsInTransitionStatus(List<AchAccount> achAccounts) {
        for (AchAccount achAccount : achAccounts) {
            if (achAccount.hasTransitionalStatus()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Single<ChangebankResponse> requestPhysicalCard(CardParameters cardParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support createMicroDepositAccount");
    }

    private void resetCards() {
        mCardsCacheMultimap = ArrayListMultimap.create();
    }

    @Override
    public Single<CreateNewSubCardResponse> createSubCard(CreateSubCardParameter createSubCardParameter) {
        return Single.just(new CreateNewSubCardResponse());
    }

}
