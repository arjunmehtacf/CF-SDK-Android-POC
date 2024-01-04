package com.example.cf_sdk.changebankapi.source.local;

import com.example.cf_sdk.changebankapi.model.account.Account;
import com.example.cf_sdk.changebankapi.model.account.AchAccount;
import com.example.cf_sdk.changebankapi.model.account.AchTransfer;
import com.example.cf_sdk.changebankapi.model.account.Bank;
import com.example.cf_sdk.changebankapi.model.account.Card;
import com.example.cf_sdk.changebankapi.model.account.CardToCardResponse;
import com.example.cf_sdk.changebankapi.model.account.CreateNewSubCardResponse;
import com.example.cf_sdk.changebankapi.model.account.DepositedCheck;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.example.cf_sdk.changebankapi.parameter.StringParameters;
import com.example.cf_sdk.changebankapi.parameter.account.AchAccountParameters;
import com.example.cf_sdk.changebankapi.parameter.account.AchHistoryParameters;
import com.example.cf_sdk.changebankapi.parameter.account.AchTransferParameters;
import com.example.cf_sdk.changebankapi.parameter.account.BankParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CardActivationParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CardParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CheckDepositedParameters;
import com.example.cf_sdk.changebankapi.parameter.account.ConfirmAchAccountParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CreateAchAccountParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CreateMicroDepositAchAccountParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CreateSubCardParameter;
import com.example.cf_sdk.changebankapi.parameter.account.DeleteAchTransferParameters;
import com.example.cf_sdk.changebankapi.parameter.account.DirectDepositParameters;
import com.example.cf_sdk.changebankapi.parameter.account.GetAccountParameters;
import com.example.cf_sdk.changebankapi.parameter.account.SubmitMfaOrNewBankCredentialsParameters;
import com.example.cf_sdk.changebankapi.parameter.account.UpdateCardStatusParameters;
import com.example.cf_sdk.changebankapi.parameter.account.VerifyAchTransferParameters;
import com.example.cf_sdk.changebankapi.parameter.account.VerifyMicroDepositAchAccountParameters;
import com.example.cf_sdk.changebankapi.response.AccountBalanceResponse;
import com.example.cf_sdk.changebankapi.response.AccountsApiResponse;
import com.example.cf_sdk.changebankapi.response.AchAccountsApiResponse;
import com.example.cf_sdk.changebankapi.response.AchHistoryResponse;
import com.example.cf_sdk.changebankapi.response.AchTransferResponse;
import com.example.cf_sdk.changebankapi.response.BankCredentialsApiResponse;
import com.example.cf_sdk.changebankapi.response.BanksApiResponse;
import com.example.cf_sdk.changebankapi.source.datasource.AccountDatasource;
import com.example.cf_sdk.defination.response.ChangebankResponse;
import com.google.common.base.Optional;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *
 * Local stored data related to Account.
 */

public class AccountLocalDataSource implements AccountDatasource {
    @Override
    public Single<Optional<Account>> getAccountById(GetAccountParameters getAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getAccountById");
    }

    @Override
    public Single<Optional<AccountsApiResponse>> getMemberAccounts(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getMemberAccounts");
    }

    @Override
    public Single<List<DepositedCheck>> getDepositedCheck(CheckDepositedParameters checkDepositedParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveAccount");
    }

    @Override
    public Completable saveAccount(Account account) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveAccount");
    }

    @Override
    public Single<CardToCardResponse> cardToCardTransfer(CardToCardTransferParameters cardToCardTransferParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveAccount");
    }

    @Override
    public Single<Optional<AchAccountsApiResponse>> getMemberAchAccounts(AchAccountParameters achAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getMemberAchAccounts");
    }

    @Override
    public void saveBanksApiResponse(BanksApiResponse banksApiResponse) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveBanksApiResponse");
    }

    @Override
    public Single<ChangebankResponse> requestPhysicalCard(CardParameters cardParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveBanksApiResponse");
    }

    @Override
    public void saveAccountsApiResponse(AccountsApiResponse accountsApiResponse) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveAccountsApiResponse");
    }

    @Override
    public void saveAchAccountsApiResponse(AchAccountsApiResponse achAccountsApiResponse) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveAchAccountsApiResponse");
    }

    @Override
    public void saveAchAccount(AchAccount achAccount) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveAchAccount");
    }

    @Override
    public void saveBankResponse(Bank bank) {
            throw new UnsupportedOperationException(
                    "AccountLocalDataSource not support saveBankResponse");
    }

    @Override
    public void clearAchAccountsCache() {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support clearAchAccountsCache");
    }

    @Override
    public void saveBankCredentialsApiResponse(BankCredentialsApiResponse bankCredentialsApiResponse, String bankId) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveBankCredentialsApiResponse");
    }

    @Override
    public Single<AccountBalanceResponse> getAccountBalance(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getAccountBalance");
    }

    @Override
    public Single<AchTransferResponse> postAchTransfer(AchTransferParameters achTransferParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support postAchTransfer");
    }

    @Override
    public Single<AchHistoryResponse> getMemberAchHistory(AchHistoryParameters parameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getMemberAchHistory");
    }

    @Override
    public Single<AchTransfer> deleteAchTransfer(DeleteAchTransferParameters deleteAchTransferParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support deleteAchTransfer");
    }

    @Override
    public Single<Optional<BanksApiResponse>> getBanks(Parameters parameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getBanks");
    }

    @Override
    public Single<Optional<Bank>> getBank(BankParameters parameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getBank");
    }

    @Override
    public Single<AchTransfer> verifyAchTransfer(VerifyAchTransferParameters verifyAchTransferParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support verifyAchTransfer");
    }

    @Override
    public Single<Optional<BankCredentialsApiResponse>> getBankCredentials(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getBankCredentials");
    }

    @Override
    public Single<AchAccount> createAchAccount(CreateAchAccountParameters createAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support createAchAccount");
    }

    @Override
    public Single<AchAccount> getAchAccount(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getAchAccount");
    }

    @Override
    public Single<Optional<AchAccount>> deleteAchAccount(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support deleteAchAccount");
    }

    @Override
    public Single<ChangebankResponse> confirmAchAccount(ConfirmAchAccountParameters confirmAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support linkAchAccount");
    }

    @Override
    public Single<BankCredentialsApiResponse> getMfaOrNewBankCredentials(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getMfaOrNewBankCredentials");
    }

    @Override
    public Single<AchAccount> submitMfaOrNewBankCredentials(SubmitMfaOrNewBankCredentialsParameters submitMfaOrNewBankCredentialsParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support submitMfaOrNewBankCredentials");
    }

    @Override
    public Single<ChangebankResponse> refreshAchAccounts(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support refreshAchAccounts");
    }

    @Override
    public Single<AchAccount> reLinkAchAccount(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support reLinkAchAccount");
    }

    @Override
    public Single<AchAccount> unLinkAchAccount(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support unLinkAchAccount");
    }

    @Override
    public Single<ChangebankResponse> activateCard(CardActivationParameters cardActivationParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support activateCard");
    }

    @Override
    public Single<ChangebankResponse> updateCardPin(CardActivationParameters cardActivationParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support updateCardPin");
    }

    @Override
    public Single<Card> updateCardStatus(UpdateCardStatusParameters updateCardStatusParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support updateCardStatus");
    }

    @Override
    public void clearAllAccountDatasourceCache() {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support clearAllAccountDatasourceCache");
    }

    @Override
    public Single<Optional<File>> getDirectDepositForm(DirectDepositParameters directDepositParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getDirectDepositForm");
    }

    @Override
    public void saveDirectDepositForm(File file) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveDirectDepositForm");
    }

    @Override
    public Single<Optional<Card>> getCard(CardParameters cardParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getCard");
    }

    @Override
    public Single<Optional<Card>> getCardByCardId(CardParameters cardParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support getCard");
    }

    @Override
    public void saveCard(Card card) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveCard");
    }

    @Override
    public Map<String, AchAccount> getAchAccountMap() {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support saveCard");
    }

    @Override
    public Single<AchAccount> createMicroDepositAccount(CreateMicroDepositAchAccountParameters createMicroDepositAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support createMicroDepositAccount");
    }

    @Override
    public Single<AchAccount> verifyMicroDepositAccount(VerifyMicroDepositAchAccountParameters verifyMicroDepositAchAccountParameters) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support verifyMicroDepositAccount");
    }

    @Override
    public Single<CreateNewSubCardResponse> createSubCard(CreateSubCardParameter createSubCardParameter) {
        throw new UnsupportedOperationException(
                "AccountLocalDataSource not support createSubCard api response");
    }
}
