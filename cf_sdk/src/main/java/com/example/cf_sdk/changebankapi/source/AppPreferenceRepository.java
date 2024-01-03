package com.example.cf_sdk.changebankapi.source;



import com.example.sdk_no_dagger.changebankapi.source.datasource.AppPreferenceDatasource;

import io.reactivex.Completable;
import io.reactivex.Single;

/**

 */

public class AppPreferenceRepository implements AppPreferenceDatasource {
    private static final String TAG = AppPreferenceRepository.class.getSimpleName();
    private final AppPreferenceDatasource mAppPreferenceDatasource;


    public AppPreferenceRepository( AppPreferenceDatasource appPreferenceDatasource) {
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
