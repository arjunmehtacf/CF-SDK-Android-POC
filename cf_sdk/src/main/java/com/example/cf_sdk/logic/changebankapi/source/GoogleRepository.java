package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceAutocompleteSerializer;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceDetailSerializer;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Authentication repository is in charge of choosing the correct {@link AuthenticationDatasource}.
 */

public class GoogleRepository implements GoogleDatasource {
    private static final String TAG = GoogleRepository.class.getSimpleName();
    private final GoogleDatasource mGoogleDatasource;

    @Inject
    public GoogleRepository(@Named("remote") GoogleDatasource googleDatasource) {
        mGoogleDatasource = googleDatasource;
    }


    @Override
    public Single<PlaceDetailSerializer> getPlaceDetail(String placeId) {
        return mGoogleDatasource.getPlaceDetail(placeId);
    }

    @Override
    public Single<PlaceAutocompleteSerializer> getPlacePredictions(String input) {
        return mGoogleDatasource.getPlacePredictions(input);
    }
}
