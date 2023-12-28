package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.model.transaction.RecentTransactionParameter;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.StatementResponse;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.Transaction;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.TransactionResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.PdfStatementsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionByIdParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionsByDateParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.TransactionsSearchParameters;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.google.common.base.Optional;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Repository for transactions.
 */

public class TransactionRepository implements TransactionDatasource {
    private TransactionDatasource mRemoteDatasource;

    @Inject
    public TransactionRepository(
            @Named("remote") TransactionDatasource remoteDatasource) {
        mRemoteDatasource = remoteDatasource;
    }

    @Override
    public Single<TransactionResponse> getTransactionsByStatusFilter(RecentTransactionParameter transactionByStatusParameters) {
        return mRemoteDatasource.getTransactionsByStatusFilter(transactionByStatusParameters);
    }

    @Override
    public Single<TransactionResponse> getTransactionsByDate(
            TransactionsByDateParameters transactionsByDateParameters) {
        return mRemoteDatasource.getTransactionsByDate(transactionsByDateParameters);
    }

    @Override
    public Single<Transaction> getTransactionById(
            TransactionByIdParameters transactionByIdParameters) {
        return mRemoteDatasource.getTransactionById(transactionByIdParameters);
    }

    @Override
    public Single<TransactionResponse> searchTransactions(TransactionsSearchParameters transactionsSearchParameters) {
        return mRemoteDatasource.searchTransactions(transactionsSearchParameters);
    }

    @Override
    public Single<Optional<File>> getPdfStatements(PdfStatementsParameters pdfStatementsParameters) {
        return mRemoteDatasource.getPdfStatements(pdfStatementsParameters);
    }

    @Override
    public Single<StatementResponse> getTransactionStatements(RecentTransactionParameter recentTransactionParameter) {
        return mRemoteDatasource.getTransactionStatements(recentTransactionParameter);
    }
}
