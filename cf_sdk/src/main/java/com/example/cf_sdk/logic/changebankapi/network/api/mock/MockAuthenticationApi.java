package com.example.cf_sdk.logic.changebankapi.network.api.mock;

import com.example.cf_sdk.logic.changebankapi.network.api.AuthenticationApi;
import com.example.cf_sdk.logic.changebanksdk.exception.LoginTwoFactorTooManyAttemptsException;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.model.Features;
import com.example.cf_sdk.logic.changebanksdk.model.Role;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.SecurityLevel;
import com.example.cf_sdk.logic.changebanksdk.model.member.SecurityProfile;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.ResetPassword;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.SecurityProfileParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.TwoFactorParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdateCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdatePasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UsernameParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 *
 * Mock implementation of {@link AuthenticationApi}.
 */

public class MockAuthenticationApi implements AuthenticationApi {
    private static final String TAG = MockAccountApi.class.getSimpleName();
    private static final String KEY_TWO_FACTOR = "twofactor";

    private final Logger mLogger;

    public MockAuthenticationApi(Logger logger) {
        mLogger = logger;
    }

    @Override
    public Single<Session> authenticateByToken(Map<String, String> headers) {
        return Single
                .fromCallable(new Callable<Session>() {
                    @Override
                    public Session call() throws Exception {
                        return Session.create("abc123abc123")
                                .withMemberId(1L)
                                .withNeedsTwoFactor(false)
                                .withRole(Role.USER)
                                .withFeatures(Features.createWithAllFeatures())
                                .withTokenExpirationDate(Long.MAX_VALUE);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "authenticateByToken onSubscribe");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        mLogger.debug(TAG, "authenticateByToken onNext: session[" + session + "]");
                    }
                });
    }

    @Override
    public Single<Session> login(String url,
            Map<String, String> headers,
            final LoginParameters loginParameters) {
        return Single
                .fromCallable(new Callable<Session>() {
                    @Override
                    public Session call() throws Exception {
                        return Session.create("abc123abc123")
                                .withMemberId(1L)
                                .withNeedsTwoFactor(KEY_TWO_FACTOR.equals(loginParameters.getPassword()))
                                .withRole(Role.USER)
                                .withFeatures(Features.createWithAllFeatures())
                                .withTokenExpirationDate(Long.MAX_VALUE);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "login onSubscribe: loginParameters[" + loginParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        mLogger.debug(TAG, "login onNext: session[" + session + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateCredentials(
            Map<String, String> headers,
            long memberId,
            final UpdateCredentialsParameters parameters) {
        return Single
                .fromCallable(new Callable<ChangebankResponse>() {
                    @Override
                    public ChangebankResponse call() throws Exception {
                        return new ChangebankResponse();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "updateCredentials onSubscribe: " +
                                "updateCredentialsParameters[" + parameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "updateCredentials onNext: "
                                + "changebankResponse[" + response + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> changePassword(
            Map<String, String> headers,
            final UpdatePasswordParameters updatePasswordParameters) {
        return Single
                .fromCallable(new Callable<ChangebankResponse>() {
                    @Override
                    public ChangebankResponse call() throws Exception {
                        return new ChangebankResponse();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "changePassword onSubscribe: " +
                                "updatePassword[" + updatePasswordParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "changePassword onNext: "
                                + "changebankResponse[" + response + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> logout(Map<String, String> headers) {
        return Single
                .fromCallable(new Callable<ChangebankResponse>() {
                    @Override
                    public ChangebankResponse call() throws Exception {
                        return new ChangebankResponse();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "logout onSubscribe");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "logout onNext: response[" + response + "]");
                    }
                });
    }

    @Override
    public Single<Session> resendCode(Map<String, String> headers, final long memberId) {
        return Single
                .fromCallable(new Callable<Session>() {
                    @Override
                    public Session call() throws Exception {
                        return Session.create("abc123abc123")
                                .withMemberId(memberId)
                                .withNeedsTwoFactor(true)
                                .withRole(Role.USER)
                                .withFeatures(Features.createWithAllFeatures())
                                .withTokenExpirationDate(Long.MAX_VALUE);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "resendCode onSubscribe: memberId[" + memberId + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        mLogger.debug(TAG, "resendCode onNext: session[" + session + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> resetPassword(
            Map<String, String> headers,
            final ResetPassword resetPassword) {
        return Single
                .fromCallable(new Callable<ChangebankResponse>() {
                    @Override
                    public ChangebankResponse call() throws Exception {
                        return new ChangebankResponse();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "resetPassword onNext: resetPassword[" + resetPassword + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {

                    }
                });
    }

    @Override
    public Single<Session> twoFactor(Map<String, String> headers, final TwoFactorParameters twoFactorParameters) {
        return Single
                .fromCallable(new Callable<Session>() {
                    @Override
                    public Session call() throws Exception {
                        if (twoFactorParameters.getVerificationCode() == 11111) {
                            return Session.create("abc123abc123")
                                    .withMemberId(twoFactorParameters.getMemberId())
                                    .withNeedsTwoFactor(false)
                                    .withRole(Role.USER)
                                    .withFeatures(Features.createWithAllFeatures())
                                    .withTokenExpirationDate(Long.MAX_VALUE);
                        } else if (twoFactorParameters.getVerificationCode() == 22222) {
                            throw new LoginTwoFactorTooManyAttemptsException("Too many attempts, login and try again.");
                        } else {
                            throw new Exception("Invalid Code");
                        }
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "twoFactor onSubscribe: memberId[" + twoFactorParameters.getMemberId() + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        mLogger.debug(TAG, "twoFactor onNext: session[" + session + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> checkUsernameExists(
            Map<String, String> headers,
            final UsernameParameters usernameParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(404);
        response.setMessage("Username not found");

        return Single.<ChangebankResponse>error(response)
                .delay(1, TimeUnit.SECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "checkUsernameExists onSubscribe: " +
                                "username[" + usernameParameters.getUsername() + "]");
                    }
                })
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "checkUsernameExists onNext: response[" + response + "]");
                    }
                });

    }

    @Override
    public Single<ChangebankResponse> updateSecurityProfile(Map<String, String> headers, long memberId, SecurityProfileParameters securityProfileParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Single.just(response);
    }

    @Override
    public Single<SecurityProfile> getSecurityProfile(Map<String, String> headers, long memberId) {
        SecurityProfile securityProfile = SecurityProfile.CreateWithSecurityLevel(SecurityLevel.HIGH);
        return Single.just(securityProfile);
    }
}
