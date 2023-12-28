package com.example.cf_sdk.logic.sdk_module.singleton;



import com.example.cf_sdk.R;
import com.example.cf_sdk.logic.changebankapi.network.api.AccountApi;
import com.example.cf_sdk.logic.changebankapi.network.api.AuthenticationApi;
import com.example.cf_sdk.logic.changebankapi.network.api.GoogleApi;
import com.example.cf_sdk.logic.changebankapi.network.api.IngoApi;
import com.example.cf_sdk.logic.changebankapi.network.api.MemberApi;
import com.example.cf_sdk.logic.changebankapi.network.api.TransactionApi;
import com.example.cf_sdk.logic.changebankapi.source.remote.AccountRemoteDatasource;
import com.example.cf_sdk.logic.changebankapi.source.remote.AuthenticationRemoteDatasource;
import com.example.cf_sdk.logic.changebankapi.source.remote.GoogleRemoteDatasource;
import com.example.cf_sdk.logic.changebankapi.source.remote.IngoRemoteDatasource;
import com.example.cf_sdk.logic.changebankapi.source.remote.MemberRemoteDatasource;
import com.example.cf_sdk.logic.changebankapi.source.remote.TransactionRemoteDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.IngoDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.example.cf_sdk.logic.sdk_module.ChangebankApp;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 *
 * Module for remote data sources.
 */
@Module
public class RemoteDatasourceModule {

    private final ChangebankApp mApplication;


    public RemoteDatasourceModule(ChangebankApp application) {
        mApplication = application;

    }

    @Provides
    @Singleton
    @Named("remote")
    AuthenticationDatasource provideRemoteAuthenticationDatasource(AuthenticationApi api) {
        return new AuthenticationRemoteDatasource(api);
    }

    @Provides
    @Singleton
    @Named("remote")
    MemberDatasource provideRemoteMemberDatasource(MemberApi api) {
        return new MemberRemoteDatasource(api, mApplication.getCacheDir());
    }

    @Provides
    @Singleton
    @Named("remote")
    AccountDatasource provideRemoteAccountDatasource(AccountApi api) {
        return new AccountRemoteDatasource(api, mApplication.getCacheDir());
    }

    @Provides
    @Singleton
    @Named("remote")
    TransactionDatasource provideRemoteTransactionDatasource(TransactionApi api) {
        return new TransactionRemoteDatasource(api, mApplication.getCacheDir());
    }

    @Provides
    @Singleton
    @Named("remote")
    GoogleDatasource provideRemoteGoogleDatasource(GoogleApi api) {
        return new GoogleRemoteDatasource(api, mApplication.getString(R.string.google_places_address_autocomplete));
    }

    @Provides
    @Singleton
    @Named("remote")
    IngoDatasource provideRemoteIngoDatasource(IngoApi ingoApi) {
        return new IngoRemoteDatasource(ingoApi);
    }
}
