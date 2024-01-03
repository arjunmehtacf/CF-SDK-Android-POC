package com.example.cf_sdk.changebankapi.source.remote


import com.example.cf_sdk.changebankapi.model.account.Account
import com.example.cf_sdk.changebankapi.model.account.AchAccount
import com.example.cf_sdk.changebankapi.model.account.AchTransfer
import com.example.cf_sdk.changebankapi.model.account.Bank
import com.example.cf_sdk.changebankapi.model.account.Card
import com.example.cf_sdk.changebankapi.model.account.CardToCardResponse
import com.example.cf_sdk.changebankapi.model.account.CreateNewSubCardResponse
import com.example.cf_sdk.changebankapi.model.account.DepositedCheck
import com.example.cf_sdk.changebankapi.network.api.AccountApi
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
import com.example.sdk_no_dagger.changebankapi.model.account.AchAccountStatus
import com.example.sdk_no_dagger.changebankapi.source.datasource.AccountDatasource
import com.google.common.base.Optional
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import okhttp3.ResponseBody
import okio.Okio
import retrofit2.Response
import java.io.File
import java.io.IOException

/**
 *
 * Remote datasource to call account endpoints.
 */
class AccountRemoteDatasource(
    private val mAccountApi: AccountApi,
    private val mCacheDirectory: File
) : AccountDatasource {
    override fun getAccountById(getAccountParameters: GetAccountParameters?): Single<Optional<Account?>?>? {
        return mAccountApi.getAccountById(
            getAccountParameters!!.headers,
            getAccountParameters.accountId
        )
            .map(
                Function { account -> Optional.of(account) })
    }

    override fun cardToCardTransfer(cardToCardTransferParameters: CardToCardTransferParameters?): Single<CardToCardResponse?>? {
        return mAccountApi.cardToCardTransfer(
            cardToCardTransferParameters!!.headers,
            cardToCardTransferParameters.accountNumber,
            cardToCardTransferParameters.toAccount,
            cardToCardTransferParameters
        )
    }

    override fun getDepositedCheck(checkDepositedParameters: CheckDepositedParameters?): Single<List<DepositedCheck?>?>? {
        return mAccountApi.getDepositedCheck(
            checkDepositedParameters!!.headers,
            checkDepositedParameters
        )
    }

    override fun getMemberAccounts(stringParameters: StringParameters?): Single<Optional<AccountsApiResponse?>?>? {
        return mAccountApi.getMemberAccounts(
            stringParameters!!.headers,
            stringParameters.firstString
        )
            .map(object : Function<AccountsApiResponse?, Optional<AccountsApiResponse?>?> {
                @Throws(Exception::class)
                override fun apply(accountsApiResponse: AccountsApiResponse): Optional<AccountsApiResponse?>? {
                    return Optional.of(accountsApiResponse)
                }
            })
    }

    override fun saveAccount(account: Account?): Completable? {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveAccount"
        )
    }

    override fun getMemberAchAccounts(achAccountParameters: AchAccountParameters?): Single<Optional<AchAccountsApiResponse?>?>? {
        val status =
            if (achAccountParameters!!.achAccountStatus !== AchAccountStatus.NONE) achAccountParameters!!.achAccountStatus else null
        return mAccountApi.getMemberAchAccounts(
            achAccountParameters!!.headers,
            achAccountParameters.memberId
        )
            .map(object : Function<AchAccountsApiResponse?, Optional<AchAccountsApiResponse?>?> {
                @Throws(Exception::class)
                override fun apply(achAccountsApiResponse: AchAccountsApiResponse): Optional<AchAccountsApiResponse?>? {
                    return Optional.of(achAccountsApiResponse)
                }
            })
    }

    override fun saveBanksApiResponse(banksApiResponse: BanksApiResponse?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support banksApiResponse"
        )
    }

    override fun saveAccountsApiResponse(accountsApiResponse: AccountsApiResponse?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveAccountsApiResponse"
        )
    }

    override fun saveAchAccountsApiResponse(achAccountsApiResponse: AchAccountsApiResponse?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveAchAccountsApiResponse"
        )
    }

    override fun saveAchAccount(achAccount: AchAccount?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveAchAccount"
        )
    }

    override fun saveBankResponse(bank: Bank?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveBankResponse"
        )
    }

    override fun clearAchAccountsCache() {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support clearAchAccountsCache"
        )
    }

    override fun saveBankCredentialsApiResponse(
        bankCredentialsApiResponse: BankCredentialsApiResponse?,
        bankId: String?
    ) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveBankCredentialsApiResponse"
        )
    }

    override fun getAccountBalance(stringParameters: StringParameters?): Single<AccountBalanceResponse?>? {
        return mAccountApi.getAccountBalance(
            stringParameters!!.headers,
            stringParameters.firstString
        )
    }

    override fun postAchTransfer(achTransferParameters: AchTransferParameters?): Single<AchTransferResponse?>? {
        return mAccountApi.postAchTransfer(
            achTransferParameters!!.headers,
            achTransferParameters.achAccountId,
            achTransferParameters
        )
    }

    override fun getMemberAchHistory(parameters: AchHistoryParameters?): Single<AchHistoryResponse?>? {
        var transferTypeQueryParam: String? = null
        if (parameters!!.achTransferType != null) {
            transferTypeQueryParam = parameters.achTransferType.typeName
        }
        return mAccountApi.getMemberAchHistory(
            parameters.headers,
            parameters.memberId,
            parameters.dateFrom,
            parameters.dateTo
        )
    }

    override fun deleteAchTransfer(deleteAchTransferParameters: DeleteAchTransferParameters?): Single<AchTransfer?>? {
        return mAccountApi.deleteAchTransfer(
            deleteAchTransferParameters!!.headers,
            deleteAchTransferParameters.achTransferId
        )
    }

    override fun getBanks(parameters: Parameters?): Single<Optional<BanksApiResponse?>?>? {
        return mAccountApi.getBanks(parameters!!.headers)
            .map(object : Function<BanksApiResponse?, Optional<BanksApiResponse?>?> {
                @Throws(Exception::class)
                override fun apply(banksApiResponse: BanksApiResponse): Optional<BanksApiResponse?>? {
                    return Optional.of(banksApiResponse)
                }
            })
    }

    override fun getBank(parameters: BankParameters?): Single<Optional<Bank?>?>? {
        return mAccountApi.getBank(parameters!!.headers, parameters.bankId)
            .map(object : Function<Bank?, Optional<Bank?>?> {
                @Throws(Exception::class)
                override fun apply(bank: Bank): Optional<Bank?>? {
                    return Optional.of(bank)
                }
            })
    }

    override fun verifyAchTransfer(verifyAchTransferParameters: VerifyAchTransferParameters?): Single<AchTransfer?>? {
        return mAccountApi.verifyAchTransfer(
            verifyAchTransferParameters!!.headers,
            verifyAchTransferParameters
        )
    }

    override fun getBankCredentials(stringParameters: StringParameters?): Single<Optional<BankCredentialsApiResponse?>?>? {
        return mAccountApi.getBankCredentials(
            stringParameters!!.headers,
            stringParameters.firstString
        )
            .map(object :
                Function<BankCredentialsApiResponse?, Optional<BankCredentialsApiResponse?>?> {
                @Throws(Exception::class)
                override fun apply(bankCredentialsApiResponse: BankCredentialsApiResponse): Optional<BankCredentialsApiResponse?>? {
                    return Optional.of(bankCredentialsApiResponse)
                }
            })
    }

    override fun createAchAccount(createAchAccountParameters: CreateAchAccountParameters?): Single<AchAccount?>? {
        return mAccountApi.createAchAccount(
            createAchAccountParameters!!.headers,
            createAchAccountParameters
        )
    }

    override fun getAchAccount(stringParameters: StringParameters?): Single<AchAccount?>? {
        return mAccountApi.getAchAccount(stringParameters!!.headers, stringParameters.firstString)
    }

    override fun deleteAchAccount(stringParameters: StringParameters?): Single<Optional<AchAccount?>?>? {
        return mAccountApi.deleteAchAccount(
            stringParameters!!.headers,
            stringParameters.firstString
        )
            .map(object : Function<AchAccount?, Optional<AchAccount?>?> {
                @Throws(Exception::class)
                override fun apply(achAccount: AchAccount): Optional<AchAccount?>? {
                    return Optional.of(achAccount)
                }
            })
    }

    override fun confirmAchAccount(confirmAchAccountParameters: ConfirmAchAccountParameters?): Single<ChangebankResponse?>? {
        return mAccountApi.linkAchAccount(
            confirmAchAccountParameters!!.headers,
            confirmAchAccountParameters.achAccountId,
            confirmAchAccountParameters
        )
    }

    override fun getMfaOrNewBankCredentials(stringParameters: StringParameters?): Single<BankCredentialsApiResponse?>? {
        return mAccountApi.getMfaOrNewBankCredentials(
            stringParameters!!.headers,
            stringParameters.firstString
        )
    }

    override fun submitMfaOrNewBankCredentials(submitMfaOrNewBankCredentialsParameters: SubmitMfaOrNewBankCredentialsParameters?): Single<AchAccount?>? {
        return mAccountApi.submitMfaOrNewBankCredentials(
            submitMfaOrNewBankCredentialsParameters!!.headers,
            submitMfaOrNewBankCredentialsParameters.achAccountId,
            submitMfaOrNewBankCredentialsParameters
        )
    }

    override fun refreshAchAccounts(stringParameters: StringParameters?): Single<ChangebankResponse?>? {
        return mAccountApi.refreshAchAccounts(
            stringParameters!!.headers,
            stringParameters.firstString
        )
    }

    override fun reLinkAchAccount(stringParameters: StringParameters?): Single<AchAccount?>? {
        return mAccountApi.reLinkAchAccount(
            stringParameters!!.headers,
            stringParameters.firstString,
            Parameters.EMPTY
        )
    }

    override fun unLinkAchAccount(stringParameters: StringParameters?): Single<AchAccount?>? {
        return mAccountApi.unLinkAchAccount(
            stringParameters!!.headers,
            stringParameters.firstString
        )
    }

    override fun activateCard(cardActivationParameters: CardActivationParameters?): Single<ChangebankResponse?>? {
        return mAccountApi.activateCard(
            cardActivationParameters!!.headers,
            cardActivationParameters.cardId,
            cardActivationParameters
        )
    }

    override fun updateCardPin(cardActivationParameters: CardActivationParameters?): Single<ChangebankResponse?>? {
        return mAccountApi.updateCardPan(
            cardActivationParameters!!.headers,
            cardActivationParameters.cardId,
            cardActivationParameters
        )
    }

    override fun updateCardStatus(updateCardStatusParameters: UpdateCardStatusParameters?): Single<Card?>? {
        return mAccountApi.updateCardStatus(
            updateCardStatusParameters!!.headers,
            updateCardStatusParameters.cardNumber,
            updateCardStatusParameters
        )
    }

    override fun clearAllAccountDatasourceCache() {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support clearAllAccountDatasourceCache"
        )
    }

    override fun getDirectDepositForm(directDepositParameters: DirectDepositParameters?): Single<out Optional<out File?>?> {
        directDepositParameters ?: return Single.error<Optional<File>>(IllegalArgumentException("directDepositParameters cannot be null"))

        return mAccountApi.getDirectDepositForm(
            directDepositParameters.headers,
            directDepositParameters.accountId
        )
            .flatMap { responseBodyResponse ->
                if (responseBodyResponse.isSuccessful) {
                    val fileName = "DirectDepositForm" + directDepositParameters.accountId
                    try {
                        val file: File = File.createTempFile(fileName, ".pdf", mCacheDirectory)
                        file.deleteOnExit()
                        val sink = Okio.buffer(Okio.sink(file))

                        val responseBody = responseBodyResponse.body()
                        if (responseBody != null) {
                            sink.writeAll(responseBody.source())
                            sink.close()
                            return@flatMap Single.just(Optional.of(file))
                        } else {
                            return@flatMap Single.error<Optional<File>>(Exception("Body is null"))
                        }
                    } catch (e: IOException) {
                        return@flatMap Single.error<Optional<File>>(e)
                    }
                } else {
                    return@flatMap Single.error<Optional<File>>(Exception("Request not successful: ${responseBodyResponse.code()}"))
                }
            }
    }


    override fun saveDirectDepositForm(file: File?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveDirectDepositForm"
        )
    }

    override fun getCard(cardParameters: CardParameters?): Single<Optional<Card?>?>? {
        return mAccountApi.getCard(cardParameters!!.headers, cardParameters.cardId)
            .map(object : Function<Card?, Optional<Card?>?> {
                @Throws(Exception::class)
                override fun apply(card: Card): Optional<Card?>? {
                    return Optional.of(card)
                }
            })
    }

    override fun requestPhysicalCard(cardParameters: CardParameters?): Single<ChangebankResponse?>? {
        return mAccountApi.requestPhysicalCard(cardParameters!!.headers, cardParameters.cardId)
    }

    override fun getCardByCardId(cardParameters: CardParameters?): Single<Optional<Card?>?>? {
        return mAccountApi.getCardByCardId(cardParameters!!.headers, cardParameters.cardId)
            .map(object : Function<Card?, Optional<Card?>?> {
                @Throws(Exception::class)
                override fun apply(card: Card): Optional<Card?>? {
                    return Optional.of(card)
                }
            })
    }

    override fun saveCard(card: Card?) {
        throw UnsupportedOperationException(
            "AccountRemoteDatasource not support saveCard"
        )
    }

    override val achAccountMap: Map<String?, AchAccount?>?
        get() {
            throw UnsupportedOperationException(
                "AccountRemoteDatasource not support getAchAccountMap"
            )
        }

    override fun createMicroDepositAccount(createMicroDepositAchAccountParameters: CreateMicroDepositAchAccountParameters?): Single<AchAccount?>? {
        return mAccountApi.createMicroDepositAccount(
            createMicroDepositAchAccountParameters!!.headers,
            createMicroDepositAchAccountParameters,
            createMicroDepositAchAccountParameters.memberId
        )
    }

    override fun verifyMicroDepositAccount(verifyMicroDepositAchAccountParameters: VerifyMicroDepositAchAccountParameters?): Single<AchAccount?>? {
        return mAccountApi.verifyMicroDepositAccount(
            verifyMicroDepositAchAccountParameters!!.headers,
            verifyMicroDepositAchAccountParameters.achAccountId,
            verifyMicroDepositAchAccountParameters
        )
    }

    override fun createSubCard(createSubCardParameter: CreateSubCardParameter?): Single<CreateNewSubCardResponse?>? {
        return mAccountApi.createNewSubCard(
            createSubCardParameter!!.headers,
            createSubCardParameter.mainCardNumber,
            createSubCardParameter
        )
    }
}