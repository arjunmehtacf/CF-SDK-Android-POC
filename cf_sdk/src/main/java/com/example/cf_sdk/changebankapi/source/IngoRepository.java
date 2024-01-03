package com.example.cf_sdk.changebankapi.source;



import com.example.cf_sdk.changebankapi.parameter.StringParameters;
import com.example.cf_sdk.changebankapi.parameter.ingo.VerifyIngoSSNParameters;
import com.example.cf_sdk.changebankapi.response.IngoResponse;
import com.example.sdk_no_dagger.changebankapi.source.datasource.IngoDatasource;

import io.reactivex.Single;

/**
 *
 * Ingo repository is in charge of choosing the correct {@link IngoDatasource}.
 */

public class IngoRepository implements IngoDatasource {
    private static final String TAG = IngoRepository.class.getSimpleName();
    private final IngoDatasource mIngoDatasource;


    public IngoRepository( IngoDatasource ingoDatasource) {
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
