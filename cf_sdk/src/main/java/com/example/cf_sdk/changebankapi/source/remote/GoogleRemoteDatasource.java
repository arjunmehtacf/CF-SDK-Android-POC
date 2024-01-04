package com.example.cf_sdk.changebankapi.source.remote;




import com.example.cf_sdk.changebankapi.model.google.PlaceAutocompleteSerializer;
import com.example.cf_sdk.changebankapi.model.google.PlaceDetailSerializer;
import com.example.cf_sdk.changebankapi.network.api.GoogleApi;
import com.example.cf_sdk.changebankapi.source.datasource.GoogleDatasource;

import io.reactivex.Single;

/**
 *
 * Remote datasource to call Member endpoints.
 */

public class GoogleRemoteDatasource implements GoogleDatasource {
    private final String mApiKey;
    private GoogleApi mGoogleApi;


    public GoogleRemoteDatasource(GoogleApi googleApi, String apiKey) {
        mGoogleApi = googleApi;
        mApiKey = apiKey;
    }

    @Override
    public Single<PlaceDetailSerializer> getPlaceDetail(String placeId) {
        return mGoogleApi.getPlace(mApiKey, placeId);
    }

    @Override
    public Single<PlaceAutocompleteSerializer> getPlacePredictions(String input) {
        return mGoogleApi.getAutocompletePredictions(mApiKey, input);
    }

}
