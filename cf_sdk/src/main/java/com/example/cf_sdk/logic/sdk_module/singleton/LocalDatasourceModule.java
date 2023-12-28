package com.example.cf_sdk.logic.sdk_module.singleton;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.biometric.BiometricManager;
import com.example.cf_sdk.logic.changebankapi.source.local.AccountLocalDataSource;
import com.example.cf_sdk.logic.changebankapi.source.local.AppPreferenceLocalDataSource;
import com.example.cf_sdk.logic.changebankapi.source.local.AuthenticationLocalDataSource;
import com.example.cf_sdk.logic.changebankapi.util.AndroidEncoder;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Module that provides the local data source for the different repositories.
 */
@Module
public class LocalDatasourceModule {
    private static final String FINGERPRINT_PROTECTED_PREFERENCES = "fingerprint_protected_preferences";


    @Provides
    @Singleton
    com.changefinancials.changebank.fingerprint.BiometricAuthHelper providesWhorlwindFingerprintHelper(Application app) {
        SharedPreferences sharedPreferencesStorage = app.getSharedPreferences(
                AuthenticationLocalDataSource.AUTHENTICATION_PREFERENCES_FILE,
                Application.MODE_PRIVATE);
        return new com.changefinancials.changebank.fingerprint.BiometricAuthHelper(BiometricManager.from(app),
                sharedPreferencesStorage);
    }

    @Provides
    @Singleton
    @Named("local")
    AuthenticationDatasource provideLocalAuthenticationDatasource(Application app,
                                                                  AndroidEncoder encoder,
                                                                  com.changefinancials.changebank.fingerprint.BiometricAuthHelper biometricAuthHelper) {
        return new AuthenticationLocalDataSource(
                app.getSharedPreferences(
                        AuthenticationLocalDataSource.BIOMETRIC_PREFERENCES_FILE,
                        Application.MODE_PRIVATE),
                encoder,
                biometricAuthHelper);
    }

    @Provides
    @Singleton
    @Named("local")
    AccountDatasource provideLocalAccountDatasource() {
        return new AccountLocalDataSource();
    }

    @Provides
    @Singleton
    @Named("local")
    AppPreferenceDatasource provideLocalAppPreferenceDatasource(Application app) {
        return new AppPreferenceLocalDataSource(
                app.getSharedPreferences(
                        AppPreferenceLocalDataSource.APP_PREFERENCES_FILE,
                        Application.MODE_PRIVATE));
    }

}
