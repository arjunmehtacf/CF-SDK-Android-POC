package com.example.cf_sdk.changebankapi.source;



import com.example.cf_sdk.changebankapi.model.transaction.RecentTransactionParameter;
import com.example.cf_sdk.changebankapi.model.transaction.StatementResponse;
import com.example.cf_sdk.changebankapi.model.transaction.Transaction;
import com.example.cf_sdk.changebankapi.model.transaction.TransactionResponse;
import com.example.cf_sdk.changebankapi.parameter.transaction.PdfStatementsParameters;
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionByIdParameters;
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionsByDateParameters;
import com.example.cf_sdk.changebankapi.parameter.transaction.TransactionsSearchParameters;
import com.example.sdk_no_dagger.changebankapi.source.datasource.TransactionDatasource;
import com.google.common.base.Optional;

import java.io.File;


import io.reactivex.Single;

/**
 *
 * Repository for transactions.
 */

public class TransactionRepository implements TransactionDatasource {
    private TransactionDatasource mRemoteDatasource;


    public TransactionRepository(
       TransactionDatasource remoteDatasource) {
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
        return (Single<Optional<File>>) mRemoteDatasource.getPdfStatements(pdfStatementsParameters);
    }

    @Override
    public Single<StatementResponse> getTransactionStatements(RecentTransactionParameter recentTransactionParameter) {
        return mRemoteDatasource.getTransactionStatements(recentTransactionParameter);
    }
}
