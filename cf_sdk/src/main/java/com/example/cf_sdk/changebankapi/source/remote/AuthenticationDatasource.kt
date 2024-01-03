package com.example.cf_sdk.changebankapi.source.remote


import com.example.cf_sdk.changebankapi.model.FingerprintResponse
import com.example.cf_sdk.changebankapi.model.Session
import com.example.cf_sdk.changebankapi.model.member.SecurityProfile
import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.parameter.authentication.GetSecurityProfileParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginMethodType
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.ResendTwoFactorParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.SecurityProfileParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.TwoFactorParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.UsernameParameters
import com.example.cf_sdk.changebankapi.response.ChangebankResponse

import com.google.common.base.Optional
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by victorojeda on 10/19/16.
 *
 *
 * DataSource to provide authentication endpoints.
 */
interface AuthenticationDatasource {
    val session: Single<Session?>?
    val loginMethodType: Single<LoginMethodType?>?
    val pinSalt: Single<Optional<ByteArray?>?>?
    fun checkUsernameExists(usernameParameters: UsernameParameters?): Single<ChangebankResponse?>?
    val savedUsername: Single<Optional<String?>?>?
    val cachedUsername: Single<Optional<String?>?>?
    fun logout(parameters: Parameters?): Single<ChangebankResponse?>?
    fun saveSession(session: Session?)
    fun savePinSalt(pinSalt: ByteArray?, username: String?)
    fun saveUsername(username: String?)
    fun clearAllAuthenticationDatasourceCache()
    fun clearPinAndFingerprint()
    fun updateLocalStorage(loginParameters: LoginParameters?)
    fun readFingerprint(username: String?): Observable<FingerprintResponse?>?
    fun readFingerprintInstant(username: String?): Single<FingerprintResponse?>?
    fun writeFingerprint(username: String?, passwordHash: String?): Completable?
    fun canStoreFingerprintSecurely(): Single<Boolean?>?
    fun clearAllFingerprint(): Completable?
    fun isUserFingerprintSaved(username: String?): Single<Boolean?>?
    fun clearSpecificFingerprint(username: String?): Completable?
    fun resendLoginMFA(resendTwoFactorParameters: ResendTwoFactorParameters?): Single<Session?>?
    fun verifyLoginMFA(twoFactorParameters: TwoFactorParameters?): Single<Session?>?
    fun updateSecurityProfile(securityProfileParameters: SecurityProfileParameters?): Single<ChangebankResponse?>?
    fun getSecurityProfile(getSecurityProfileParameters: GetSecurityProfileParameters?): Single<SecurityProfile?>?
    fun clearPasscode(): Completable?
}