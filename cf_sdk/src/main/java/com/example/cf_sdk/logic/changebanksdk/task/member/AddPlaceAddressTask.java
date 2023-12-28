package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.CompletableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceDetailSerializer;
import com.example.cf_sdk.logic.changebanksdk.parameter.google.PlaceDetailParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.sanitization.AddressSanitizer;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.AddressValidator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 *
 * Use case for adding an address.
 */

public class AddPlaceAddressTask extends CompletableUseCase<PlaceDetailParameters> {
    private final AuthenticationDatasource mAuthenticationDatasource;
    private final GoogleDatasource mGoogleRepository;
    private final AddressSanitizer mAddressSanitizer;
    private MemberDatasource mMemberRepository;
    private AddressValidator mAddressValidator;

    @Inject
    public AddPlaceAddressTask(
            AddressSanitizer addressSanitizer,
            AddressValidator addressValidator,
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") GoogleDatasource googleRepository,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mGoogleRepository = googleRepository;
        mMemberRepository = memberRepository;
        mAddressValidator = addressValidator;
        mAddressSanitizer = addressSanitizer;
    }

    @Override
    protected Completable buildUseCaseObservable(final PlaceDetailParameters parameters) {
        return mGoogleRepository.getPlaceDetail(parameters.getPlaceId())
                .flatMap(new Function<PlaceDetailSerializer, SingleSource<? extends AddAddressParameters>>() {
                    @Override
                    public SingleSource<? extends AddAddressParameters> apply(PlaceDetailSerializer placeDetailSerializer) throws Exception {
                        AddAddressParameters addAddressParameters = placeDetailSerializer.convertToAddAddressParameters(parameters);
                        return mAddressSanitizer.sanitize(addAddressParameters);
                    }
                })
                .flatMap(new Function<AddAddressParameters, SingleSource<? extends AddAddressParameters>>() {
                    @Override
                    public SingleSource<? extends AddAddressParameters> apply(AddAddressParameters addAddressParameters) throws Exception {
                        return Single.zip(
                                mAddressValidator.validate(addAddressParameters),
                                mAuthenticationDatasource.getSession(),
                                new BiFunction<AddAddressParameters, Session, AddAddressParameters>() {
                                    @Override
                                    public AddAddressParameters apply(AddAddressParameters addAddressParameters, Session session) throws Exception {
                                        addAddressParameters.addToken(session.getToken());
                                        addAddressParameters.setMemberId(session.getCustomerNumber());
                                        return addAddressParameters;
                                    }
                                });
                    }
                })
                .flatMapCompletable(new Function<AddAddressParameters, Completable>() {
                    @Override
                    public Completable apply(AddAddressParameters addAddressParameters) throws Exception {
                        return mMemberRepository.addAddress(addAddressParameters);
                    }
                });
    }
}
