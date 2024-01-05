package com.example.cf_sdk.changebankapi.source.datasource

import io.reactivex.Completable
import io.reactivex.Single

/**
 *
 * Datasource for AppPreferences.
 */
interface AppPreferenceDatasource {
    val isWelcomeScreenCompletedOrSkipped: Single<Boolean?>?
    fun setWelcomeScreenCompletedOrSkipped(): Completable?
    fun setHasLoggedInAlready(): Completable?
    fun clearAllAppPreference(): Completable?
}