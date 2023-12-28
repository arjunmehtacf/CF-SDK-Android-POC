package com.example.cf_sdk.logic.changebankapi.network.api.mock;

import com.example.cf_sdk.logic.changebankapi.network.api.TransactionApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.model.account.Money;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.ProcessorCategory;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.StatementResponse;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.Transaction;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.TransactionResponse;
import com.example.cf_sdk.logic.changebanksdk.model.transaction.TransactionStatus;

import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Response;

/**
 *
 * Mock implementation of {@link TransactionApi}.
 */

public class MockTransactionApi implements TransactionApi {
    private static final String TAG = MockTransactionApi.class.getSimpleName();
    private static final int MONTHS_SINCE_ACCOUNT_OPENED = 12;
    private static final int TRANSACTIONS_COUNT = 1000;

    private final Logger mLogger;

    public MockTransactionApi(Logger logger) {
        mLogger = logger;
    }

    @Override
    public Single<TransactionResponse> getTransactionsByDate(
            Map<String, String> headers,
            final String from,
            final String to,
            final String cardNumber) {
        final List<String> merchants = Arrays.asList(
                "PUBLIX SUPER MAR 19221",
                ".7102 CAMPBELL RD",
                "Wal-Mart Super Center",
                "COSTCO WHSE #1022",
                "RAPID GAS 78",
                "SPROUTS FARMERS MARK");

        return null;/*Observable.range(1, TRANSACTIONS_COUNT)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        *//*mLogger.debug(TAG, "Entry getTransactionsByDate: " +
                                "from [" + from + "], " +
                                "to[" + to + "], " +
                                "page[" + page + "], " +
                                "recordsPerPage" + recordsPerPage);*//*
                    }
                })
                .map(new Function<Integer, Transaction>() {
                    @Override
                    public Transaction apply(Integer integer) throws Exception {
                        return Transaction.create(integer.toString())
                                .withTransactionAmount(new Money(integer * 10))
                                .withMerchantDetails(merchants.get(integer % merchants.size()))
                                .withDescription(merchants.get(integer % merchants.size()))
                                .withTransactionStatus(TransactionStatus.values()
                                        [integer % TransactionStatus.values().length])
                                .withProcessorCategory(integer % 2 == 0?
                                        ProcessorCategory.CREDIT :
                                        ProcessorCategory.DEBIT)
                                .withAcquireDateEpoch(convertToEpoch(DateTime.now()
                                        .withZone(DateTimeZone.UTC)
                                        .minusMonths(integer % MONTHS_SINCE_ACCOUNT_OPENED)
                                        .withDayOfMonth((integer % 25) + 1)
                                        .withHourOfDay(integer % 24)
                                        .getMillis()));
                    }
                })
                .sorted(new Comparator<Transaction>() {
                    @Override
                    public int compare(Transaction transaction, Transaction transaction2) {
                        return clamp(
                                transaction2.getDateEpoch()
                                        - transaction.getDateEpoch(),
                                1,
                                -1);
                    }

                    private int clamp(long value, int max, int min) {
                        if (value > max) {
                            return max;
                        } else if (value < min) {
                            return min;
                        } else {
                            return 0;
                        }
                    }
                })
                .filter(new Predicate<Transaction>() {
                    @Override
                    public boolean test(Transaction transaction) throws Exception {
                        DateTime fromDateTime = DateTime.parse(from);
                        DateTime toDateTime = DateTime.parse(to);
                        DateTime transactionDate = new DateTime(transaction.getDateEpoch() * 1000, DateTimeZone.UTC);
                        return transactionDate.isAfter(fromDateTime)
                                && transactionDate.isBefore(toDateTime);
                    }
                })
                *//*.skip((page - 1) * recordsPerPage)
                .take(recordsPerPage)
                .doOnNext(new Consumer<Transaction>() {
                    @Override
                    public void accept(Transaction transaction) throws Exception {
                        mLogger.debug(TAG, "getTransactionsByDate onNext: " +
                                "transaction[" + transaction + "]");
                    }
                })*//*
                .toList()
                .delay(1, TimeUnit.SECONDS);*/
    }

    @Override
    public Single<TransactionResponse> getTransactionsByStatus(Map<String, String> headers,
                                                               String cardNumber,
                                                               String dateFrom, String dateTo) {
        return Single.just(TransactionResponse.create(cardNumber));

        /*Observable.range(0, new Random().nextInt(5))
                .map(new Function<Integer, Transaction>() {
                    @Override
                    public Transaction apply(Integer integer) throws Exception {
                        return Transaction.create(integer.toString())
                                .withTransactionAmount(new Money(1044400))
                                .withMerchantDetails("Test Merchant " + integer)
                                .withDescription("Test Merchant " + integer)
                                .withProcessorCategory(ProcessorCategory.DEBIT)
                                .withAcquireDateEpoch(convertToEpoch(DateTime.now()
                                        .withZone(DateTimeZone.UTC)
                                        .getMillis()));
                    }

                    private TransactionStatus mapStatus(TransactionFilter transactionFilter) {
                        switch (transactionFilter) {
                            case COMPLETED:
                                int completedStatus = new Random().nextInt(3) + 1;
                                switch (completedStatus) {
                                    case 1:
                                        return TransactionStatus.SETTLEMENT;
                                    case 2:
                                        return TransactionStatus.CANCELLED;
                                    case 3:
                                        return TransactionStatus.REJECTED;
                                    default:
                                        return TransactionStatus.UNKNOWN;
                                }
                            case PENDING:
                                return TransactionStatus.PENDING;
                            case ALL:
                                int status = new Random().nextInt(4) + 1;
                                switch (status) {
                                    case 1:
                                        return TransactionStatus.SETTLEMENT;
                                    case 2:
                                        return TransactionStatus.CANCELLED;
                                    case 3:
                                        return TransactionStatus.REJECTED;
                                    case 4:
                                        return TransactionStatus.PENDING;
                                    default:
                                        return TransactionStatus.UNKNOWN;
                                }
                            default:
                                return TransactionStatus.UNKNOWN;

                        }
                    }
                })
                .toList();*/
    }

    @Override
    public Single<Transaction> getTransactionById(Map<String, String> headers,
                                                  String transactionId,
                                                  long transactionByIdParameters) {
        return Single.just(Transaction.create(transactionId)
                .withAcquireDateEpoch(convertToEpoch(DateTime.now().getMillis()))
                .withMerchantDetails("Fancy Place")
                .withDescription("Fancy Place")
                .withProcessorCategory(ProcessorCategory.DEBIT)
                .withTransactionAmount(new Money(10))
                .withTransactionStatus(TransactionStatus.SETTLEMENT));
    }

    @Override
    public Single<TransactionResponse> getTransactionsBySearch(
            Map<String, String> headers,
            final String searchText,
            final String from,
            final String to,
            String min,
            String max) {
        final List<String> merchants = Arrays.asList(
                "PUBLIX SUPER MAR 19221",
                ".7102 CAMPBELL RD",
                "Wal-Mart Super Center",
                "COSTCO WHSE #1022",
                "RAPID GAS 78",
                "SPROUTS FARMERS MARK");


        int count = TRANSACTIONS_COUNT;
//
//        if (searchText.length() != 0 && searchText.length() < 6) {
//            count = 21 - (searchText.length() * 2);
//        }
        long page=1231;

        return null; /*Observable.range(1, count)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "Entry getTransactionsByDate: " +
                                "from [" + from + "], " +
                                "to[" + to + "], " );
                    }
                })
                .map(new Function<Integer, Transaction>() {
                    @Override
                    public Transaction apply(Integer integer) throws Exception {
                        return Transaction.create(integer.toString())
                                .withTransactionAmount(new Money(integer * 10))
                                .withMerchantDetails(merchants.get(integer % merchants.size()))
                                .withDescription(merchants.get(integer % merchants.size()))
                                .withTransactionStatus(TransactionStatus.SETTLEMENT)
                                .withProcessorCategory(ProcessorCategory.values()
                                        [(integer % (ProcessorCategory.values().length-1)) + 1] )
                                .withAcquireDateEpoch(convertToEpoch(DateTime.now()
                                        .withZone(DateTimeZone.UTC)
                                        .minusMonths(integer % MONTHS_SINCE_ACCOUNT_OPENED)
                                        .withDayOfMonth((integer % 25) + 1)
                                        .withHourOfDay(integer % 24)
                                        .getMillis()));
                    }
                })
                .sorted(new Comparator<Transaction>() {
                    @Override
                    public int compare(Transaction transaction, Transaction transaction2) {
                        return clamp(
                                transaction2.getDateEpoch()
                                        - transaction.getDateEpoch(),
                                1,
                                -1);
                    }

                    private int clamp(long value, int max, int min) {
                        if (value > max) {
                            return max;
                        } else if (value < min) {
                            return min;
                        } else {
                            return 0;
                        }
                    }
                })
                .filter(new Predicate<Transaction>() {
                    @Override
                    public boolean test(Transaction transaction) throws Exception {
                        DateTime fromDateTime = DateTime.parse(from);
                        DateTime toDateTime = DateTime.parse(to);
                        DateTime transactionDate = new DateTime(transaction.getDateEpoch() * 1000, DateTimeZone.UTC);
                        return transactionDate.isAfter(fromDateTime)
                                && transactionDate.isBefore(toDateTime);
                    }
                })
                .skip(1200)
                .take(recordsPerPage)
                .doOnNext(new Consumer<Transaction>() {
                    @Override
                    public void accept(Transaction transaction) throws Exception {
                        mLogger.debug(TAG, "getTransactionsByDate onNext: " +
                                "transaction[" + transaction + "]");
                    }
                })
                .toList()
                .delay(1, TimeUnit.SECONDS);*/
    }

    @Override
    public Single<Response<ResponseBody>> getPdfStatements(Map<String, String> headers, String accountId, String month, String year) {
        String file = "res/raw/test.pdf"; // res/raw/test.txt also work.
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);

        Buffer bf;
        try {
            bf = new Buffer().readFrom(in);
        } catch (IOException e) {
            return Single.error(e);
        }
        return Single.just(Response.success(
                ResponseBody.create(MediaType.parse("application/pdf"),
                        bf.size(), bf)));
    }

    @Override
    public Single<StatementResponse> getTransactionStatements(Map<String, String> headers, String cardNumber, String from, String to) {
        return null;
    }


    private Long convertToEpoch(Long milliSeconds){
        return milliSeconds / 1000;
    }
}
