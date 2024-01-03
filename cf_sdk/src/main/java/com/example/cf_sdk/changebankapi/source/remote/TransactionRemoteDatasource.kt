package com.example.sdk_no_dagger.changebankapi.source.remote


import com.example.cf_sdk.changebankapi.model.transaction.RecentTransactionParameter
import com.example.cf_sdk.changebankapi.model.transaction.StatementResponse
import com.example.cf_sdk.changebankapi.model.transaction.Transaction
import com.example.cf_sdk.changebankapi.model.transaction.TransactionResponse
import com.example.cf_sdk.changebankapi.network.api.TransactionApi
import com.example.cf_sdk.changebankapi.parameter.transaction.PdfStatementsParameters
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionByIdParameters
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionsByDateParameters
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionsSearchParameters
import com.example.sdk_no_dagger.changebankapi.source.datasource.TransactionDatasource
import com.google.common.base.Optional
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
 * Remote datasource to call transaction endpoints.
 */
class TransactionRemoteDatasource(
    private val mTransactionApi: TransactionApi,
    private val mCacheDirectory: File
) : TransactionDatasource {
    override fun getTransactionsByStatusFilter(recentTransactionParameter: RecentTransactionParameter?): Single<TransactionResponse?>? {
        return mTransactionApi.getTransactionsByStatus(
            recentTransactionParameter!!.headers,
            recentTransactionParameter.getmCardNumber(),
            recentTransactionParameter.getmDateFrom(), recentTransactionParameter.getmDateTo()
        )
    }

    override fun getTransactionsByDate(
        transactionsByDateParameters: TransactionsByDateParameters?
    ): Single<TransactionResponse?>? {
        return mTransactionApi.getTransactionsByDate(
            transactionsByDateParameters!!.headers,
            transactionsByDateParameters.cardToken,
            transactionsByDateParameters.fromDate,
            transactionsByDateParameters.toDate
        )
    }

    override fun getTransactionById(
        transactionByIdParameters: TransactionByIdParameters?
    ): Single<Transaction?>? {
        return mTransactionApi.getTransactionById(
            transactionByIdParameters!!.headers,
            transactionByIdParameters.transactionId,
            transactionByIdParameters.memberId
        )
    }

    override fun searchTransactions(transactionsSearchParameters: TransactionsSearchParameters?): Single<TransactionResponse?>? {
        return mTransactionApi.getTransactionsBySearch(
            transactionsSearchParameters!!.headers,
            transactionsSearchParameters.memberId,
            transactionsSearchParameters.fromDate,
            transactionsSearchParameters.toDate,
            transactionsSearchParameters.minMoney.replace("$", ""),
            transactionsSearchParameters.maxMoney.replace("$", "")
        )
    }

    override fun getPdfStatements(pdfStatementsParameters: PdfStatementsParameters?): Single<out Optional<out File?>?> {
        pdfStatementsParameters ?: return Single.error<Optional<File>>(IllegalArgumentException("pdfStatementsParameters cannot be null"))

        return mTransactionApi.getPdfStatements(
            pdfStatementsParameters.headers,
            pdfStatementsParameters.memberId,
            pdfStatementsParameters.month.toString(),
            pdfStatementsParameters.year.toString()
        ).flatMap { responseBodyResponse ->
            if (responseBodyResponse.isSuccessful) {
                val fileName = "Statement${pdfStatementsParameters.month}${pdfStatementsParameters.year}"
                try {
                    val file = File.createTempFile(fileName, ".pdf", mCacheDirectory)
                    file.deleteOnExit()
                    val sink = Okio.buffer(Okio.sink(file))

                    // you can access the body of the response
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

    override fun getTransactionStatements(recentTransactionParameter: RecentTransactionParameter?): Single<StatementResponse?>? {
        return mTransactionApi.getTransactionStatements(
            recentTransactionParameter!!.headers,
            recentTransactionParameter.getmCardNumber(),
            recentTransactionParameter.getmDateFrom(),
            recentTransactionParameter.getmDateTo()
        )
    }
}