package com.example.cf_sdk.logic.changebankapi.source.remote;

import com.example.cf_sdk.logic.changebankapi.network.api.AccountApi;
import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
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
import com.example.cf_sdk.logic.changebankapi.network.api.AccountApi;
import com.google.common.base.Optional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 *
 * Remote datasource to call account endpoints.
 */

public class AccountRemoteDatasource implements AccountDatasource {

    private final File mCacheDirectory;
    private AccountApi mAccountApi;

    @Inject
    public AccountRemoteDatasource(AccountApi accountApi, File cacheDirectory) {
        mAccountApi = accountApi;
        mCacheDirectory = cacheDirectory;
    }

    @Override
    public Single<Optional<Account>> getAccountById(GetAccountParameters getAccountParameters) {
        return mAccountApi.getAccountById(
                getAccountParameters.getHeaders(),
                getAccountParameters.getAccountId())
                .map(new Function<Account, Optional<Account>>() {
                    @Override
                    public Optional<Account> apply(Account account) throws Exception {
                        return Optional.of(account);
                    }
                });
    }

    @Override
    public Single<CardToCardResponse> cardToCardTransfer(CardToCardTransferParameters cardToCardTransferParameters) {
        return mAccountApi.cardToCardTransfer(cardToCardTransferParameters.getHeaders(),cardToCardTransferParameters.getAccountNumber(),cardToCardTransferParameters.getToAccount(),cardToCardTransferParameters);
    }

    @Override
    public Single<List<DepositedCheck>> getDepositedCheck(CheckDepositedParameters checkDepositedParameters) {
        return mAccountApi.getDepositedCheck(checkDepositedParameters.getHeaders(),checkDepositedParameters);
    }

    @Override
    public Single<Optional<AccountsApiResponse>> getMemberAccounts(StringParameters stringParameters) {
        return mAccountApi.getMemberAccounts(
                stringParameters.getHeaders(),
                stringParameters.getFirstString())
                .map(new Function<AccountsApiResponse, Optional<AccountsApiResponse>>() {
                    @Override
                    public Optional<AccountsApiResponse> apply(AccountsApiResponse accountsApiResponse) throws Exception {
                        return Optional.of(accountsApiResponse);
                    }
                });
    }

    @Override
    public Completable saveAccount(Account account) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveAccount");
    }

    @Override
    public Single<Optional<AchAccountsApiResponse>> getMemberAchAccounts(AchAccountParameters achAccountParameters) {
        AchAccountStatus status = achAccountParameters.getAchAccountStatus() != AchAccountStatus.NONE ?
                achAccountParameters.getAchAccountStatus() : null;
        return mAccountApi.getMemberAchAccounts(
                achAccountParameters.getHeaders(),
                achAccountParameters.getMemberId())
                .map(new Function<AchAccountsApiResponse, Optional<AchAccountsApiResponse>>() {
                    @Override
                    public Optional<AchAccountsApiResponse> apply(AchAccountsApiResponse achAccountsApiResponse) throws Exception {
                        return Optional.of(achAccountsApiResponse);
                    }
                });
    }

    @Override
    public void saveBanksApiResponse(BanksApiResponse banksApiResponse) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support banksApiResponse");
    }

    @Override
    public void saveAccountsApiResponse(AccountsApiResponse accountsApiResponse) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveAccountsApiResponse");
    }

    @Override
    public void saveAchAccountsApiResponse(AchAccountsApiResponse achAccountsApiResponse) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveAchAccountsApiResponse");
    }

    @Override
    public void saveAchAccount(AchAccount achAccount) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveAchAccount");
    }

    @Override
    public void saveBankResponse(Bank bank) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveBankResponse");
    }

    @Override
    public void clearAchAccountsCache() {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support clearAchAccountsCache");
    }

    @Override
    public void saveBankCredentialsApiResponse(BankCredentialsApiResponse bankCredentialsApiResponse, String bankId) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveBankCredentialsApiResponse");
    }

    @Override
    public Single<AccountBalanceResponse> getAccountBalance(StringParameters stringParameters) {
        return mAccountApi.getAccountBalance(stringParameters.getHeaders(), stringParameters.getFirstString());
    }

    @Override
    public Single<AchTransferResponse> postAchTransfer(AchTransferParameters achTransferParameters) {
        return mAccountApi.postAchTransfer(achTransferParameters.getHeaders(), achTransferParameters.getAchAccountId(), achTransferParameters);
    }

    @Override
    public Single<AchHistoryResponse> getMemberAchHistory(AchHistoryParameters parameters) {
        String transferTypeQueryParam = null;
        if (parameters.getAchTransferType() != null) {
            transferTypeQueryParam = parameters.getAchTransferType().getTypeName();
        }
        return mAccountApi.getMemberAchHistory(parameters.getHeaders(),parameters.getMemberId(), parameters.getDateFrom(),parameters.getDateTo());
    }

    @Override
    public Single<AchTransfer> deleteAchTransfer(DeleteAchTransferParameters deleteAchTransferParameters) {
        return mAccountApi.deleteAchTransfer(
                deleteAchTransferParameters.getHeaders(),
                deleteAchTransferParameters.getAchTransferId());
    }

    @Override
    public Single<Optional<BanksApiResponse>> getBanks(Parameters parameters) {
        return mAccountApi.getBanks(parameters.getHeaders())
                .map(new Function<BanksApiResponse, Optional<BanksApiResponse>>() {
                    @Override
                    public Optional<BanksApiResponse> apply(BanksApiResponse banksApiResponse) throws Exception {
                        return Optional.of(banksApiResponse);
                    }
                });
    }

    @Override
    public Single<Optional<Bank>> getBank(BankParameters parameters) {
        return mAccountApi.getBank(parameters.getHeaders(), parameters.getBankId())
                .map(new Function<Bank, Optional<Bank>>() {
                    @Override
                    public Optional<Bank> apply(Bank bank) throws Exception {
                        return Optional.of(bank);
                    }
                });
    }

    @Override
    public Single<AchTransfer> verifyAchTransfer(VerifyAchTransferParameters verifyAchTransferParameters) {
        return mAccountApi.verifyAchTransfer(verifyAchTransferParameters.getHeaders(), verifyAchTransferParameters);
    }

    @Override
    public Single<Optional<BankCredentialsApiResponse>> getBankCredentials(StringParameters stringParameters) {
        return mAccountApi.getBankCredentials(stringParameters.getHeaders(), stringParameters.getFirstString())
                .map(new Function<BankCredentialsApiResponse, Optional<BankCredentialsApiResponse>>() {
                    @Override
                    public Optional<BankCredentialsApiResponse> apply(BankCredentialsApiResponse bankCredentialsApiResponse) throws Exception {
                        return Optional.of(bankCredentialsApiResponse);
                    }
                });
    }

    @Override
    public Single<AchAccount> createAchAccount(CreateAchAccountParameters createAchAccountParameters) {
        return mAccountApi.createAchAccount(createAchAccountParameters.getHeaders(), createAchAccountParameters);
    }

    @Override
    public Single<AchAccount> getAchAccount(StringParameters stringParameters) {
        return mAccountApi.getAchAccount(stringParameters.getHeaders(), stringParameters.getFirstString());
    }

    @Override
    public Single<Optional<AchAccount>> deleteAchAccount(StringParameters stringParameters) {
        return mAccountApi.deleteAchAccount(stringParameters.getHeaders(), stringParameters.getFirstString())
                .map(new Function<AchAccount, Optional<AchAccount>>() {
                    @Override
                    public Optional<AchAccount> apply(AchAccount achAccount) throws Exception {
                        return Optional.of(achAccount);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> confirmAchAccount(ConfirmAchAccountParameters confirmAchAccountParameters) {
        return mAccountApi.linkAchAccount(
                confirmAchAccountParameters.getHeaders(),
                confirmAchAccountParameters.getAchAccountId(),
                confirmAchAccountParameters);
    }

    @Override
    public Single<BankCredentialsApiResponse> getMfaOrNewBankCredentials(StringParameters stringParameters) {
        return mAccountApi.getMfaOrNewBankCredentials(stringParameters.getHeaders(), stringParameters.getFirstString());
    }


    @Override
    public Single<AchAccount> submitMfaOrNewBankCredentials(SubmitMfaOrNewBankCredentialsParameters submitMfaOrNewBankCredentialsParameters) {
        return mAccountApi.submitMfaOrNewBankCredentials(submitMfaOrNewBankCredentialsParameters.getHeaders(),
                submitMfaOrNewBankCredentialsParameters.getAchAccountId(),
                submitMfaOrNewBankCredentialsParameters);
    }

    @Override
    public Single<ChangebankResponse> refreshAchAccounts(StringParameters stringParameters) {
        return mAccountApi.refreshAchAccounts(stringParameters.getHeaders(), stringParameters.getFirstString());
    }

    @Override
    public Single<AchAccount> reLinkAchAccount(StringParameters stringParameters) {
        return mAccountApi.reLinkAchAccount(
                stringParameters.getHeaders(),
                stringParameters.getFirstString(),
                Parameters.EMPTY);
    }

    @Override
    public Single<AchAccount> unLinkAchAccount(StringParameters stringParameters) {
        return mAccountApi.unLinkAchAccount(stringParameters.getHeaders(), stringParameters.getFirstString());
    }

    @Override
    public Single<ChangebankResponse> activateCard(CardActivationParameters cardActivationParameters) {
        return mAccountApi.activateCard(
                cardActivationParameters.getHeaders(),
                cardActivationParameters.getCardId(),
                cardActivationParameters);
    }

    @Override
    public Single<ChangebankResponse> updateCardPin(CardActivationParameters cardActivationParameters) {
        return mAccountApi.updateCardPan(cardActivationParameters.getHeaders(), cardActivationParameters.getCardId(), cardActivationParameters);
    }

    @Override
    public Single<Card> updateCardStatus(final UpdateCardStatusParameters updateCardStatusParameters) {
        return mAccountApi.updateCardStatus(
                updateCardStatusParameters.getHeaders(),
                updateCardStatusParameters.getCardNumber(),
                updateCardStatusParameters);
    }

    @Override
    public void clearAllAccountDatasourceCache() {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support clearAllAccountDatasourceCache");
    }

    @Override
    public Single<Optional<File>> getDirectDepositForm(final DirectDepositParameters directDepositParameters) {
        return mAccountApi.getDirectDepositForm(directDepositParameters.getHeaders(), directDepositParameters.getAccountId())
                .flatMap(new Function<Response<ResponseBody>, SingleSource<? extends Optional<File>>>() {
                    @Override
                    public SingleSource<? extends Optional<File>> apply(Response<ResponseBody> responseBodyResponse) throws Exception {
                        String fileName = "DirectDepositForm" + directDepositParameters.getAccountId();
                        try {
                            File file;
                            file = File.createTempFile(fileName, ".pdf", mCacheDirectory);
                            file.deleteOnExit();
                            BufferedSink sink = Okio.buffer(Okio.sink(file));
                            // you can access body of response
                            ResponseBody responseBody = responseBodyResponse.body();
                            if (responseBody != null) {
                                sink.writeAll(responseBody.source());
                                sink.close();
                            } else {
                                return Single.error(new Exception("Body is null"));
                            }

                            return Single.just(Optional.of(file));
                        } catch (IOException e) {
                            return Single.error(e);
                        }
                    }
                });
    }

    @Override
    public void saveDirectDepositForm(File file) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveDirectDepositForm");
    }

    @Override
    public Single<Optional<Card>> getCard(CardParameters cardParameters) {
        return mAccountApi.getCard(cardParameters.getHeaders(), cardParameters.getCardId())
                .map(new Function<Card, Optional<Card>>() {
                    @Override
                    public Optional<Card> apply(Card card) throws Exception {
                        return Optional.of(card);
                    }
                });
    }


    @Override
    public Single<ChangebankResponse> requestPhysicalCard(CardParameters cardParameters) {
        return mAccountApi.requestPhysicalCard(cardParameters.getHeaders(),cardParameters.getCardId());
    }

    @Override
    public Single<Optional<Card>> getCardByCardId(CardParameters cardParameters) {
        return mAccountApi.getCardByCardId(cardParameters.getHeaders(),cardParameters.getCardId())
                .map(new Function<Card, Optional<Card>>() {
                    @Override
                    public Optional<Card> apply(Card card) throws Exception {
                        return Optional.of(card);
                    }
                });
    }

    @Override
    public void saveCard(Card card) {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support saveCard");
    }

    @Override
    public Map<String, AchAccount> getAchAccountMap() {
        throw new UnsupportedOperationException(
                "AccountRemoteDatasource not support getAchAccountMap");
    }

    @Override
    public Single<AchAccount> createMicroDepositAccount(CreateMicroDepositAchAccountParameters createMicroDepositAchAccountParameters) {
        return mAccountApi.createMicroDepositAccount(createMicroDepositAchAccountParameters.getHeaders(), createMicroDepositAchAccountParameters,createMicroDepositAchAccountParameters.getMemberId());
    }

    @Override
    public Single<AchAccount> verifyMicroDepositAccount(VerifyMicroDepositAchAccountParameters verifyMicroDepositAchAccountParameters) {
        return mAccountApi.verifyMicroDepositAccount(verifyMicroDepositAchAccountParameters.getHeaders(), verifyMicroDepositAchAccountParameters.getAchAccountId(), verifyMicroDepositAchAccountParameters);
    }

    @Override
    public Single<CreateNewSubCardResponse> createSubCard(CreateSubCardParameter createSubCardParameter) {
        return mAccountApi.createNewSubCard(createSubCardParameter.getHeaders(),createSubCardParameter.getMainCardNumber(),createSubCardParameter);
    }

}
