package com.example.cf_sdk.logic.sdk_module.singleton;


import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Executor Provider
 */

@Module
public class ExecutorModule {
    @Provides
    @Singleton
    @Named("ui")
    public ExecutionThread provideUiThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    @Named("io")
    public ExecutionThread provideIoThread() {
        return new IOThread();
    }
}
