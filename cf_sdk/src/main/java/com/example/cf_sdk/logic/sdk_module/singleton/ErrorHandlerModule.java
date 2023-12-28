package com.example.cf_sdk.logic.sdk_module.singleton;


import com.example.cf_sdk.logic.changebankapi.ErrorHandler;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 *
 * Module for error handler.
 */

@Module
public class ErrorHandlerModule {
    @Provides
    @Singleton
    @Named("ErrorHandler")
    ChangebankError provideErrorHandler(Retrofit retrofit) {
        return new ErrorHandler(retrofit);
    }
}
