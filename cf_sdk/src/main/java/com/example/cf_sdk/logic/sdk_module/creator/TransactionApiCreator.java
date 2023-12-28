package com.example.cf_sdk.logic.sdk_module.creator;

import com.example.cf_sdk.logic.changebankapi.network.api.ApiCreator;
import com.example.cf_sdk.logic.changebankapi.network.api.TransactionApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;

import retrofit2.Retrofit;

/**
 *
 * Network {@link TransactionApi} creator.
 */

public class TransactionApiCreator implements ApiCreator<TransactionApi> {
    @Override
    public TransactionApi create(Logger logger, Retrofit retrofit) {
        return retrofit.create(TransactionApi.class);
    }
}
