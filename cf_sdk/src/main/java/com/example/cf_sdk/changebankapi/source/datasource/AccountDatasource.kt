package com.example.sdk_no_dagger.changebankapi.source.datasource


import com.example.cf_sdk.changebankapi.model.account.Account
import com.example.cf_sdk.changebankapi.model.account.AchAccount
import com.example.cf_sdk.changebankapi.model.account.AchTransfer
import com.example.cf_sdk.changebankapi.model.account.Bank
import com.example.cf_sdk.changebankapi.model.account.Card
import com.example.cf_sdk.changebankapi.model.account.CardToCardResponse
import com.example.cf_sdk.changebankapi.model.account.CreateNewSubCardResponse
import com.example.cf_sdk.changebankapi.model.account.DepositedCheck
import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.parameter.StringParameters
import com.example.cf_sdk.changebankapi.parameter.account.AchAccountParameters
import com.example.cf_sdk.changebankapi.parameter.account.AchHistoryParameters
import com.example.cf_sdk.changebankapi.parameter.account.AchTransferParameters
import com.example.cf_sdk.changebankapi.parameter.account.BankParameters
import com.example.cf_sdk.changebankapi.parameter.account.CardActivationParameters
import com.example.cf_sdk.changebankapi.parameter.account.CardParameters
import com.example.cf_sdk.changebankapi.parameter.account.CardToCardTransferParameters
import com.example.cf_sdk.changebankapi.parameter.account.CheckDepositedParameters
import com.example.cf_sdk.changebankapi.parameter.account.ConfirmAchAccountParameters
import com.example.cf_sdk.changebankapi.parameter.account.CreateAchAccountParameters
import com.example.cf_sdk.changebankapi.parameter.account.CreateMicroDepositAchAccountParameters
import com.example.cf_sdk.changebankapi.parameter.account.CreateSubCardParameter
import com.example.cf_sdk.changebankapi.parameter.account.DeleteAchTransferParameters
import com.example.cf_sdk.changebankapi.parameter.account.DirectDepositParameters
import com.example.cf_sdk.changebankapi.parameter.account.GetAccountParameters
import com.example.cf_sdk.changebankapi.parameter.account.SubmitMfaOrNewBankCredentialsParameters
import com.example.cf_sdk.changebankapi.parameter.account.UpdateCardStatusParameters
import com.example.cf_sdk.changebankapi.parameter.account.VerifyAchTransferParameters
import com.example.cf_sdk.changebankapi.parameter.account.VerifyMicroDepositAchAccountParameters
import com.example.cf_sdk.changebankapi.response.AccountBalanceResponse
import com.example.cf_sdk.changebankapi.response.AccountsApiResponse
import com.example.cf_sdk.changebankapi.response.AchAccountsApiResponse
import com.example.cf_sdk.changebankapi.response.AchHistoryResponse
import com.example.cf_sdk.changebankapi.response.AchTransferResponse
import com.example.cf_sdk.changebankapi.response.BankCredentialsApiResponse
import com.example.cf_sdk.changebankapi.response.BanksApiResponse
import com.example.cf_sdk.changebankapi.response.ChangebankResponse
import com.google.common.base.Optional
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File

/**
 * Created by gunveernatt on 10/4/17.
 *
 *
 * Datasource to provide account data.
 */
interface AccountDatasource {
    fun getAccountById(getAccountParameters: GetAccountParameters?): Single<Optional<Account?>?>?
    fun getMemberAccounts(stringParameters: StringParameters?): Single<Optional<AccountsApiResponse?>?>?
    fun getDepositedCheck(checkDepositedParameters: CheckDepositedParameters?): Single<List<DepositedCheck?>?>?
    fun saveAccount(account: Account?): Completable?
    fun getMemberAchAccounts(achAccountParameters: AchAccountParameters?): Single<Optional<AchAccountsApiResponse?>?>?
    fun saveBanksApiResponse(banksApiResponse: BanksApiResponse?)
    fun saveAccountsApiResponse(accountsApiResponse: AccountsApiResponse?)
    fun saveAchAccountsApiResponse(achAccountsApiResponse: AchAccountsApiResponse?)
    fun saveAchAccount(achAccount: AchAccount?)
    fun saveBankResponse(bank: Bank?)
    fun clearAchAccountsCache()
    fun saveBankCredentialsApiResponse(
        bankCredentialsApiResponse: BankCredentialsApiResponse?,
        bankId: String?
    )

    fun getAccountBalance(stringParameters: StringParameters?): Single<AccountBalanceResponse?>?
    fun postAchTransfer(achTransferParameters: AchTransferParameters?): Single<AchTransferResponse?>?
    fun getMemberAchHistory(parameters: AchHistoryParameters?): Single<AchHistoryResponse?>?
    fun deleteAchTransfer(deleteAchTransferParameters: DeleteAchTransferParameters?): Single<AchTransfer?>?
    fun getBanks(parameters: Parameters?): Single<Optional<BanksApiResponse?>?>?
    fun getBank(parameters: BankParameters?): Single<Optional<Bank?>?>?
    fun verifyAchTransfer(verifyAchTransferParameters: VerifyAchTransferParameters?): Single<AchTransfer?>?
    fun getBankCredentials(stringParameters: StringParameters?): Single<Optional<BankCredentialsApiResponse?>?>?
    fun createAchAccount(createAchAccountParameters: CreateAchAccountParameters?): Single<AchAccount?>?
    fun getAchAccount(stringParameters: StringParameters?): Single<AchAccount?>?
    fun deleteAchAccount(stringParameters: StringParameters?): Single<Optional<AchAccount?>?>?
    fun confirmAchAccount(confirmAchAccountParameters: ConfirmAchAccountParameters?): Single<ChangebankResponse?>?
    fun getMfaOrNewBankCredentials(stringParameters: StringParameters?): Single<BankCredentialsApiResponse?>?
    fun submitMfaOrNewBankCredentials(submitMfaOrNewBankCredentialsParameters: SubmitMfaOrNewBankCredentialsParameters?): Single<AchAccount?>?
    fun refreshAchAccounts(stringParameters: StringParameters?): Single<ChangebankResponse?>?
    fun reLinkAchAccount(stringParameters: StringParameters?): Single<AchAccount?>?
    fun cardToCardTransfer(cardToCardTransferParameters: CardToCardTransferParameters?): Single<CardToCardResponse?>?
    fun unLinkAchAccount(stringParameters: StringParameters?): Single<AchAccount?>?
    fun activateCard(cardActivationParameters: CardActivationParameters?): Single<ChangebankResponse?>?
    fun updateCardPin(cardActivationParameters: CardActivationParameters?): Single<ChangebankResponse?>?
    fun updateCardStatus(updateCardStatusParameters: UpdateCardStatusParameters?): Single<Card?>?
    fun clearAllAccountDatasourceCache()
    fun getDirectDepositForm(directDepositParameters: DirectDepositParameters?): Single<out Optional<out File?>?>
    fun saveDirectDepositForm(file: File?)
    fun getCard(cardParameters: CardParameters?): Single<Optional<Card?>?>?
    fun getCardByCardId(cardParameters: CardParameters?): Single<Optional<Card?>?>?
    fun requestPhysicalCard(cardParameters: CardParameters?): Single<ChangebankResponse?>?
    fun saveCard(card: Card?)
    val achAccountMap: Map<String?, AchAccount?>?
    fun createMicroDepositAccount(createMicroDepositAchAccountParameters: CreateMicroDepositAchAccountParameters?): Single<AchAccount?>?
    fun verifyMicroDepositAccount(verifyMicroDepositAchAccountParameters: VerifyMicroDepositAchAccountParameters?): Single<AchAccount?>?
    fun createSubCard(createSubCardParameter: CreateSubCardParameter?): Single<CreateNewSubCardResponse?>?
}