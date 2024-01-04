package com.example.cf_sdk.changebankapi.source.datasource


import com.example.cf_sdk.changebankapi.model.transaction.RecentTransactionParameter
import com.example.cf_sdk.changebankapi.model.transaction.StatementResponse
import com.example.cf_sdk.changebankapi.model.transaction.Transaction
import com.example.cf_sdk.changebankapi.model.transaction.TransactionResponse
import com.example.cf_sdk.changebankapi.parameter.transaction.PdfStatementsParameters
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionByIdParameters
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionsByDateParameters
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionsSearchParameters
import com.google.common.base.Optional
import io.reactivex.Single
import java.io.File

/**
 * Created by victorojeda on 12/21/17.
 *
 *
 * Datasource to retrieve transactions.
 */
interface TransactionDatasource {
    fun getTransactionsByStatusFilter(transactionByStatusParameters: RecentTransactionParameter?): Single<TransactionResponse?>?
    fun getTransactionsByDate(transactionsByDateParameters: TransactionsByDateParameters?): Single<TransactionResponse?>?
    fun getTransactionById(transactionByIdParameters: TransactionByIdParameters?): Single<Transaction?>?
    fun searchTransactions(transactionsSearchParameters: TransactionsSearchParameters?): Single<TransactionResponse?>?
    fun getPdfStatements(pdfStatementsParameters: PdfStatementsParameters?): Single<out Optional<out File?>?>
    fun getTransactionStatements(recentTransactionParameter: RecentTransactionParameter?): Single<StatementResponse?>?
}