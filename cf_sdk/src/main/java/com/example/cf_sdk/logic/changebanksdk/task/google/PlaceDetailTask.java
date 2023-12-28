package com.example.cf_sdk.logic.changebanksdk.task.google;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceDetailSerializer;
import com.example.cf_sdk.logic.changebanksdk.model.member.Address;
import com.example.cf_sdk.logic.changebanksdk.parameter.google.PlaceDetailParameters;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for getting place details
 */

public class PlaceDetailTask extends SingleUseCase<PlaceDetailParameters, Address> {
    private GoogleDatasource mGoogleRepository;

    @Inject
    public PlaceDetailTask(
            @Named("repository") GoogleDatasource googleDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mGoogleRepository = googleDatasource;
    }

    protected Single<Address> buildUseCaseObservable(PlaceDetailParameters parameters) {
        return mGoogleRepository.getPlaceDetail(parameters.getPlaceId())
                .flatMap(new Function<PlaceDetailSerializer, SingleSource<? extends Address>>() {
                    @Override
                    public SingleSource<? extends Address> apply(PlaceDetailSerializer placeDetailSerializer) throws Exception {
                        Address address = transformPlaceDetail(placeDetailSerializer.getPlace().getAddressComponents());


                        return Single.just(address);
                    }
                });
    }

    private Address transformPlaceDetail(List<PlaceDetailSerializer.AddressComponent> addressComponents) {
        StringBuilder sbStreet = new StringBuilder();
        Address address = new Address();

        for (PlaceDetailSerializer.AddressComponent component : addressComponents) {
            if (Arrays.asList(component.getTypes()).contains("street_number")) {
                sbStreet.insert(0, component.getLong_name() + " ");
            } else if (Arrays.asList(component.getTypes()).contains("route")) {
                sbStreet.append(component.getLong_name());
            } else if (Arrays.asList(component.getTypes()).contains("locality")) {
                address.setCity(component.getLong_name());
            } else if (Arrays.asList(component.getTypes()).contains("administrative_area_level_1")) {
                address.setState(component.getShort_name());
            } else if (Arrays.asList(component.getTypes()).contains("country")) {
             //   address.setCountry(component.getShort_name());
            } else if (Arrays.asList(component.getTypes()).contains("postal_code")) {
                address.setZipCode(component.getLong_name());
            }
        }
        address.setAddressLine1(sbStreet.toString());

        return address;
    }
}
