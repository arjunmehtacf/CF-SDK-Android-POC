package com.example.cf_sdk.logic.changebanksdk.task.validation;

import com.example.cf_sdk.logic.changebanksdk.ObservableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.validation.PhoneValidationParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.sanitization.PhoneSanitizer;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.PhoneValidator;

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
 * Phone validation task.
 */

public class PhoneValidationTask extends ObservableUseCase<PhoneValidationParameters, ChangebankResponse> {
    private final MemberDatasource mMemberRepository;
    private final PhoneValidator mPhoneValidator;
    private final PhoneSanitizer mPhoneSanitizer;
    private PublishSubject<String> mSubject;

    @Inject
    public PhoneValidationTask(PhoneSanitizer phoneSanitizer,
                               PhoneValidator phoneValidator,
                               @Named("repository") MemberDatasource memberRepository,
                               @Named("io") ExecutionThread backgroundExecutionThread,
                               @Named("ui") ExecutionThread uiExecutionThread,
                               @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mPhoneSanitizer = phoneSanitizer;
        mPhoneValidator = phoneValidator;
        mMemberRepository = memberRepository;

        mSubject = PublishSubject.create();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Observer<ChangebankResponse> changebankResponseObserver, final PhoneValidationParameters pvp) {
        if (!mSubject.hasObservers()) {
            buildUseCaseObservable(pvp)
                    .observeOn(mUiExecutionThread.getScheduler())
                    .compose(mErrorHandler)
                    .subscribe(changebankResponseObserver);
        }
        mSubject.onNext(pvp.getPhone());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Observable<ChangebankResponse> buildUseCaseObservable(
            final PhoneValidationParameters pvp) {
        return mSubject.debounce(1, TimeUnit.SECONDS, mBackgroundExecutionThread.getScheduler())
                .switchMapSingle(new Function<String, SingleSource<String>>() {
                    @Override
                    public SingleSource<String> apply(String sanitizedPhone) throws Exception {
                        return mPhoneSanitizer.sanitize(sanitizedPhone);
                    }
                })
                .switchMapSingle(new Function<String, SingleSource<String>>() {
                    @Override
                    public SingleSource<String> apply(String sanitizedPhone) throws Exception {
                        return mPhoneValidator.validate(sanitizedPhone);
                    }
                })
                .switchMapSingle(new Function<String, SingleSource<ChangebankResponse>>() {
                    @Override
                    public SingleSource<ChangebankResponse> apply(String validPhone) throws Exception {
                        return mMemberRepository
                                .phoneAvailability(PhoneValidationParameters.create("+"+validPhone))
                                .subscribeOn(mBackgroundExecutionThread.getScheduler());
                    }
                });

    }
}
