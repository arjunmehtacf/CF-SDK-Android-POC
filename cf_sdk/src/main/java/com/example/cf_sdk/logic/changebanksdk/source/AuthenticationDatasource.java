package com.example.cf_sdk.logic.changebanksdk.source;

import com.example.cf_sdk.logic.changebanksdk.model.FingerprintResponse;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.SecurityProfile;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.CheckPasscodeEnabledParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.GetSecurityProfileParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.ResendTwoFactorParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.SecurityProfileParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.TwoFactorParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdateCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdatePasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UsernameParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.google.common.base.Optional;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 *
 * DataSource to provide authentication endpoints.
 */

public interface AuthenticationDatasource {
    Single<Session> login(LoginParameters loginParameters);

    Single<ChangebankResponse> updateCredentials(UpdateCredentialsParameters updateCredentialsParameters);

    Single<Session> getSession();

    Single<LoginParameters.LoginMethodType> getLoginMethodType();

    Single<Optional<byte[]>> getPinSalt();

    Single<ChangebankResponse> checkUsernameExists(UsernameParameters usernameParameters);

    Single<Optional<String>> getSavedUsername();

    Single<Optional<String>> getCachedUsername();

    Single<ChangebankResponse> logout(Parameters parameters);

    Single<ChangebankResponse> changePassword(UpdatePasswordParameters updatePasswordParameters);

    Single<Boolean> checkPasscodeEnabled(CheckPasscodeEnabledParameters parameters);

    void saveSession(Session session);

    void savePinSalt(byte[] pinSalt, String username);

    void saveUsername(String username);

    void clearAllAuthenticationDatasourceCache();

    void clearPinAndFingerprint();

    void updateLocalStorage(LoginParameters loginParameters);

    Observable<FingerprintResponse> readFingerprint(String username);

    Single<FingerprintResponse> readFingerprintInstant(String username);

    Completable writeFingerprint(String username, String passwordHash);

    Single<Boolean> canStoreFingerprintSecurely();

    Completable clearAllFingerprint();

    Single<Boolean> isUserFingerprintSaved(String username);

    Completable clearSpecificFingerprint(String username);

    Single<Session> resendLoginMFA(ResendTwoFactorParameters resendTwoFactorParameters);

    Single<Session> verifyLoginMFA(TwoFactorParameters twoFactorParameters);

    Single<ChangebankResponse> updateSecurityProfile(SecurityProfileParameters securityProfileParameters);

    Single<SecurityProfile> getSecurityProfile(GetSecurityProfileParameters getSecurityProfileParameters);

    Completable clearPasscode();
}
