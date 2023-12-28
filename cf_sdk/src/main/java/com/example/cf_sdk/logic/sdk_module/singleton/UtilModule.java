package com.example.cf_sdk.logic.sdk_module.singleton;


import com.example.cf_sdk.logic.changebankapi.util.AndroidEncoder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Provider of {@link AndroidEncoder}.
 */

@Module
public class UtilModule {
    @Provides
    @Singleton
    AndroidEncoder provideAndroidEncoder() {
        return new AndroidEncoder();
    }
}
