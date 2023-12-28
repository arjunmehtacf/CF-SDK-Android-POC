package com.example.cf_sdk.logic.sdk_module;

import android.app.Application;

import androidx.annotation.VisibleForTesting;

import com.example.cf_sdk.defifination.CFApiCall;
import com.example.cf_sdk.logic.sdk_module.singleton.ApiModule;
import com.example.cf_sdk.logic.sdk_module.singleton.AppModule;
import com.example.cf_sdk.logic.sdk_module.singleton.CacheDatasourceModule;
import com.example.cf_sdk.logic.sdk_module.singleton.ChangebankComponent;
import com.example.cf_sdk.logic.sdk_module.singleton.DaggerChangebankComponent;
import com.example.cf_sdk.logic.sdk_module.singleton.ExecutorModule;
import com.example.cf_sdk.logic.sdk_module.singleton.LocalDatasourceModule;
import com.example.cf_sdk.logic.sdk_module.singleton.LoggerModule;
import com.example.cf_sdk.logic.sdk_module.singleton.NetworkModule;
import com.example.cf_sdk.logic.sdk_module.singleton.RemoteDatasourceModule;
import com.example.cf_sdk.logic.sdk_module.singleton.RepositoryModule;

/**
 * Application init.
 */

public class ChangebankApp extends Application {


    private ChangebankComponent mChangebankComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mChangebankComponent == null) {
            mChangebankComponent = createDaggerComponent();
        }
        mChangebankComponent.inject(this);
        CFApiCall.getInstance(this);
    }

    @VisibleForTesting
    public void setComponent(ChangebankComponent changebankComponent) {
        mChangebankComponent = changebankComponent;
    }

    public ChangebankComponent getChangebankComponent() {
        return mChangebankComponent;
    }

    public ChangebankComponent createDaggerComponent() {
        return DaggerChangebankComponent.builder()
                .loggerModule(new LoggerModule(this))
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .executorModule(new ExecutorModule())
                .networkModule(new NetworkModule(this))
                .repositoryModule(new RepositoryModule())
                .remoteDatasourceModule(new RemoteDatasourceModule(this))
                .localDatasourceModule(new LocalDatasourceModule())
                .cacheDatasourceModule(new CacheDatasourceModule())
                .build();
    }
}
