package com.example.cf_sdk.changebankapi.network.api;





import static com.example.cf_sdk.changebankapi.Endpoints.Google.AUTOCOMPLETE_PREDICTIONS;
import static com.example.cf_sdk.changebankapi.Endpoints.Google.PLACE_DETAILS;
import static com.example.cf_sdk.changebankapi.network.api.ApiConfig.GOOGLE_PLACES_BASE_ENDPOINT;


import com.example.cf_sdk.changebankapi.model.google.PlaceAutocompleteSerializer;
import com.example.cf_sdk.changebankapi.model.google.PlaceDetailSerializer;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by victorojeda on 11/22/16.
 * <p>
 * Retrofit interface to connect with Member API.
 */

public interface GoogleApi {

    @GET(GOOGLE_PLACES_BASE_ENDPOINT + PLACE_DETAILS)
    @Headers("Content-Type: application/json")
    Single<PlaceDetailSerializer> getPlace(
            @Query("key") String key,
            @Query("placeid") String placeId
    );

    @GET(GOOGLE_PLACES_BASE_ENDPOINT + AUTOCOMPLETE_PREDICTIONS)
    @Headers("Content-Type: application/json")
    Single<PlaceAutocompleteSerializer> getAutocompletePredictions(
            @Query("key") String key,
            @Query("input") String input
    );
}
