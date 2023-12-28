package com.example.cf_sdk.logic.changebanksdk.source;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *
 * Datasource for AppPreferences.
 */

public interface AppPreferenceDatasource {
    Single<Boolean> isWelcomeScreenCompletedOrSkipped();
    Completable setWelcomeScreenCompletedOrSkipped();
    Completable setHasLoggedInAlready();
    Completable clearAllAppPreference();
}
