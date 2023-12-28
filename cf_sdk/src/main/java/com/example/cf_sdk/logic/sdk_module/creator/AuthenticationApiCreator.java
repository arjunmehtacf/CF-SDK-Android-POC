package com.example.cf_sdk.logic.sdk_module.creator;

import com.example.cf_sdk.logic.changebankapi.network.api.ApiCreator;
import com.example.cf_sdk.logic.changebankapi.network.api.AuthenticationApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;

import retrofit2.Retrofit;

/**
 *
 * Network {@link AuthenticationApi} creator.
 */

public class AuthenticationApiCreator implements ApiCreator<AuthenticationApi> {
    @Override
    public AuthenticationApi create(Logger logger, Retrofit retrofit) {
        return retrofit.create(AuthenticationApi.class);
    }
}
