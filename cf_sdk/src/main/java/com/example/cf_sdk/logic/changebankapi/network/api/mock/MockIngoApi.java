package com.example.cf_sdk.logic.changebankapi.network.api.mock;

import com.example.cf_sdk.logic.changebankapi.network.api.IngoApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.parameter.ingo.VerifyIngoSSNParameters;
import com.example.cf_sdk.logic.changebanksdk.response.IngoResponse;

import java.util.Map;

import io.reactivex.Single;

/**
 *
 * Mock implementation of {@link MockIngoApi}.
 */

public class MockIngoApi implements IngoApi {
    private static final String TAG = MockIngoApi.class.getSimpleName();
    private static final String SSO_TOKEN = "MDBmYWM1MmYtZjI0Mi00ZDNiLThhYzEtNmE5ZDQwZjg4YjQwOmM0ZTAyYTQyLTYwZDEtNDIzMy1hZDIwLWRlZmJhMGZhYzc2ZA==";
    private final Logger mLogger;

    public MockIngoApi(Logger logger) {
        mLogger = logger;
    }


    @Override
    public Single<IngoResponse> verifyIngoSSSN(Map<String, String> headers, VerifyIngoSSNParameters verifyIngoSSNParameters) {
        IngoResponse ingoResponse = new IngoResponse("c4e02a42-60d1-4233-ad20-defba0fac76d", SSO_TOKEN);
        return Single.just(ingoResponse);
    }

    @Override
    public Single<IngoResponse> getIngoSSOToken(Map<String, String> headers, String deviceType) {
        IngoResponse ingoResponse = new IngoResponse("c4e02a42-60d1-4233-ad20-defba0fac76d", SSO_TOKEN);
        return Single.just(ingoResponse);

        //   return Single.error(new Throwable("Failed"));
    }
}
