package com.example.cf_sdk.logic.sdk_module.singleton;

import com.example.cf_sdk.logic.changebankapi.source.cache.AccountCacheDatasource;
import com.example.cf_sdk.logic.changebankapi.source.cache.AuthenticationCacheDatasource;
import com.example.cf_sdk.logic.changebankapi.source.cache.MemberCacheDatasource;
import com.example.cf_sdk.logic.changebankapi.source.cache.ZendeskCacheDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Module for cache data sources.
 */
@Module
public class CacheDatasourceModule {
    @Provides
    @Singleton
    @Named("cache")
    AuthenticationDatasource provideCacheAuthenticationDatasource() {
        return new AuthenticationCacheDatasource();
    }

    @Provides
    @Singleton
    @Named("cache")
    MemberDatasource provideCacheMemberDatasource() {
        return new MemberCacheDatasource();
    }

    @Provides
    @Singleton
    @Named("cache")
    AccountDatasource provideCacheAccountDatasource() {
        return new AccountCacheDatasource();
    }


    @Provides
    @Singleton
    @Named("cache")
    ZendeskDatasource provideCacheZendeskDatasource() {
        return new ZendeskCacheDatasource();
    }
}
