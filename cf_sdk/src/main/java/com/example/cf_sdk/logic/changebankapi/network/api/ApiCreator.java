package com.example.cf_sdk.logic.changebankapi.network.api;

import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.util.Creator;

import retrofit2.Retrofit;

/**
 *
 * Interface to abstract the creation of an API.
 */

public interface ApiCreator<R> extends Creator<Retrofit, R>{
    @Override
    R create(Logger logger, Retrofit retrofit);
}
