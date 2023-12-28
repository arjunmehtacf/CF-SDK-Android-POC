package com.example.cf_sdk.logic.changebankapi.source.local;

import android.content.SharedPreferences;

import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *
 * Local stored data related to Authentication.
 */

public class AppPreferenceLocalDataSource implements AppPreferenceDatasource {
    static final String HAS_LOGGED_IN = "has_logged_in";
    static final String WELCOME_PAGER_DISPLAYED = "welcome_pager_displayed";
    public static final String APP_PREFERENCES_FILE = "AppPreferences";


    private final SharedPreferences mAppPreferences;

    @Inject
    public AppPreferenceLocalDataSource(SharedPreferences sharedPreferences) {
        mAppPreferences = sharedPreferences;
    }


    @Override
    public Single<Boolean> isWelcomeScreenCompletedOrSkipped() {
        return Single.just(getBoolean(WELCOME_PAGER_DISPLAYED));
    }

    @Override
    public Completable setWelcomeScreenCompletedOrSkipped() {
        putBooleanValue(WELCOME_PAGER_DISPLAYED, true);
        return Completable.complete();
    }

    @Override
    public Completable setHasLoggedInAlready() {
        putBooleanValue(HAS_LOGGED_IN, true);
        return Completable.complete();
    }

    @Override
    public Completable clearAllAppPreference() {
        mAppPreferences.edit().clear().apply();
        return Completable.complete();
    }


    private void putBooleanValue(String key, boolean value) {
        mAppPreferences.edit().putBoolean(key, value).apply();
    }

    private boolean getBoolean(String welcomePagerDisplayed) {
        return mAppPreferences.getBoolean(welcomePagerDisplayed, false);
    }
}
