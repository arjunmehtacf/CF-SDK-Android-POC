package com.example.cf_sdk.logic.sdk_module.singleton;


import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.task.member.LoggerTask;
import com.example.cf_sdk.logic.sdk_module.ChangebankApp;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Module for Logger creation.
 */

@Module
public class LoggerModule {
    private static ChangebankLogger mChangebankLogger;
    private static Logger mLogManager;
    private final ChangebankApp mChangebankApp;

    public LoggerModule(ChangebankApp changebankApp) {
        mLogManager = new LogManager();
        mChangebankApp = changebankApp;
    }

    @Provides
    @Singleton
    Logger provideLogManager() {
        return mLogManager;
    }


    @Provides
    @Singleton
    ChangebankLogger provideChangebankLogger(LoggerTask loggerTask) {
        mChangebankLogger = new ChangebankLogger(loggerTask, mChangebankApp);
        ((LogManager) mLogManager).setChangebankLogger(mChangebankLogger);
        return mChangebankLogger;
    }

    public static void setDebugChangebankLogger(boolean shouldEnable) {
        if (mLogManager != null) {
            ((LogManager) mLogManager).enableDebugChangebankLog(shouldEnable);
        }
    }
}
