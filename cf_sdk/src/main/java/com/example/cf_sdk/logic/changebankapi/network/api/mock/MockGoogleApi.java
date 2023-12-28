package com.example.cf_sdk.logic.changebankapi.network.api.mock;

import com.example.cf_sdk.logic.changebankapi.network.api.GoogleApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceAutocompleteSerializer;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceDetailSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 *
 * Mock implementation of {@link MockGoogleApi}.
 */

public class MockGoogleApi implements GoogleApi {
    private static final String TAG = MockGoogleApi.class.getSimpleName();
    private final Logger mLogger;

    public MockGoogleApi(Logger logger) {
        mLogger = logger;
    }

    @Override
    public Single<PlaceDetailSerializer> getPlace(final String key, final String placeId) {
        return Single.zip(
                Single.just(key),
                Single.just(placeId),
                new BiFunction<String, String, PlaceDetailSerializer>() {
                    @Override
                    public PlaceDetailSerializer apply(String s, String s2) throws Exception {
                        ArrayList<PlaceDetailSerializer.AddressComponent> components = new ArrayList<>();

                        components.add(PlaceDetailSerializer
                                .AddressComponent
                                .create(new String[]{"locality"}, "Earth", "Life"));

                        components.add(PlaceDetailSerializer
                                .AddressComponent
                                .create(new String[]{"administrative_area_level_1"},
                                        "Illuminati", "Scientology"));

                        components.add(PlaceDetailSerializer
                                .AddressComponent
                                .create(new String[]{"country"}, "Solar system", "Sun"));

                        components.add(PlaceDetailSerializer
                                .AddressComponent
                                .create(new String[]{"postal_code"}, "90023", "90023-29"));


                        return PlaceDetailSerializer.create(PlaceDetailSerializer.Place.create(
                                placeId,
                                key,
                                components));
                    }
                });
    }

    @Override
    public Single<PlaceAutocompleteSerializer> getAutocompletePredictions(
            final String key,
            final String input) {
        return Observable.range(1, new Random().nextInt(20))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "getAutocompletePredictions onSubscribe: " +
                                "key[" + key +
                                "] input[" + input + "]");
                    }
                })
                .map(new Function<Integer, PlaceAutocompleteSerializer.Prediction>() {
                    @Override
                    public PlaceAutocompleteSerializer.Prediction apply(Integer integer) throws Exception {
                        return PlaceAutocompleteSerializer.Prediction.create(
                                integer.toString(),
                                input + "abc",
                                PlaceAutocompleteSerializer.StructuredFormatting.create("Solar System, Earth"));
                    }
                })
                .toList()
                .map(new Function<List<PlaceAutocompleteSerializer.Prediction>, PlaceAutocompleteSerializer>() {
                    @Override
                    public PlaceAutocompleteSerializer apply(List<PlaceAutocompleteSerializer.Prediction> predictions) throws Exception {
                        return PlaceAutocompleteSerializer.create(predictions);
                    }
                })
                .doOnSuccess(new Consumer<PlaceAutocompleteSerializer>() {
                    @Override
                    public void accept(PlaceAutocompleteSerializer placeAutocompleteSerializer) throws Exception {
                        mLogger.debug(TAG, "getAutocompletePredictions onNext: " +
                                "key[" + key +
                                "] input[" + input + "]");
                    }
                });
    }
}
