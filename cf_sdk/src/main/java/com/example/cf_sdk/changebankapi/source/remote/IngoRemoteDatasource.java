package com.example.cf_sdk.changebankapi.source.remote;

import com.example.cf_sdk.changebankapi.network.api.IngoApi;
import com.example.cf_sdk.changebankapi.parameter.StringParameters;
import com.example.cf_sdk.changebankapi.parameter.ingo.VerifyIngoSSNParameters;
import com.example.cf_sdk.changebankapi.response.IngoResponse;
import com.example.cf_sdk.changebankapi.source.datasource.IngoDatasource;

import io.reactivex.Single;

/**
 *
 * Remote datasource to call Ingo endpoints.
 */

public class IngoRemoteDatasource implements IngoDatasource {
    private IngoApi mIngoApi;


    public IngoRemoteDatasource(IngoApi ingoApi) {
        mIngoApi = ingoApi;
    }

    @Override
    public Single<IngoResponse> verifyIngoSSSN(VerifyIngoSSNParameters verifyIngoSSNParameters) {
        return mIngoApi.verifyIngoSSSN(verifyIngoSSNParameters.getHeaders(), verifyIngoSSNParameters);
    }

    @Override
    public Single<IngoResponse> getIngoSSOToken(StringParameters stringParameters) {
        return mIngoApi.getIngoSSOToken(stringParameters.getHeaders(), stringParameters.getFirstString());
    }

}
