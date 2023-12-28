package com.example.cf_sdk.logic.changebanksdk.task.validation;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.PasswordValidator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Task for Password validation.
 */

public class PasswordValidationTask extends SingleUseCase<StringParameters, Boolean> {

    private final PasswordValidator mPasswordValidator;


    @Inject
    public PasswordValidationTask(PasswordValidator passwordValidator,
                                  @Named("io") ExecutionThread backgroundExecutionThread,
                                  @Named("ui") ExecutionThread uiExecutionThread,
                                  @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mPasswordValidator = passwordValidator;
    }


    @Override
    protected Single<Boolean> buildUseCaseObservable(StringParameters parameters) {
        return mPasswordValidator.validate(parameters.getFirstString());
    }

}
