package com.example.cf_sdk.logic.changebanksdk.task.validation;

import com.example.cf_sdk.logic.changebanksdk.ObservableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.validation.EmailValidationParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.sanitization.EmailSanitizer;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.EmailValidator;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 *
 * Task for Email validation.
 */

public class EmailValidationOnlyTask extends ObservableUseCase<EmailValidationParameters, ChangebankResponse> {

    private final EmailSanitizer mEmailSanitizer;
    private final EmailValidator mEmailValidator;
    private final MemberDatasource mMemberRepository;

    private final PublishSubject<EmailValidationParameters> mSubject;

    @Inject
    public EmailValidationOnlyTask(EmailSanitizer emailSanitizer,
                                   EmailValidator emailValidator,
                                   @Named("repository") MemberDatasource memberRepository,
                                   @Named("io") ExecutionThread backgroundExecutionThread,
                                   @Named("ui") ExecutionThread uiExecutionThread,
                                   @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mBackgroundExecutionThread = backgroundExecutionThread;
        mUiExecutionThread = uiExecutionThread;

        mEmailSanitizer = emailSanitizer;
        mEmailValidator = emailValidator;
        mMemberRepository = memberRepository;

        mSubject = PublishSubject.create();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Observer<ChangebankResponse> observer, EmailValidationParameters evp) {
        if (!mSubject.hasObservers()) {
            buildUseCaseObservable(evp)
                    .observeOn(mUiExecutionThread.getScheduler())
                    .compose(mErrorHandler)
                    .subscribe(observer);
        }
        mSubject.onNext(evp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Observable<ChangebankResponse> buildUseCaseObservable(EmailValidationParameters evp) {
        return mSubject.debounce(500, TimeUnit.MILLISECONDS, mBackgroundExecutionThread.getScheduler())
                .flatMapSingle(new Function<EmailValidationParameters, SingleSource<EmailValidationParameters>>() {
                    @Override
                    public SingleSource<EmailValidationParameters> apply(EmailValidationParameters evp) throws Exception {
                        return mEmailSanitizer.sanitize(evp);
                    }
                })
                .flatMapSingle(new Function<EmailValidationParameters, SingleSource<EmailValidationParameters>>() {
                    @Override
                    public SingleSource<EmailValidationParameters> apply(EmailValidationParameters evp) throws Exception {
                        return mEmailValidator.validate(evp);
                    }
                })
                .switchMapSingle(new Function<EmailValidationParameters, SingleSource<ChangebankResponse>>() {
                    @Override
                    public SingleSource<ChangebankResponse> apply(EmailValidationParameters evp) throws Exception {
                        ChangebankResponse changebankResponse = new ChangebankResponse();
                        changebankResponse.setHttpCode(HttpURLConnection.HTTP_NOT_FOUND);
                        return Single.error(changebankResponse);
                    }
                });
    }
}
