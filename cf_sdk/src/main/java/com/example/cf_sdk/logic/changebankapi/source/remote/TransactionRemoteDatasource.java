package com.example.cf_sdk.logic.changebankapi.source.remote;

import com.example.cf_sdk.logic.changebankapi.network.api.TransactionApi;
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
import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 *
 * Remote datasource to call transaction endpoints.
 */

public class TransactionRemoteDatasource implements TransactionDatasource {

    private final File mCacheDirectory;
    private TransactionApi mTransactionApi;

    public TransactionRemoteDatasource(TransactionApi transactionApi, File cacheDirectory) {
        mTransactionApi = transactionApi;
        mCacheDirectory = cacheDirectory;
    }

    @Override
    public Single<TransactionResponse> getTransactionsByStatusFilter(RecentTransactionParameter recentTransactionParameter) {
        return mTransactionApi.getTransactionsByStatus(
                recentTransactionParameter.getHeaders(),
                recentTransactionParameter.getmCardNumber(),
                recentTransactionParameter.getmDateFrom(), recentTransactionParameter.getmDateTo());
    }

    @Override
    public Single<TransactionResponse> getTransactionsByDate(
            TransactionsByDateParameters transactionsByDateParameters) {
        return mTransactionApi.getTransactionsByDate(
                transactionsByDateParameters.getHeaders(),
                transactionsByDateParameters.getCardToken(),
                transactionsByDateParameters.getFromDate(),
                transactionsByDateParameters.getToDate());
    }

    @Override
    public Single<Transaction> getTransactionById(
            TransactionByIdParameters transactionByIdParameters) {
        return mTransactionApi.getTransactionById(
                transactionByIdParameters.getHeaders(),
                transactionByIdParameters.getTransactionId(),
                transactionByIdParameters.getMemberId());
    }

    @Override
    public Single<TransactionResponse> searchTransactions(TransactionsSearchParameters transactionsSearchParameters) {
            return mTransactionApi.getTransactionsBySearch(
                    transactionsSearchParameters.getHeaders(),
                    transactionsSearchParameters.getMemberId(),
                    transactionsSearchParameters.getFromDate(),
                    transactionsSearchParameters.getToDate(),
                    transactionsSearchParameters.getMinMoney().replace("$","") ,
                    transactionsSearchParameters.getMaxMoney().replace("$",""));

    }

    @Override
    public Single<Optional<File>> getPdfStatements(final PdfStatementsParameters pdfStatementsParameters) {
        return mTransactionApi.getPdfStatements(pdfStatementsParameters.getHeaders(), pdfStatementsParameters.getMemberId(), String.valueOf(pdfStatementsParameters.getMonth()), String.valueOf(pdfStatementsParameters.getYear()))
                .flatMap(new Function<Response<ResponseBody>, SingleSource<? extends Optional<File>>>() {
                    @Override
                    public SingleSource<? extends Optional<File>> apply(Response<ResponseBody> responseBodyResponse) throws Exception {
                        String fileName = "Statement" + pdfStatementsParameters.getMonth() + pdfStatementsParameters.getYear();
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
    public Single<StatementResponse> getTransactionStatements(RecentTransactionParameter recentTransactionParameter) {
        return mTransactionApi.getTransactionStatements(recentTransactionParameter.getHeaders(),recentTransactionParameter.getmCardNumber(),recentTransactionParameter.getmDateFrom(),recentTransactionParameter.getmDateTo());
    }
}
