package com.example.cf_sdk.changebankapi.source;






import com.example.sdk_no_dagger.changebankapi.model.google.PlaceAutocompleteSerializer;
import com.example.sdk_no_dagger.changebankapi.model.google.PlaceDetailSerializer;
import com.example.sdk_no_dagger.changebankapi.source.datasource.GoogleDatasource;

import io.reactivex.Single;



public class GoogleRepository implements GoogleDatasource {
    private static final String TAG = GoogleRepository.class.getSimpleName();
    private final GoogleDatasource mGoogleDatasource;


    public GoogleRepository(GoogleDatasource googleDatasource) {
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
