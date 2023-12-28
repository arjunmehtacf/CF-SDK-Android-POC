package com.example.cf_sdk.logic.changebanksdk.task.validation;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.sanitization.StringSanitizer;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.NameValidator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task for Name validation.
 */

public class NameValidationTask extends SingleUseCase<StringParameters, Boolean> {

    private final StringSanitizer mStringSanitizer;
    private final NameValidator mNameValidator;


    @Inject
    public NameValidationTask(StringSanitizer stringSanitizer,
                              NameValidator nameValidator,
                              @Named("io") ExecutionThread backgroundExecutionThread,
                              @Named("ui") ExecutionThread uiExecutionThread,
                              @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mStringSanitizer = stringSanitizer;
        mNameValidator = nameValidator;
    }

    @Override
    protected Single<Boolean> buildUseCaseObservable(StringParameters parameters) {
        return Single
                .just(mStringSanitizer.sanitize(parameters.getFirstString()))
                .flatMap(new Function<String, SingleSource<? extends Boolean>>() {
                    @Override
                    public SingleSource<? extends Boolean> apply(String s) throws Exception {
                        return mNameValidator.validate(s);
                    }
                });
    }

}
