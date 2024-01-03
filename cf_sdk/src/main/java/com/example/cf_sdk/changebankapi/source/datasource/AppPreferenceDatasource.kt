package com.example.sdk_no_dagger.changebankapi.source.datasource

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by gunveernatt on 8/17/17.
 *
 * Datasource for AppPreferences.
 */
interface AppPreferenceDatasource {
    val isWelcomeScreenCompletedOrSkipped: Single<Boolean?>?
    fun setWelcomeScreenCompletedOrSkipped(): Completable?
    fun setHasLoggedInAlready(): Completable?
    fun clearAllAppPreference(): Completable?
}