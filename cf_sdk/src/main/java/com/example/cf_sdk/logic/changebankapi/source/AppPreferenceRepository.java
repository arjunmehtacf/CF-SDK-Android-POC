package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *
 * Authentication repository is in charge of choosing the correct {@link AuthenticationDatasource}.
 */

public class AppPreferenceRepository implements AppPreferenceDatasource {
    private static final String TAG = AppPreferenceRepository.class.getSimpleName();
    private final AppPreferenceDatasource mAppPreferenceDatasource;

    @Inject
    public AppPreferenceRepository(@Named("local") AppPreferenceDatasource appPreferenceDatasource) {
        mAppPreferenceDatasource = appPreferenceDatasource;
    }

    @Override
    public Single<Boolean> isWelcomeScreenCompletedOrSkipped() {
        return mAppPreferenceDatasource.isWelcomeScreenCompletedOrSkipped();
    }

    @Override
    public Completable setWelcomeScreenCompletedOrSkipped() {
        return mAppPreferenceDatasource.setWelcomeScreenCompletedOrSkipped();
    }

    @Override
    public Completable setHasLoggedInAlready() {
        return mAppPreferenceDatasource.setHasLoggedInAlready();
    }

    @Override
    public Completable clearAllAppPreference() {
        return mAppPreferenceDatasource.clearAllAppPreference();
    }
}
