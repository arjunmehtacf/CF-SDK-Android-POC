package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.ingo.VerifyIngoSSNParameters;
import com.example.cf_sdk.logic.changebanksdk.response.IngoResponse;
import com.example.cf_sdk.logic.changebanksdk.source.IngoDatasource;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Ingo repository is in charge of choosing the correct {@link IngoDatasource}.
 */

public class IngoRepository implements IngoDatasource {
    private static final String TAG = IngoRepository.class.getSimpleName();
    private final IngoDatasource mIngoDatasource;

    @Inject
    public IngoRepository(@Named("remote") IngoDatasource ingoDatasource) {
        mIngoDatasource = ingoDatasource;
    }

    @Override
    public Single<IngoResponse> verifyIngoSSSN(VerifyIngoSSNParameters verifyIngoSSNParameters) {
        return mIngoDatasource.verifyIngoSSSN(verifyIngoSSNParameters);

    }

    @Override
    public Single<IngoResponse> getIngoSSOToken(StringParameters stringParameters) {
        return mIngoDatasource.getIngoSSOToken(stringParameters);

    }

}
