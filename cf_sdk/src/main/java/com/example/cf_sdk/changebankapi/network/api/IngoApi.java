package com.example.cf_sdk.changebankapi.network.api;





import static com.example.cf_sdk.changebankapi.Endpoints.Member.CHECK_DEPOSIT;
import static com.example.cf_sdk.changebankapi.network.api.ApiConfig.INGO_BASE_ENDPOINT;

import com.example.cf_sdk.changebankapi.parameter.ingo.VerifyIngoSSNParameters;
import com.example.cf_sdk.changebankapi.response.IngoResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *
 * Retrofit interface to connect with Ingo API.
 */

public interface IngoApi {

    @POST(INGO_BASE_ENDPOINT + CHECK_DEPOSIT)
    @Headers("Content-Type: application/json")
    Single<IngoResponse> verifyIngoSSSN(@HeaderMap Map<String, String> headers,
                                        @Body VerifyIngoSSNParameters verifyIngoSSNParameters);

    @GET(INGO_BASE_ENDPOINT + CHECK_DEPOSIT)
    @Headers("Content-Type: application/json")
    Single<IngoResponse> getIngoSSOToken(@HeaderMap Map<String, String> headers,
                                         @Query("deviceType") String deviceType);
}
