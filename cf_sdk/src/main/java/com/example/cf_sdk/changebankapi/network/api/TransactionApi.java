package com.example.cf_sdk.changebankapi.network.api;




import com.example.cf_sdk.changebankapi.Endpoints;
import com.example.cf_sdk.changebankapi.model.transaction.StatementResponse;
import com.example.cf_sdk.changebankapi.model.transaction.Transaction;
import com.example.cf_sdk.changebankapi.model.transaction.TransactionResponse;

import java.util.Map;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 *
 * Retrofit interface to connect with transaction api.
 */

public interface TransactionApi {

    @GET(ApiConfig.TRANSACTION_BASE_ENDPOINT + Endpoints.Transactions.GET_TRANSACTIONS_BY_DATE)
    @Headers("Accept: application/json")
    Single<TransactionResponse> getTransactionsByDate(
            @HeaderMap Map<String, String> headers,
            @Path("cardNumber") String cardNumber,
            @Path("dateFrom") String dateFrom,
            @Path(("dateTo"))String dateTo);

    @GET(ApiConfig.TRANSACTION_BASE_ENDPOINT + Endpoints.Transactions.GET_TRANSACTIONS_BY_STATUS)
    @Headers("Accept: application/json")
    Single<TransactionResponse> getTransactionsByStatus(
            @HeaderMap Map<String, String> headers,
            @Path("cardNumber") String cardNumber,
            @Path("dateFrom") String dateFrom,
            @Path(("dateTo"))String dateTo);

    @GET(ApiConfig.TRANSACTION_BASE_ENDPOINT + Endpoints.Transactions.GET_TRANSACTION_BY_ID)
    @Headers("Content-Type: application/json")
    Single<Transaction> getTransactionById(
            @HeaderMap Map<String, String> headers,
            @Path("transactionId") String transactionId,
            @Query("memberId") long transactionByIdParameters);

    @GET(ApiConfig.TRANSACTION_BASE_ENDPOINT + Endpoints.Transactions.SEARCH_TRANSACTIONS)
    @Headers("Content-Type: application/json")
    Single<TransactionResponse> getTransactionsBySearch(
            @HeaderMap Map<String, String> headers,
            @Path("cardNumber") String cardNumber,
            @Path("dateFrom") String from,
            @Path("dateTo") String to,
            @Query("minAmount") String min,
            @Query("maxAmount") String max);

    @Streaming
    @GET(ApiConfig.TRANSACTION_BASE_ENDPOINT + Endpoints.Transactions.GET_PDF_STATEMENTS)
    Single<Response<ResponseBody>> getPdfStatements(
            @HeaderMap Map<String, String> headers,
            @Path("account") String accountId,
            @Path("month") String month,
            @Path("year") String year);


    @GET(ApiConfig.TRANSACTION_BASE_ENDPOINT + Endpoints.Transactions.GET_TRANSACTION_STATEMENTS)
    @Headers("Content-Type: application/json")
    Single<StatementResponse> getTransactionStatements(
            @HeaderMap Map<String, String> headers,
            @Path("cardNumber") String cardNumber,
            @Query("dateFrom") String from,
            @Query("dateTo") String to);
}
