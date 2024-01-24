package com.example.cf_sdk.changebankapi.source;


import com.example.cf_sdk.changebankapi.model.FingerprintResponse
import com.example.cf_sdk.changebankapi.model.member.SecurityProfile
import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.parameter.authentication.GetSecurityProfileParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginMethodType
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.ResendTwoFactorParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.SecurityProfileParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.TwoFactorParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.UsernameParameters
import com.example.cf_sdk.changebankapi.source.remote.AuthenticationDatasource
import com.example.cf_sdk.defination.request.AccessTokenParameter
import com.example.cf_sdk.defination.response.ChangebankResponse
import com.example.cf_sdk.defination.response.Session
import com.google.common.base.Optional
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

/**
 *
 * Authentication repository is in charge of choosing the correct [AuthenticationDatasource].
 */
class AuthenticationRepository(
    private val mRemoteDatasource: AuthenticationDatasource,
    private val mLocalDatasource: AuthenticationDatasource,
    private val mCacheDatasource: AuthenticationDatasource,
) : AuthenticationDatasource {
    private val saveSession = Consumer<Session?> { session -> saveSession(session) }


    override val session: Single<Session?>?
        get() = mCacheDatasource.session
    override val loginMethodType: Single<LoginMethodType?>?
        get() = mLocalDatasource.loginMethodType
    override val pinSalt: Single<Optional<ByteArray?>?>?
        get() = mLocalDatasource.pinSalt

    override fun checkUsernameExists(usernameParameters: UsernameParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.checkUsernameExists(usernameParameters)
    }

    override val savedUsername: Single<Optional<String?>?>?
        get() = mLocalDatasource.savedUsername
    override val cachedUsername: Single<Optional<String?>?>?
        get() = mCacheDatasource.cachedUsername

    override fun logout(parameters: Parameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.logout(parameters)
    }


    override fun saveSession(session: Session?) {
        mCacheDatasource.saveSession(session)
    }

    override fun savePinSalt(pinSalt: ByteArray?, username: String?) {
        mLocalDatasource.savePinSalt(pinSalt, username)
    }

    override fun saveUsername(username: String?) {
        mLocalDatasource.saveUsername(username)
    }

    override fun clearAllAuthenticationDatasourceCache() {
        mCacheDatasource.clearAllAuthenticationDatasourceCache()
    }

    override fun clearPinAndFingerprint() {
        mLocalDatasource.clearPinAndFingerprint()
    }

    override fun updateLocalStorage(loginParameters: LoginParameters?) {
        mLocalDatasource.updateLocalStorage(loginParameters)
    }

    override fun readFingerprint(username: String?): Observable<FingerprintResponse?>? {
        return mLocalDatasource.readFingerprint(username)
    }

    override fun readFingerprintInstant(username: String?): Single<FingerprintResponse?>? {
        return mLocalDatasource.readFingerprintInstant(username)
    }

    override fun writeFingerprint(username: String?, passwordHash: String?): Completable? {
        return mLocalDatasource.writeFingerprint(username, passwordHash)
    }

    override fun canStoreFingerprintSecurely(): Single<Boolean?>? {
        return mLocalDatasource.canStoreFingerprintSecurely()
    }

    override fun clearAllFingerprint(): Completable? {
        return mLocalDatasource.clearAllFingerprint()
    }

    override fun isUserFingerprintSaved(username: String?): Single<Boolean?>? {
        return mLocalDatasource.isUserFingerprintSaved(username)
    }

    override fun clearSpecificFingerprint(username: String?): Completable? {
        return mLocalDatasource.clearSpecificFingerprint(username)
    }

    override fun resendLoginMFA(resendTwoFactorParameters: ResendTwoFactorParameters?): Single<Session?>? {
        return mRemoteDatasource.resendLoginMFA(resendTwoFactorParameters)
            ?.doOnSuccess(saveSession)
    }

    override fun verifyLoginMFA(twoFactorParameters: TwoFactorParameters?): Single<Session?>? {
        return mRemoteDatasource.verifyLoginMFA(twoFactorParameters)
            ?.doOnSuccess(saveSession)
    }

    override fun updateSecurityProfile(securityProfileParameters: SecurityProfileParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.updateSecurityProfile(securityProfileParameters)
    }

    override fun getSecurityProfile(getSecurityProfileParameters: GetSecurityProfileParameters?): Single<SecurityProfile?>? {
        return mRemoteDatasource.getSecurityProfile(getSecurityProfileParameters)
    }

    override fun clearPasscode(): Completable? {
        return mLocalDatasource.clearPasscode()
    }

    override fun getAccessToken(accessTokenParameter: AccessTokenParameter?): Single<Session?>? {
        return mRemoteDatasource.getAccessToken(accessTokenParameter)
    }

    companion object {
        private val TAG = AuthenticationRepository::class.java.simpleName
    }
}