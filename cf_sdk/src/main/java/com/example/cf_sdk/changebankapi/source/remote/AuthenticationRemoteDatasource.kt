package com.example.cf_sdk.changebankapi.source.remote;


import com.example.cf_sdk.changebankapi.model.FingerprintResponse
import com.example.cf_sdk.defination.response.Session
import com.example.cf_sdk.changebankapi.model.member.SecurityProfile
import com.example.cf_sdk.changebankapi.network.api.AuthenticationApi
import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.example.cf_sdk.changebankapi.parameter.authentication.GetSecurityProfileParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginMethodType
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.ResendTwoFactorParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.SecurityProfileParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.TwoFactorParameters
import com.example.cf_sdk.changebankapi.parameter.authentication.UsernameParameters
import com.example.cf_sdk.defination.response.ChangebankResponse
import com.example.cf_sdk.changebankapi.source.AuthenticationRepository
import com.example.cf_sdk.defination.request.AccessTokenParameter


import com.google.common.base.Optional
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 *
 * Remote datasource to call authentication endpoints.
 */
class AuthenticationRemoteDatasource(private val mAuthenticationApi: AuthenticationApi) :
    AuthenticationDatasource {


    override val session: Single<Session?>?
        get() {
            throw UnsupportedOperationException("AuthenticationRemoteDatasource not support getSession")
        }
    override val loginMethodType: Single<LoginMethodType?>?
        get() {
            throw UnsupportedOperationException("AuthenticationRemoteDatasource not support getLoginMethodType")
        }
    override val pinSalt: Single<Optional<ByteArray?>?>?
        get() {
            throw UnsupportedOperationException("AuthenticationCacheDatasource not support getPinSalt")
        }

    override fun checkUsernameExists(usernameParameters: UsernameParameters?): Single<ChangebankResponse?>? {
        return mAuthenticationApi.checkUsernameExists(
            usernameParameters!!.headers,
            usernameParameters
        )
    }

    override val savedUsername: Single<Optional<String?>?>?
        get() {
            throw UnsupportedOperationException("AuthenticationCacheDatasource not support getSavedUsername")
        }
    override val cachedUsername: Single<Optional<String?>?>?
        get() {
            throw UnsupportedOperationException("AuthenticationCacheDatasource not support getCachedUsername")
        }

    override fun logout(parameters: Parameters?): Single<ChangebankResponse?>? {
        return mAuthenticationApi.logout(parameters!!.headers)
    }


    override fun saveSession(session: Session?) {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support saveSession")
    }

    override fun savePinSalt(pinSalt: ByteArray?, username: String?) {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support savePinSalt")
    }

    override fun saveUsername(username: String?) {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support saveUsername")
    }

    override fun clearAllAuthenticationDatasourceCache() {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support clearAllAuthenticationDatasourceCache")
    }

    override fun clearPinAndFingerprint() {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support clearPinAndFingerprint")
    }

    override fun updateLocalStorage(loginParameters: LoginParameters?) {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support updateLocalStorage")
    }

    override fun readFingerprint(username: String?): Observable<FingerprintResponse?>? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support readFingerprint")
    }

    override fun readFingerprintInstant(username: String?): Single<FingerprintResponse?>? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support readFingerprintInstant")
    }

    override fun writeFingerprint(username: String?, passwordHash: String?): Completable? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support writeFingerprint")
    }

    override fun canStoreFingerprintSecurely(): Single<Boolean?>? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support canStoreFingerprintSecurely")
    }

    override fun clearAllFingerprint(): Completable? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support clearAllFingerprint")
    }

    override fun isUserFingerprintSaved(username: String?): Single<Boolean?>? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support isUserFingerprintSaved")
    }

    override fun clearSpecificFingerprint(username: String?): Completable? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support clearSpecificFingerprint")
    }

    override fun resendLoginMFA(resendTwoFactorParameters: ResendTwoFactorParameters?): Single<Session?>? {
        return mAuthenticationApi.resendCode(
            resendTwoFactorParameters!!.headers,
            resendTwoFactorParameters.memberId
        )
    }

    override fun verifyLoginMFA(twoFactorParameters: TwoFactorParameters?): Single<Session?>? {
        return mAuthenticationApi.twoFactor(twoFactorParameters!!.headers, twoFactorParameters)
    }

    override fun updateSecurityProfile(securityProfileParameters: SecurityProfileParameters?): Single<ChangebankResponse?>? {
        return mAuthenticationApi.updateSecurityProfile(
            securityProfileParameters!!.headers,
            securityProfileParameters.memberId,
            securityProfileParameters
        )
    }

    override fun getAccessToken(accessTokenParameter: AccessTokenParameter?): Single<Session?>? {
        // todo: Replace url it with new one
        return mAuthenticationApi.getAccessToken("http://e49.vtxn-qadev.cf-cloud.net:8080/v1/auth/token",accessTokenParameter?.headers,accessTokenParameter)
    }


    override fun getSecurityProfile(getSecurityProfileParameters: GetSecurityProfileParameters?): Single<SecurityProfile?>? {
        return mAuthenticationApi.getSecurityProfile(
            getSecurityProfileParameters!!.headers,
            getSecurityProfileParameters.memberId
        )
    }

    override fun clearPasscode(): Completable? {
        throw UnsupportedOperationException("AuthenticationRemoteDatasource not support clearPasscode")
    }

    companion object {
        private val TAG = AuthenticationRepository::class.java.simpleName
    }
}