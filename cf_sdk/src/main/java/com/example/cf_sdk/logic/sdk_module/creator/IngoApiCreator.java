package com.example.cf_sdk.logic.sdk_module.creator;

import com.example.cf_sdk.logic.changebankapi.network.api.ApiCreator;
import com.example.cf_sdk.logic.changebankapi.network.api.IngoApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;

import retrofit2.Retrofit;

/**
 *
 * Network {@link IngoApi} creator.
 */

public class IngoApiCreator implements ApiCreator<IngoApi> {
    @Override
    public IngoApi create(Logger logger, Retrofit retrofit) {
        return retrofit.create(IngoApi.class);
    }
}
