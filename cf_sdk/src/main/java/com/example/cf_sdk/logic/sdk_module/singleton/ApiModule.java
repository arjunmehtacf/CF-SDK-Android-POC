package com.example.cf_sdk.logic.sdk_module.singleton;

import com.example.cf_sdk.logic.changebankapi.network.api.AccountApi;
import com.example.cf_sdk.logic.changebankapi.network.api.AuthenticationApi;
import com.example.cf_sdk.logic.changebankapi.network.api.GoogleApi;
import com.example.cf_sdk.logic.changebankapi.network.api.IngoApi;
import com.example.cf_sdk.logic.changebankapi.network.api.MemberApi;
import com.example.cf_sdk.logic.changebankapi.network.api.TransactionApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.sdk_module.creator.AccountApiCreator;
import com.example.cf_sdk.logic.sdk_module.creator.AuthenticationApiCreator;
import com.example.cf_sdk.logic.sdk_module.creator.GoogleApiCreator;
import com.example.cf_sdk.logic.sdk_module.creator.IngoApiCreator;
import com.example.cf_sdk.logic.sdk_module.creator.MemberApiCreator;
import com.example.cf_sdk.logic.sdk_module.creator.TransactionApiCreator;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


/**
 *
 * Creates Apis from a Retrofit instance (Provided in {@link NetworkModule}).
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    AuthenticationApi provideAuthenticationApi(Logger logManager, Retrofit retrofit) {
        return new AuthenticationApiCreator().create(logManager, retrofit);
    }

    @Provides
    @Singleton
    MemberApi provideMemberApi(Logger logManager, Retrofit retrofit) {
        return new MemberApiCreator().create(logManager, retrofit);
    }

    @Provides
    @Singleton
    AccountApi provideAccountApi(Logger logManager, Retrofit retrofit) {
        return new AccountApiCreator().create(logManager, retrofit);
    }

    @Provides
    @Singleton
    TransactionApi provideTransactionApi(Logger logManager, Retrofit retrofit) {
        return new TransactionApiCreator().create(logManager, retrofit);
    }

    @Provides
    @Singleton
    GoogleApi provideGoogleApi(Logger logManager, Retrofit retrofit, @Named("debugRetrofit") Retrofit debugRetrofit) {
        ;
        return new GoogleApiCreator().create(logManager, debugRetrofit);
    }

    @Provides
    @Singleton
    IngoApi provideIngoApi(Logger logManager, Retrofit retrofit) {
        return new IngoApiCreator().create(logManager, retrofit);
    }
}