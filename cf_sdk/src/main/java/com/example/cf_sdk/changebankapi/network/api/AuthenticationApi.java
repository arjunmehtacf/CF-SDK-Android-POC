package com.example.cf_sdk.changebankapi.network.api;






import com.example.cf_sdk.changebankapi.Endpoints;
import com.example.cf_sdk.changebankapi.model.Session;
import com.example.cf_sdk.changebankapi.model.member.SecurityProfile;
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.ResetPassword;
import com.example.cf_sdk.changebankapi.parameter.authentication.SecurityProfileParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.TwoFactorParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.UpdateCredentialsParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.UpdatePasswordParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.UsernameParameters;
import com.example.cf_sdk.defination.response.ChangebankResponse;


import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 *
 * Retrofit interface to connect with Authentication API.
 */

public interface AuthenticationApi {

    /**
     * Authentication by token.
     */
    @GET(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.AUTHENTICATION)
    Single<Session> authenticateByToken(@HeaderMap Map<String, String> headers);

    /**
     * Authentication by credentials: Phone and Password/Pin.
     */
    @POST
    @Headers("Content-Type: application/json")
    Single<Session> login(@Url String url, @HeaderMap Map<String, String> headers,
                              @Body LoginParameters loginParameters);

    /**
     * Update member credentials.
     */
    @PUT(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.CREDENTIALS)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> updateCredentials(@HeaderMap Map<String, String> headers,
                                                 @Path("memberId") long memberId,
                                                 @Body UpdateCredentialsParameters updateCredentialsParameters);

    /**
     * Change member password.
     */
    @PUT(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.PASSWORD)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> changePassword(@HeaderMap Map<String, String> headers,
                                                  @Body UpdatePasswordParameters updatePasswordParameters);

    /**
     * Logout member by token.
     */
    @POST(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.LOGOUT)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> logout(@HeaderMap Map<String, String> headers);

    /**
     * Two-Factor authentication: Re-send code.
     */
    @POST(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.RESEND_CODE)
    @Headers("Content-Type: application/json")
    Single<Session> resendCode(@HeaderMap Map<String, String> headers,
                                   @Query("memberId") long memberId);

    /**
     * Forgotten password (reset)
     */
    @POST(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.RESET_PASSWORD)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> resetPassword(@HeaderMap Map<String, String> headers,
                                                 @Body ResetPassword resetPassword);

    /**
     * Two-Factor authentication: login verification.
     */
    @POST(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.TWO_FACTOR)
    @Headers("Content-Type: application/json")
    Single<Session> twoFactor(@HeaderMap Map<String, String> headers,
                                  @Body TwoFactorParameters twoFactorParameters);

    /**
     * Check if Username is already used by another user.
     */
    @POST(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.USERNAME_AVAILABILITY)
    Single<ChangebankResponse> checkUsernameExists(@HeaderMap Map<String, String> headers,
                                                       @Body UsernameParameters usernameParameters);

    /**
     * Update Security Profile.
     */
    @PUT(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.UPDATE_SECURITY_PROFILE)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> updateSecurityProfile(@HeaderMap Map<String, String> headers,
                                                         @Path("memberId") long memberId,
                                                         @Body SecurityProfileParameters securityProfileParameters);

    /**
     * Retrieves the current Security Profile of member.
     */
    @GET(ApiConfig.AUTHENTICATION_BASE_ENDPOINT + Endpoints.Auth.GET_SECURITY_PROFILE)
    Single<SecurityProfile> getSecurityProfile(@HeaderMap Map<String, String> headers,
                                               @Path("memberId") long memberId);

}
