package com.example.cf_sdk.logic.sdk_module.singleton;



import com.example.cf_sdk.logic.changebankapi.source.AccountRepository;
import com.example.cf_sdk.logic.changebankapi.source.AppPreferenceRepository;
import com.example.cf_sdk.logic.changebankapi.source.AuthenticationRepository;
import com.example.cf_sdk.logic.changebankapi.source.GoogleRepository;
import com.example.cf_sdk.logic.changebankapi.source.IngoRepository;
import com.example.cf_sdk.logic.changebankapi.source.MemberRepository;
import com.example.cf_sdk.logic.changebankapi.source.TransactionRepository;
import com.example.cf_sdk.logic.changebankapi.source.ZendeskRepository;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.IngoDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Module for repository data sources.
 */

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    @Named("repository")
    public AuthenticationDatasource provideAuthenticationRepository(
            @Named("remote") AuthenticationDatasource remote,
            @Named("local") AuthenticationDatasource local,
            @Named("cache") AuthenticationDatasource cache) {
        return new AuthenticationRepository(remote, local, cache);
    }

    @Provides
    @Singleton
    @Named("repository")
    public AccountDatasource provideAccountRepository(
            @Named("remote") AccountDatasource remote,
            @Named("local") AccountDatasource local,
            @Named("cache") AccountDatasource cache) {
        return new AccountRepository(remote, local, cache);
    }


    @Provides
    @Singleton
    @Named("repository")
    AppPreferenceDatasource provideAppPreferenceRepository(
            @Named("local") AppPreferenceDatasource local) {
        return new AppPreferenceRepository(local);
    }

    @Provides
    @Singleton
    @Named("repository")
    public MemberDatasource provideMemberRepository(@Named("remote") MemberDatasource remote,
                                                    @Named("cache") MemberDatasource cache) {
        return new MemberRepository(remote, cache);
    }

    @Provides
    @Singleton
    @Named("repository")
    public TransactionDatasource provideTransactionRepository(
            @Named("remote") TransactionDatasource remote) {
        return new TransactionRepository(remote);
    }

    @Provides
    @Singleton
    @Named("repository")
    public GoogleDatasource provideGoogleRepository(@Named("remote") GoogleDatasource remote) {
        return new GoogleRepository(remote);
    }


    @Provides
    @Singleton
    @Named("repository")
    public IngoDatasource provideIngoDatasource(@Named("remote") IngoDatasource remote) {
        return new IngoRepository(remote);
    }

    @Provides
    @Singleton
    @Named("repository")
    public ZendeskDatasource provideZendeskRepository(@Named("remote") ZendeskDatasource remote,
                                                      @Named("cache") ZendeskDatasource cache) {
        return new ZendeskRepository(remote, cache);
    }
}
