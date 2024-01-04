package com.example.cf_sdk.changebankapi.source.cache;


import com.example.cf_sdk.changebankapi.exception.SessionExpiredException;
import com.example.cf_sdk.changebankapi.model.FingerprintResponse;
import com.example.cf_sdk.changebankapi.model.Session;
import com.example.cf_sdk.changebankapi.model.member.SecurityProfile;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.GetSecurityProfileParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginMethodType;
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.ResendTwoFactorParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.SecurityProfileParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.TwoFactorParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.UsernameParameters;
import com.example.cf_sdk.defination.response.ChangebankResponse;
import com.example.cf_sdk.changebankapi.source.remote.AuthenticationDatasource;

import com.google.common.base.Optional;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 *
 * Authentication cache datasource to provide in-memory data.
 */

public class AuthenticationCacheDatasource implements AuthenticationDatasource {

    private Session mSession;
    private String mUsername;

    @Override
    public Single<Session> getSession() {
        if (mSession != null) {
            return Single.just(mSession);
        } else {
            return Single.error(new SessionExpiredException());
        }
    }

    @Override
    public void saveSession(Session session) {
        mSession = session;
    }



    @Override
    public Single<LoginMethodType> getLoginMethodType() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getLoginMethodType");
    }

    @Override
    public Single<Optional<byte[]>> getPinSalt() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getPinSalt");
    }

    @Override
    public Single<ChangebankResponse> checkUsernameExists(UsernameParameters usernameParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support checkUsernameExists");
    }

    @Override
    public Single<Optional<String>> getSavedUsername() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getSavedUsername");
    }

    @Override
    public Single<Optional<String>> getCachedUsername() {
        return Single.just(Optional.fromNullable(mUsername));
    }

    @Override
    public Single<ChangebankResponse> logout(Parameters parameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support logout");
    }


    @Override
    public void savePinSalt(byte[] pinSalt, String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support savePinSalt");
    }

    @Override
    public void saveUsername(String username) {
        mUsername = username;
    }

    @Override
    public void clearAllAuthenticationDatasourceCache() {
        mUsername = null;
        mSession = null;
    }

    @Override
    public void clearPinAndFingerprint() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearPinAndFingerprint");
    }

    @Override
    public void updateLocalStorage(LoginParameters loginParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support updateLocalStorage");
    }


    @Override
    public Observable<FingerprintResponse> readFingerprint(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support readFingerprint");
    }

    @Override
    public Single<FingerprintResponse> readFingerprintInstant(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support readFingerprintInstant");    }

    @Override
    public Completable writeFingerprint(String username, String passwordHash) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support writeFingerprint");
    }

    @Override
    public Single<Boolean> canStoreFingerprintSecurely() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support canStoreFingerprintSecurely");
    }

    @Override
    public Completable clearAllFingerprint() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearAllFingerprint");
    }

    @Override
    public Single<Boolean> isUserFingerprintSaved(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support isUserFingerprintSaved");
    }

    @Override
    public Completable clearSpecificFingerprint(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearSpecificFingerprint");
    }

    @Override
    public Single<Session> resendLoginMFA(ResendTwoFactorParameters resendTwoFactorParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support resendLoginMFA");
    }

    @Override
    public Single<Session> verifyLoginMFA(TwoFactorParameters twoFactorParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support verifyLoginMFA");
    }

    @Override
    public Single<ChangebankResponse> updateSecurityProfile(SecurityProfileParameters securityProfileParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support updateSecurityProfile");
    }

    @Override
    public Single<SecurityProfile> getSecurityProfile(GetSecurityProfileParameters getSecurityProfileParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getSecurityProfile");
    }

    @Override
    public Completable clearPasscode() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearPasscode");
    }
}
