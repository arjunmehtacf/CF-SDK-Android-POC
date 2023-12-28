package com.example.cf_sdk.logic.sdk_module.singleton;

import android.app.Application;

import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.sdk_module.CFSDKModule;
import com.example.cf_sdk.logic.sdk_module.ChangebankApp;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * Component for Singleton instances.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ExecutorModule.class,
        CacheDatasourceModule.class,
        LocalDatasourceModule.class,
        RemoteDatasourceModule.class,
        RepositoryModule.class,
        NetworkModule.class,
        ApiModule.class,
        ErrorHandlerModule.class,
        UtilModule.class,
        LoggerModule.class,
        CFSDKModule.class
})
public interface ChangebankComponent {
    @Named("ui")
    ExecutionThread getUiThread();

    @Named("io")
    ExecutionThread getIoThread();

    @Named("repository")
    AuthenticationDatasource getAuthenticationRepository();

    @Named("repository")
    AppPreferenceDatasource getAppPreferenceDatasource();

    @Named("repository")
    MemberDatasource getMemberRepository();

    @Named("repository")
    GoogleDatasource getGoogleDatasource();

    @Named("repository")
    AccountDatasource getAccountDatasource();

    @Named("repository")
    TransactionDatasource getTransactionRepository();

    Application getApplication();

    @Named("ErrorHandler")
    ChangebankError getErrorHandler();

    Logger getLogger();

    void inject(ChangebankApp changebankApp);
}
