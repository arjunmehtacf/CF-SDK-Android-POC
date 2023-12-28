package com.example.cf_sdk.logic.changebanksdk.source;

import com.example.cf_sdk.logic.changebanksdk.model.transaction.RecentTransactionParameter;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.StatementResponse;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.Transaction;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.TransactionResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.PdfStatementsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionByIdParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionsByDateParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionsSearchParameters;
import com.google.common.base.Optional;

import java.io.File;

import io.reactivex.Single;

/**
 *
 * Datasource to retrieve transactions.
 */

public interface TransactionDatasource {

    Single<TransactionResponse> getTransactionsByStatusFilter(RecentTransactionParameter transactionByStatusParameters);

    Single<TransactionResponse> getTransactionsByDate(TransactionsByDateParameters transactionsByDateParameters);

    Single<Transaction> getTransactionById(TransactionByIdParameters transactionByIdParameters);

    Single<TransactionResponse> searchTransactions(TransactionsSearchParameters transactionsSearchParameters);

    Single<Optional<File>> getPdfStatements(PdfStatementsParameters pdfStatementsParameters);

    Single<StatementResponse> getTransactionStatements(RecentTransactionParameter recentTransactionParameter);

}
