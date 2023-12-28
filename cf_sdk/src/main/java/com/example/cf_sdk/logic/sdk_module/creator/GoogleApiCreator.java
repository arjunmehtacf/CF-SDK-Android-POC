package com.example.cf_sdk.logic.sdk_module.creator;

import com.example.cf_sdk.logic.changebankapi.network.api.ApiCreator;
import com.example.cf_sdk.logic.changebankapi.network.api.GoogleApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;

import retrofit2.Retrofit;

/**
 *
 * Network {@link GoogleApi} creator.
 */

public class GoogleApiCreator implements ApiCreator<GoogleApi> {
    @Override
    public GoogleApi create(Logger logger, Retrofit retrofit) {
        return retrofit.create(GoogleApi.class);
    }
}
