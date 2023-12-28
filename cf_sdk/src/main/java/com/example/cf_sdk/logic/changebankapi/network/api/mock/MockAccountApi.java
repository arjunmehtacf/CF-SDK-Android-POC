package com.example.cf_sdk.logic.changebankapi.network.api.mock;

import com.example.cf_sdk.logic.changebankapi.network.api.AccountApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.model.account.AccountStatus;
import com.example.cf_sdk.logic.changebanksdk.model.account.AccountType;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccountStatus;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransfer;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransferStatus;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransferType;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.model.account.BankType;
import com.example.cf_sdk.logic.changebanksdk.model.account.Card;
import com.example.cf_sdk.logic.changebanksdk.model.account.CardStatus;
import com.example.cf_sdk.logic.changebanksdk.model.account.CardToCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.account.CreateNewSubCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.account.Credential;
import com.example.cf_sdk.logic.changebanksdk.model.account.CredentialType;
import com.example.cf_sdk.logic.changebanksdk.model.account.DepositedCheck;
import com.example.cf_sdk.logic.changebanksdk.model.account.Money;
import com.example.cf_sdk.logic.changebanksdk.model.account.Option;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardActivationParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CheckDepositedParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.ConfirmAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateMicroDepositAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateSubCardParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.SubmitMfaOrNewBankCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.UpdateCardStatusParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.VerifyAchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.VerifyMicroDepositAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AccountBalanceResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchAccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchHistoryResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchTransferResponse;
import com.example.cf_sdk.logic.changebanksdk.response.BankCredentialsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.BanksApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;

import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Response;

/**
 *
 * Mock implementation of {@link AccountApi}.
 */

public class MockAccountApi implements AccountApi {
    private final static String TAG = MockAccountApi.class.getSimpleName();
    private final static String DEFAULT_ACH_TRANSFER_VERIFICATION_ID = "00000";
    private final static String ACH_LINKING_TWO_FACTOR_AUTHENTICATION = "2fa";
    private static final String PERSON_IMAGE_URL = "http://tallclub.co.uk/wp-content/uploads/2011/04/Tall-Person-1.png";
    private static final String ACH_TRANSFER_VERIFICATION_CODE = "00000";

    private static final int ACH_TWO_FACTOR_OPTIONS = 1;
    private static final int ACH_TWO_FACTOR_IMAGE = 2;
    private static final int ACH_TWO_FACTOR_TOTAL = 2;

    private final Logger mLogger;

    public MockAccountApi(Logger logger) {
        mLogger = logger;
    }

    @Override
    public Single<Account> getAccountById(Map<String, String> headers, final String accountId) {
        Single<List<Card>> cardsObservable = Observable.range(0, CardStatus.values().length)
                .map(new Function<Integer, Card>() {
                    @Override
                    public Card apply(Integer integer) throws Exception {
                        return Card.create(integer.toString(),
                                "1234",
                                "04-29",
                                CardStatus.values()[integer])
                                .withAdminNumber("1234")
                                .withAccountNumber("123456")
                                .withAccountId(accountId);
                    }
                })
                .toList();

        Single<Account> accountObservable = Single
                .fromCallable(new Callable<Account>() {
                    @Override
                    public Account call() throws Exception {
                        return Account.create(
                                accountId,
                                "123456781234" + (Integer.valueOf(accountId) * 1000),
                                "10")
                                .withAccountStatus(AccountStatus.values()[Integer.parseInt(accountId) - 1])
                                .withAccountType(AccountType.CHEQUE)
                                .withOpeningDate(DateTime.now().minusYears(1).getMillis())
                                .withIsCCProcessor(true);
                    }
                });

        return Single.zip(
                cardsObservable,
                accountObservable,
                new BiFunction<List<Card>, Account, Account>() {
                    @Override
                    public Account apply(List<Card> cards, Account account) throws Exception {
                        return account.withCardsList(cards);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getAccountById onSubscribe: " +
                                "accountId[" + accountId + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Account>() {
                    @Override
                    public void accept(Account account) throws Exception {
                        mLogger.debug(TAG, "getAccountById onNext: account[" + account + "]");
                    }
                });
    }

    @Override
    public Single<AccountsApiResponse> getMemberAccounts(
            Map<String, String> headers,
            final String memberId) {
        return Observable
                .range(1, AccountStatus.values().length)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getMemberAccounts onSubscribe: " +
                                "memberId[" + memberId + "]");
                    }
                })
                .map(new Function<Integer, Account>() {
                    @Override
                    public Account apply(Integer integer) throws Exception {
                        return Account.create(
                                integer.toString(),
                                "" + (integer * 1000),
                                null)
                                .withAccountStatus(AccountStatus.values()[integer - 1])
                                .withAccountType(AccountType.CHEQUE)
                                .withIsCCProcessor(true)
                                .withOpeningDate(DateTime.now().minusYears(1).getMillis());
                    }
                })
                .toList()
                .map(new Function<List<Account>, AccountsApiResponse>() {
                    @Override
                    public AccountsApiResponse apply(List<Account> accounts) throws Exception {
                        return AccountsApiResponse.create(accounts);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AccountsApiResponse>() {
                    @Override
                    public void accept(AccountsApiResponse accountsApiResponse) throws Exception {
                        mLogger.debug(TAG, "getAccountById onNext: " +
                                "account[" + accountsApiResponse + "]");
                    }
                });
    }

    @Override
    public Single<CardToCardResponse> cardToCardTransfer(Map<String, String> headers,String account,String toAccount, CardToCardTransferParameters cardToCardTransferParameters) {
        return null;
    }

    @Override
    public Single<List<DepositedCheck>> getDepositedCheck(Map<String, String> headers, CheckDepositedParameters checkDepositedParameters) {
        return null;
    }

    @Override
    public Single<AchAccountsApiResponse> getMemberAchAccounts(
            Map<String, String> headers,
            final String memberId) {
        return Single.just(memberId)
                .map(new Function<String, AchAccountsApiResponse>() {
                    @Override
                    public AchAccountsApiResponse apply(String memberId) throws Exception {
                        List<AchAccount> achAccounts = new ArrayList<>();

                        AchAccount achAccount1 = AchAccount.create("ABC")
                                .withStatus(AchAccountStatus.UNAUTHENTICATED)
                                .withAccountNumber("" + (1000))
                                .withAccountType(AccountType.CHEQUE)
                                .withBalance(new Money(10))
                                .withBankId(String.valueOf(3));

                        AchAccount achAccount2 = AchAccount.create("BCD")
                                .withStatus(AchAccountStatus.FAILED)
                                .withAccountNumber("" + (2000))
                                .withAccountType(AccountType.CHEQUE)
                                .withBalance(new Money(10))
                                .withBankId(String.valueOf(3));

                        AchAccount achAccount3 = AchAccount.create("CDE")
                                .withStatus(AchAccountStatus.LINKED)
                                .withAccountNumber("" + (3000))
                                .withAccountType(AccountType.CHEQUE)
                                .withBalance(new Money(10))
                                .withBankId(String.valueOf(3));

                        AchAccount achAccount4 = AchAccount.create("DEF")
                                .withStatus(AchAccountStatus.UNLINKED)
                                .withAccountNumber("" + (4000))
                                .withAccountType(AccountType.CHEQUE)
                                .withBalance(new Money(10))
                                .withBankId(String.valueOf(3));

                        AchAccount achAccount5 = AchAccount.create("EFG")
                                .withStatus(AchAccountStatus.DELETED)
                                .withAccountNumber("" + (5000))
                                .withAccountType(AccountType.CHEQUE)
                                .withBalance(new Money(10))
                                .withBankId(String.valueOf(3));

                        AchAccount achAccount6 = AchAccount.create("FGH")
                                .withStatus(AchAccountStatus.LOCKED)
                                .withAccountNumber("" + (6000))
                                .withAccountType(AccountType.CHEQUE)
                                .withBalance(new Money(10))
                                .withBankId(String.valueOf(3));

                        achAccounts.add(achAccount1);
                        achAccounts.add(achAccount2);
                        achAccounts.add(achAccount3);
                        achAccounts.add(achAccount4);
                        achAccounts.add(achAccount5);
                        achAccounts.add(achAccount6);


                        return AchAccountsApiResponse.create(achAccounts);
                    }
                })


                /*      .map(new Function<Integer, AchAccount>() {
                          @Override
                          public AchAccount apply(Integer integer) throws Exception {
                              return AchAccount.create(integer.toString())
                                      .withStatus(AchAccountStatus.values()[new Random().nextInt(AchAccountStatus.values().length - 1)])
                                      .withAccountNumber("" + (integer * 1000))
                                      .withAccountType(AccountType.CHECKING)
                                      .withBalance(new Money(integer * 10))
                                      .withBankId(String.valueOf(integer * 3));
                          }
                      })
                      .toList()
                .map(new Function<List<AchAccount>, AchAccountsApiResponse>() {
                    @Override
                    public AchAccountsApiResponse apply(List<AchAccount> achAccounts) throws Exception {
                        return AchAccountsApiResponse.create(achAccounts);
                    }
                })*/
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchAccountsApiResponse>() {
                    @Override
                    public void accept(AchAccountsApiResponse achAccountsApiResponse) throws Exception {
                        mLogger.debug(TAG, "getMemberAchAccounts onNext: " +
                                "achAccountsApiResponse[" + achAccountsApiResponse + "]");
                    }
                });
    }


    @Override
    public Single<AchAccount> getAcountToAcount(Map<String, String> headers, String accountId, String adminNumber, double amount, String last$Digit) {
        return null;
    }

    @Override
    public Single<AccountBalanceResponse> getAccountBalance(
            Map<String, String> headers,
            final String accountId) {
        return Single
                .fromCallable(new Callable<AccountBalanceResponse>() {
                    @Override
                    public AccountBalanceResponse call() throws Exception {
                        return AccountBalanceResponse.create(
                                accountId,
                                new Money(Integer.valueOf(accountId) * 10));
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getAccountBalance onSubscribe: " +
                                "accountId[" + accountId + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AccountBalanceResponse>() {
                    @Override
                    public void accept(AccountBalanceResponse accountBalanceResponse) throws Exception {
                        mLogger.debug(TAG, "getAccountBalance onNext: " +
                                "accountBalanceResponse[" + accountBalanceResponse + "]");
                    }
                });
    }

    @Override
    public Single<AchTransferResponse> postAchTransfer(
            Map<String, String> headers,String accountid,
            final AchTransferParameters achTransferParameters) {
        return Single.just(achTransferParameters)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "postAchTransfer onSubscribe: " +
                                "achTransferParameters[" + achTransferParameters + "]");
                    }
                })
                .map(new Function<AchTransferParameters, AchTransfer>() {
                    @Override
                    public AchTransfer apply(AchTransferParameters achTransferParameters) throws Exception {
                        return AchTransfer.create(
                                String.valueOf(new Random().nextLong()),
                                achTransferParameters.getMemberId(),
                                achTransferParameters.getTransferType(),
                                achTransferParameters.getAmount(),
                                achTransferParameters.getAchAccountId(),
                                achTransferParameters.getAccountId(),
                                achTransferParameters.getAchAccount().getAccountNumber(),
                                achTransferParameters.getAccount().getLast4AccountNumber());
                    }
                })
                .map(new Function<AchTransfer, AchTransferResponse>() {
                    @Override
                    public AchTransferResponse apply(AchTransfer achTransfer) throws Exception {
                        return AchTransferResponse.create(
                                achTransfer,
                                DEFAULT_ACH_TRANSFER_VERIFICATION_ID);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchTransferResponse>() {
                    @Override
                    public void accept(AchTransferResponse achTransferResponse) throws Exception {
                        mLogger.debug(TAG, "postAchTransfer onNext: " +
                                "achTransferResponse[" + achTransferResponse + "]");
                    }
                });
    }

    @Override
    public Single<AchHistoryResponse> getMemberAchHistory(
            Map<String, String> headers,
            final String memberId,
            final String transferType,String todate) {
        return Observable.range(1, new Random().nextInt(100))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getMemberAchHistory onSubscribe: " +
                                "memberId[" + memberId
                                + "], transferType[" + transferType + "]");
                    }
                })
                .map(new Function<Integer, AchTransfer>() {
                    @Override
                    public AchTransfer apply(Integer integer) throws Exception {
                        return AchTransfer.create(
                                String.valueOf(integer),
                                Long.valueOf(memberId),
                                AchTransferType.values()[new Random().nextInt(AchTransferType.values().length - 1)],
                                "100",
                                String.valueOf(new Random().nextInt(10)),
                                String.valueOf(new Random().nextInt(10) + 10),
                                String.valueOf(integer * 1000),
                                String.valueOf(integer * 10202))
                                .withStatus(AchTransferStatus.values()[new Random().nextInt(AchTransferStatus.values().length - 1)]);
                    }
                })
                .filter(new Predicate<AchTransfer>() {
                    @Override
                    public boolean test(AchTransfer achTransfer) throws Exception {
                        return transferType == null ||
                                achTransfer.getStatus().getStatusName().equals(transferType);
                    }
                })
                .toList()
                .map(new Function<List<AchTransfer>, AchHistoryResponse>() {
                    @Override
                    public AchHistoryResponse apply(List<AchTransfer> achTransfers) throws Exception {
                        return AchHistoryResponse.create(achTransfers);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchHistoryResponse>() {
                    @Override
                    public void accept(AchHistoryResponse achHistoryResponse) throws Exception {
                        mLogger.debug(TAG, "getMemberAchHistory onNext: " +
                                "achHistoryResponse[" + achHistoryResponse + "]");
                    }
                });
    }

    @Override
    public Single<AchTransfer> deleteAchTransfer(
            Map<String, String> headers,
            final String achTransferId) {
        return Single.just(achTransferId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "deleteAchTransfer onSubscribe: " +
                                "achTransferId[" + achTransferId + "]");
                    }
                })
                .map(new Function<String, AchTransfer>() {
                    @Override
                    public AchTransfer apply(String id) throws Exception {
                        return AchTransfer.create(
                                id,
                                Long.valueOf(id),
                                AchTransferType.values()[new Random().nextInt(AchTransferType.values().length - 1)],
                                "10",
                                String.valueOf(new Random().nextInt(10)),
                                String.valueOf(new Random().nextInt(10) + 10),
                                String.valueOf(new Random().nextInt(10) * 1000),
                                String.valueOf(new Random().nextInt(10) * 10202))
                                .withUpdatedDataAfterTransferCanceled(
                                        AchTransferStatus.CANCELLED,
                                        DateTime.now().getMillis(),
                                        DateTime.now().getMillis());
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchTransfer>() {
                    @Override
                    public void accept(AchTransfer achTransfer) throws Exception {
                        mLogger.debug(TAG, "deleteAchTransfer onNext: " +
                                "achTransfer[" + achTransfer + "]");
                    }
                });
    }

    @Override
    public Single<BanksApiResponse> getBanks(Map<String, String> headers) {
        return Observable.range(1, new Random().nextInt(20))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getBanks onSubscribe");
                    }
                })
                .map(new Function<Integer, Bank>() {
                    @Override
                    public Bank apply(Integer integer) throws Exception {
                        return Bank.create(
                                integer.toString(),
                                "John's bank " + integer,
                                BankType.ACH_BANK);
                    }
                })
                .toList()
                .map(new Function<List<Bank>, BanksApiResponse>() {
                    @Override
                    public BanksApiResponse apply(List<Bank> banks) throws Exception {
                        return BanksApiResponse.create(banks);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<BanksApiResponse>() {
                    @Override
                    public void accept(BanksApiResponse banksApiResponse) throws Exception {
                        mLogger.debug(TAG, "getBanks onNext: " +
                                "banksApiResponse[" + banksApiResponse + "]");
                    }
                });
    }

    @Override
    public Single<Bank> getBank(Map<String, String> headers, final String bankId) {
        return Single.just(bankId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getBank: bankId[" + bankId + "]");
                    }
                })
                .map(new Function<String, Bank>() {
                    @Override
                    public Bank apply(String id) throws Exception {
                        return Bank.create(id, "John's bank " + id, BankType.ACH_BANK)
                                .withMxId(id);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Bank>() {
                    @Override
                    public void accept(Bank bank) throws Exception {
                        mLogger.debug(TAG, "getBank onNext: bank[" + bank + "]");
                    }
                });
    }


    @Override
    public Single<AchTransfer> verifyAchTransfer(
            Map<String, String> headers,
            final VerifyAchTransferParameters verifyAchTransferParameters) {
        return Single.just(verifyAchTransferParameters)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "verifyAchTransfer onSubscribe: " +
                                "verifyAchTransferParameters[" + verifyAchTransferParameters + "]");
                    }
                })
                .flatMap(new Function<VerifyAchTransferParameters, SingleSource<? extends AchTransfer>>() {
                    @Override
                    public SingleSource<? extends AchTransfer> apply(VerifyAchTransferParameters verifyAchTransferParameters) throws Exception {
                        String code = verifyAchTransferParameters.getVerificationCode();
                        if (ACH_TRANSFER_VERIFICATION_CODE.equals(code)) {
                            return Single.just(AchTransfer.create(
                                    verifyAchTransferParameters.getAchTransferId(),
                                    verifyAchTransferParameters.getMemberId(),
                                    AchTransferType.BANK_TO_CARD,
                                    "10",
                                    "1234",
                                    "12345",
                                    "123456",
                                    "87654"));
                        } else {
                            return Single.error(new Exception("Code not valid"));
                        }
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchTransfer>() {
                    @Override
                    public void accept(AchTransfer achTransfer) throws Exception {
                        mLogger.debug(TAG, "verifyAchTransfer onNext: achTransfer["
                                + achTransfer + "]");
                    }
                });
    }

    @Override
    public Single<BankCredentialsApiResponse> getBankCredentials(
            Map<String, String> headers,
            final String bankId) {
        return Observable.range(1, 2)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getBankCredentials onSubscribe: " +
                                "bankId[" + bankId + "]");
                    }
                })
                .map(new Function<Integer, Credential>() {
                    @Override
                    public Credential apply(Integer integer) throws Exception {
                        if (integer == 1) {
                            return Credential.createForLogin(
                                    integer.toString(),
                                    "abc",
                                    "",
                                    integer,
                                    "Username " + integer);
                        } else {
                            return Credential.createForPassword(
                                    integer.toString(),
                                    "abc",
                                    "",
                                    integer,
                                    "Password " + integer);
                        }
                    }
                })
                .collect(
                        new Callable<ArrayList<Credential>>() {
                            @Override
                            public ArrayList<Credential> call() throws Exception {
                                return new ArrayList<>();
                            }
                        },
                        new BiConsumer<ArrayList<Credential>, Credential>() {
                            @Override
                            public void accept(ArrayList<Credential> credentials, Credential credential) throws Exception {
                                credentials.add(credential);
                            }
                        })
                .map(new Function<ArrayList<Credential>, BankCredentialsApiResponse>() {
                    @Override
                    public BankCredentialsApiResponse apply(ArrayList<Credential> credentials) throws Exception {
                        return BankCredentialsApiResponse.create(credentials);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<BankCredentialsApiResponse>() {
                    @Override
                    public void accept(BankCredentialsApiResponse bankCredentialsApiResponse) throws Exception {
                        mLogger.debug(TAG, "getBankCredentials onNext: " +
                                "bankCredentialsApiResponse[" + bankCredentialsApiResponse + "]");
                    }
                });
    }

    @Override
    public Single<AchAccount> createAchAccount(
            Map<String, String> headers,
            final CreateAchAccountParameters createAchAccountParameters) {
        return Observable.fromIterable(createAchAccountParameters.getCredentials())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "createAchAccount onSubscribe: " +
                                "createAchAccountParameters [" + createAchAccountParameters + "]");
                    }
                })
                .filter(new Predicate<Credential>() {
                    @Override
                    public boolean test(Credential credential) throws Exception {
                        return credential.getFieldTypeName() != CredentialType.LOGIN;
                    }
                })
                .flatMap(new Function<Credential, ObservableSource<AchAccount>>() {
                    @Override
                    public ObservableSource<AchAccount> apply(Credential credential) throws Exception {
                        switch (credential.getFieldTypeName()) {
                            case PASSWORD:
                                if (ACH_LINKING_TWO_FACTOR_AUTHENTICATION.equals(credential.getValue())) {
                                    return Observable.just(AchAccount
                                            .create("12345Id")
                                            .withStatus(AchAccountStatus.MFA_REQUIRED)
                                            .withAccountType(AccountType.CHEQUE));
                                }
                                break;
                        }

                        return Observable.just(AchAccount.create("123")
                                .withBankId("100020")
                                .withAccountType(AccountType.CHEQUE)
                                .withStatus(AchAccountStatus.AUTHENTICATING));
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .singleOrError()
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "createAchAccount onNext: " +
                                "achAccount[" + achAccount + "]");
                    }
                });

    }

    @Override
    public Single<AchAccount> getAchAccount(
            Map<String, String> headers,
            final String achAccountId) {
        return Single.just(achAccountId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getAchAccount onSubscribe: " +
                                "achAccountId [" + achAccountId + "]");
                    }
                })
                .map(new Function<String, AchAccount>() {
                    @Override
                    public AchAccount apply(String id) throws Exception {
                        return AchAccount.create(id)
                                .withBalance(new Money(100000))
                                .withBankId("12345")
                                .withStatus(AchAccountStatus.LINKED);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "getAchAccount onNext: achAccount [" + achAccount + "]");
                    }
                });
    }

    @Override
    public Single<BankCredentialsApiResponse> getMfaOrNewBankCredentials(
            Map<String, String> headers,
            final String achAccountId) {
        return Observable.just(new Random().nextInt(ACH_TWO_FACTOR_TOTAL))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getMfaOrNewBankCredentials onSubscribe: " +
                                "achAccountId [" + achAccountId + "]");
                    }
                })
                .map(new Function<Integer, Credential>() {
                    @Override
                    public Credential apply(Integer integer) throws Exception {
                        switch (integer) {
                            case ACH_TWO_FACTOR_OPTIONS:
                                List<Option> options = new ArrayList<>();
                                options.add(new Option("abcopi", "correct", "correct"));
                                options.add(new Option("abc123", "correct", "correct"));
                                options.add(new Option("abcfgh", "correct", "correct"));
                                options.add(new Option("abccvb", "correct", "correct"));

                                return Credential.createForOptions(
                                        "CRD-a4ba0739-3ede-ca43-4f0a-3a046b67cfa0",
                                        "INS-1572a04c-912b-59bf-5841-332c7dfafaef",
                                        "",
                                        0,
                                        "Choose one?",
                                        options);

                            case ACH_TWO_FACTOR_IMAGE:
                            default:
                                return Credential.createForImage(
                                        "CRD-a4ba0739-3ede-ca43-4f0a-3a046b67cfa0",
                                        "INS-1572a04c-912b-59bf-5841-332c7dfafaef",
                                        "",
                                        0,
                                        "Who is this guy?",
                                        PERSON_IMAGE_URL);
                        }
                    }
                })
                .collect(
                        new Callable<ArrayList<Credential>>() {
                            @Override
                            public ArrayList<Credential> call() throws Exception {
                                return new ArrayList<>();
                            }
                        },
                        new BiConsumer<ArrayList<Credential>, Credential>() {
                            @Override
                            public void accept(ArrayList<Credential> credentials,
                                               Credential credential) throws Exception {
                                credentials.add(credential);
                            }
                        })
                .map(new Function<ArrayList<Credential>, BankCredentialsApiResponse>() {
                    @Override
                    public BankCredentialsApiResponse apply(ArrayList<Credential> credentials) throws Exception {
                        return BankCredentialsApiResponse.create(credentials);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<BankCredentialsApiResponse>() {
                    @Override
                    public void accept(BankCredentialsApiResponse bankCredentialsApiResponse) throws Exception {
                        mLogger.debug(TAG, "getMfaOrNewBankCredentials onNext: " +
                                "bankCredentialsApiResponse [" + bankCredentialsApiResponse + "]");
                    }
                });
    }

    @Override
    public Single<AchAccount> submitMfaOrNewBankCredentials(
            Map<String, String> headers,
            final String achAccountId,
            final SubmitMfaOrNewBankCredentialsParameters submitMfaOrNewBankCredentialsParameters) {
        return Observable.fromIterable(submitMfaOrNewBankCredentialsParameters.getCredentials())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "submitMfaOrNewBankCredentials onSubscribe: " +
                                "submitMfaOrNewBankCredentialsParameters ["
                                + submitMfaOrNewBankCredentialsParameters + "]");
                    }
                })
                .filter(new Predicate<Credential>() {
                    @Override
                    public boolean test(Credential credential) throws Exception {
                        return credential.getFieldTypeName() != CredentialType.LOGIN;
                    }
                })
                .flatMap(new Function<Credential, ObservableSource<AchAccount>>() {
                    @Override
                    public ObservableSource<AchAccount> apply(Credential credential) throws Exception {
                        switch (credential.getFieldTypeName()) {
                            case PASSWORD:
                                if (ACH_LINKING_TWO_FACTOR_AUTHENTICATION.equals(credential.getValue())) {
                                    return Observable.just(AchAccount
                                            .create(achAccountId)
                                            .withStatus(AchAccountStatus.MFA_REQUIRED)
                                            .withAccountType(AccountType.CHEQUE));
                                }
                                break;
                        }

                        return Observable.just(AchAccount.create(achAccountId)
                                .withBankId("100020")
                                .withAccountType(AccountType.CHEQUE)
                                .withStatus(AchAccountStatus.AUTHENTICATING));
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .singleOrError()
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "submitMfaOrNewBankCredentials onNext: achAccount["
                                + achAccount + "]");
                    }
                });
    }

    @Override
    public Single<AchAccount> deleteAchAccount(
            Map<String, String> headers,
            final String achAccountId) {
        return Single.just(achAccountId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "deleteAchAccount onSubscribe: " +
                                "achAccountId[" + achAccountId + "]");
                    }
                })
                .map(new Function<String, AchAccount>() {
                    @Override
                    public AchAccount apply(String id) throws Exception {
                        return AchAccount.create(id)
                                .withStatus(AchAccountStatus.DELETED);
                    }
                })
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "deleteAchAccount onNext: " +
                                "achAccount[" + achAccount + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> linkAchAccount(
            Map<String, String> headers,
            String achAccountId,
            final ConfirmAchAccountParameters confirmAchAccountParameters) {
        return Single.just(new ChangebankResponse())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "linkAchAccount onSubscribe: confirmAchAccountParameters["
                                + confirmAchAccountParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "linkAchAccount onNext: response[" + response + "]");
                    }
                });
    }

    @Override
    public Single<AchAccount> createMicroDepositAccount(Map<String, String> headers, CreateMicroDepositAchAccountParameters createMicroDepositAchAccountParameters,String accountNumber) {
        return Single.just("as")
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "createMicroDepositAccount onSubscribe: ]");
                    }
                })
                .map(new Function<String, AchAccount>() {
                    @Override
                    public AchAccount apply(String id) throws Exception {
                        return AchAccount.create(id)
                                .withStatus(AchAccountStatus.DELETED);
                    }
                })
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "deleteAchAccount onNext: " +
                                "achAccount[" + achAccount + "]");
                    }
                });
    }

    @Override
    public Single<AchAccount> verifyMicroDepositAccount(Map<String, String> headers, String achAccountId, VerifyMicroDepositAchAccountParameters verifyMicroDepositAchAccountParameters) {
        return Single.just("as")
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "createMicroDepositAccount onSubscribe: ]");
                    }
                })
                .map(new Function<String, AchAccount>() {
                    @Override
                    public AchAccount apply(String id) throws Exception {
                        return AchAccount.create(id)
                                .withStatus(AchAccountStatus.DELETED);
                    }
                })
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "deleteAchAccount onNext: " +
                                "achAccount[" + achAccount + "]");
                    }
                });
    }

    @Override
    public Single<AchAccount> reLinkAchAccount(
            Map<String, String> headers,
            String achAccountId,
            final Parameters parameters) {
        return Single.just(achAccountId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "reLinkAchAccount onSubscribe: " +
                                "parameters[" + parameters + "]");
                    }
                })
                .map(new Function<String, AchAccount>() {
                    @Override
                    public AchAccount apply(String id) throws Exception {
                        return AchAccount.create(id)
                                .withStatus(AchAccountStatus.LINKED)
                                .withBalance(new Money(100000))
                                .withBankId("1234")
                                .withAccountType(AccountType.CHEQUE)
                                .withAccountNumber("123456");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "reLinkAchAccount onNext: " +
                                "achAccount[" + achAccount + "]");
                    }
                });
    }

    @Override
    public Single<AchAccount> unLinkAchAccount(
            Map<String, String> headers,
            final String achAccountId) {
        return Single.just(achAccountId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "unLinkAchAccount onSubscribe: " +
                                "achAccountId[" + achAccountId + "]");
                    }
                })
                .map(new Function<String, AchAccount>() {
                    @Override
                    public AchAccount apply(String id) throws Exception {
                        return AchAccount.create(id)
                                .withStatus(AchAccountStatus.UNLINKED)
                                .withBalance(new Money(100000))
                                .withBankId("1234")
                                .withAccountType(AccountType.CHEQUE)
                                .withAccountNumber("123456");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<AchAccount>() {
                    @Override
                    public void accept(AchAccount achAccount) throws Exception {
                        mLogger.debug(TAG, "unLinkAchAccount onNext: achAccount["
                                + achAccount + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> refreshAchAccounts(
            Map<String, String> headers,
            final String memberId) {
        return Single.just(new ChangebankResponse())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "refreshAchAccounts onSubscribe: " +
                                "memberId[" + memberId + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "refreshAchAccounts onNext: response[" + response + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> activateCard(
            Map<String, String> headers,
            String cardId,
            final CardActivationParameters cardActivationParameters) {
        return Single.just(new ChangebankResponse())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "Entry activateCard: " +
                                "cardActivationParameters[" + cardActivationParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "Result activateCard: response[" + response + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateCardPan(Map<String, String> headers, String cardId, final CardActivationParameters cardActivationParameters) {
        return Single.just(new ChangebankResponse())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "Entry activateCard: " +
                                "cardActivationParameters[" + cardActivationParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "Result activateCard: response[" + response + "]");
                    }
                });
    }

    @Override
    public Single<Card> updateCardStatus(
            Map<String, String> headers,
            String cardId,
            final UpdateCardStatusParameters updateCardStatusParameters) {
        return Single.just(Card.create(
                updateCardStatusParameters.getCardId(),
                "1876",
                "04-89",
                updateCardStatusParameters.getStatus())
                .withAccountNumber("1234")
                .withAdminNumber("9876"))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "updateCardStatus onSubscribe: " +
                                "updateCardStatusParameters[" + updateCardStatusParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Card>() {
                    @Override
                    public void accept(Card card) throws Exception {
                        mLogger.debug(TAG, "Result updateCardStatus: card[" + card + "]");
                    }
                });
    }

    @Override
    public Single<Response<ResponseBody>> getDirectDepositForm(Map<String, String> headers, String accountId) {
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
    public Single<Card> getCard(Map<String, String> headers, String cardId) {
        return Single.just(Card.create(cardId, "0987", "03-03", CardStatus.ACTIVE));
    }

    @Override
    public Single<Card> getCardByCardId(Map<String, String> headers, String cardId) {
        return Single.just(Card.create(cardId, "0987", "03-03", CardStatus.ACTIVE));
    }

    @Override
    public Single<ChangebankResponse> requestPhysicalCard(Map<String, String> headers, String cardID) {
        return Single.just(new ChangebankResponse())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "Entry activateCard: " +
                                "cardActivationParameters[" + cardID + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "Result activateCard: response[" + response + "]");
                    }
                });
    }

    @Override
    public Single<CreateNewSubCardResponse> createNewSubCard(Map<String, String> headers, String cardId, CreateSubCardParameter createSubCardParameter) {
        return null;
    }

}
