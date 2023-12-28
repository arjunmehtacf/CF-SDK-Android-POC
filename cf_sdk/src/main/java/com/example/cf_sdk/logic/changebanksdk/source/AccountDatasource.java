package com.example.cf_sdk.logic.changebanksdk.source;

import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
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
import com.google.common.base.Optional;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *
 * Datasource to provide account data.
 */

public interface AccountDatasource {
    Single<Optional<Account>> getAccountById(GetAccountParameters getAccountParameters);

    Single<Optional<AccountsApiResponse>> getMemberAccounts(StringParameters stringParameters);

    Single<List<DepositedCheck>> getDepositedCheck(CheckDepositedParameters checkDepositedParameters);

    Completable saveAccount(Account account);

    Single<Optional<AchAccountsApiResponse>> getMemberAchAccounts(AchAccountParameters achAccountParameters);

    void saveBanksApiResponse(BanksApiResponse banksApiResponse);

    void saveAccountsApiResponse(AccountsApiResponse accountsApiResponse);

    void saveAchAccountsApiResponse(AchAccountsApiResponse achAccountsApiResponse);

    void saveAchAccount(AchAccount achAccount);

    void saveBankResponse(Bank bank);

    void clearAchAccountsCache();

    void saveBankCredentialsApiResponse(BankCredentialsApiResponse bankCredentialsApiResponse, String bankId);

    Single<AccountBalanceResponse> getAccountBalance(StringParameters stringParameters);

    Single<AchTransferResponse> postAchTransfer(AchTransferParameters achTransferParameters);

    Single<AchHistoryResponse> getMemberAchHistory(AchHistoryParameters parameters);

    Single<AchTransfer> deleteAchTransfer(DeleteAchTransferParameters deleteAchTransferParameters);

    Single<Optional<BanksApiResponse>> getBanks(Parameters parameters);

    Single<Optional<Bank>> getBank(BankParameters parameters);

    Single<AchTransfer> verifyAchTransfer(VerifyAchTransferParameters verifyAchTransferParameters);

    Single<Optional<BankCredentialsApiResponse>> getBankCredentials(StringParameters stringParameters);

    Single<AchAccount> createAchAccount(CreateAchAccountParameters createAchAccountParameters);

    Single<AchAccount> getAchAccount(StringParameters stringParameters);

    Single<Optional<AchAccount>> deleteAchAccount(StringParameters stringParameters);

    Single<ChangebankResponse> confirmAchAccount(ConfirmAchAccountParameters confirmAchAccountParameters);

    Single<BankCredentialsApiResponse> getMfaOrNewBankCredentials(StringParameters stringParameters);

    Single<AchAccount> submitMfaOrNewBankCredentials(SubmitMfaOrNewBankCredentialsParameters submitMfaOrNewBankCredentialsParameters);

    Single<ChangebankResponse> refreshAchAccounts(StringParameters stringParameters);

    Single<AchAccount> reLinkAchAccount(StringParameters stringParameters);

    Single<CardToCardResponse> cardToCardTransfer(CardToCardTransferParameters cardToCardTransferParameters);

    Single<AchAccount> unLinkAchAccount(StringParameters stringParameters);

    Single<ChangebankResponse> activateCard(CardActivationParameters cardActivationParameters);

    Single<ChangebankResponse> updateCardPin(CardActivationParameters cardActivationParameters);

    Single<Card> updateCardStatus(UpdateCardStatusParameters updateCardStatusParameters);

    void clearAllAccountDatasourceCache();

    Single<Optional<File>> getDirectDepositForm(DirectDepositParameters directDepositParameters);

    void saveDirectDepositForm(File file);

    Single<Optional<Card>> getCard(CardParameters cardParameters);

    Single<Optional<Card>> getCardByCardId(CardParameters cardParameters);

    Single<ChangebankResponse> requestPhysicalCard(CardParameters cardParameters);

    void saveCard(Card card);

    Map<String, AchAccount> getAchAccountMap();

    Single<AchAccount> createMicroDepositAccount(CreateMicroDepositAchAccountParameters createMicroDepositAchAccountParameters);

    Single<AchAccount> verifyMicroDepositAccount(VerifyMicroDepositAchAccountParameters verifyMicroDepositAchAccountParameters);

    Single<CreateNewSubCardResponse> createSubCard(CreateSubCardParameter createSubCardParameter);

}
