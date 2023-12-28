package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.exception.AchLinkMaxRetryException;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccountStatus;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 *
 * Task for Ach Login for External Bank.
 */

public class LoginAchAccountTask extends SingleUseCase<CreateAchAccountParameters, AchAccount> {

    private final ExecutionThread mBackgroundExecutionThread;
    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public LoginAchAccountTask(@Named("repository") AccountDatasource accountDatasource,
                               @Named("repository") AuthenticationDatasource authenticationDatasource,
                               @Named("io") ExecutionThread backgroundExecutionThread,
                               @Named("ui") ExecutionThread uiExecutionThread,
                               @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mBackgroundExecutionThread = backgroundExecutionThread;
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<AchAccount> buildUseCaseObservable(final CreateAchAccountParameters createAchAccountParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends AchAccount>>() {
                    @Override
                    public SingleSource<? extends AchAccount> apply(final Session session) throws Exception {
                        createAchAccountParameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource
                                .createAchAccount(createAchAccountParameters
                                        .withMemberId(session.getMemberId()))
                                .flatMap(new Function<AchAccount, SingleSource<? extends AchAccount>>() {
                                    @Override
                                    public SingleSource<? extends AchAccount> apply(AchAccount achAccount) throws Exception {
                                        final StringParameters stringParameters = StringParameters.create(achAccount.getId());
                                        stringParameters.addHeader(Header.TOKEN, session.getToken());
                                        return Observable.interval(5, TimeUnit.SECONDS, mBackgroundExecutionThread.getScheduler())
                                                .flatMapSingle(new Function<Long, SingleSource<AchAccount>>() {
                                                    @Override
                                                    public SingleSource<AchAccount> apply(Long aLong) throws Exception {
                                                        if (aLong > 8L) {
                                                            return Single.error(new AchLinkMaxRetryException());
                                                        }
                                                        return mAccountDatasource.getAchAccount(stringParameters);
                                                    }
                                                })
                                                .takeUntil(new Predicate<AchAccount>() {
                                                    @Override
                                                    public boolean test(AchAccount achAccount) throws Exception {
                                                        return achAccount.getStatus() != AchAccountStatus.AUTHENTICATING && achAccount.getStatus() != AchAccountStatus.REFRESHING;
                                                    }
                                                })
                                                .lastOrError();
                                    }
                                });
                    }
                });
    }
}
