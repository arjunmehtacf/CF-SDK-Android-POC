package com.example.cf_sdk.logic.changebanksdk.task.google;

import com.example.cf_sdk.logic.changebanksdk.ObservableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceAutocompleteSerializer;
import com.example.cf_sdk.logic.changebanksdk.parameter.google.PlaceAutocompleteParameters;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 *
 * Use case for getting place autocomplete predictions
 */

public class PlaceAutocompleteTask extends ObservableUseCase<PlaceAutocompleteParameters, PlaceAutocompleteSerializer> {
    private final ExecutionThread mBackgroundExecutionThread;
    private GoogleDatasource mGoogleRepository;
    private PublishSubject<PlaceAutocompleteParameters> mSubject;


    @Inject
    public PlaceAutocompleteTask(
            @Named("repository") GoogleDatasource googleDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mSubject = PublishSubject.create();
        mBackgroundExecutionThread = backgroundExecutionThread;
        mGoogleRepository = googleDatasource;
    }

    protected Observable<PlaceAutocompleteSerializer> buildUseCaseObservable(final PlaceAutocompleteParameters parameters) {
        return mSubject.debounce(1, TimeUnit.SECONDS, mBackgroundExecutionThread.getScheduler())
                .switchMapSingle(new Function<PlaceAutocompleteParameters, SingleSource<PlaceAutocompleteSerializer>>() {
                    @Override
                    public SingleSource<PlaceAutocompleteSerializer> apply(PlaceAutocompleteParameters placeAutocompleteParameters) throws Exception {
                        return mGoogleRepository.getPlacePredictions(placeAutocompleteParameters.getSearchInput())
                                .subscribeOn(mBackgroundExecutionThread.getScheduler());
                    }
                });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Observer<PlaceAutocompleteSerializer> placeAutocompleteSerializerObserver,
                        PlaceAutocompleteParameters parameters) {
        if (!mSubject.hasObservers()) {
            buildUseCaseObservable(parameters)
                    .observeOn(mUiExecutionThread.getScheduler())
                    .compose(mErrorHandler)
                    .subscribe(placeAutocompleteSerializerObserver);
        }
        mSubject.onNext(parameters);
    }
}
