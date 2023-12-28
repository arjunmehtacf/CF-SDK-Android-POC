package com.example.cf_sdk.logic.sdk_module.singleton;

import android.app.Application;


import com.example.cf_sdk.logic.changebanksdk.util.Validator;
import com.example.cf_sdk.logic.sdk_module.ChangebankApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Provides application instance.
 */
@Module
public class AppModule {
    private final ChangebankApp mApplication;

    public AppModule(ChangebankApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    Validator provideValidator() {
        return (Validator) new AndroidValidator();
    }



}
