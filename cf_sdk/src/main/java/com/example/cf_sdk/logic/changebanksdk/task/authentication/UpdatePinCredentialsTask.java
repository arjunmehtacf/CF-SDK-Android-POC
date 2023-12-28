package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdateCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.PasscodeValidator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for User update pin credentials scenarios.
 */

public class UpdatePinCredentialsTask extends SingleUseCase<UpdateCredentialsParameters, ChangebankResponse> {

    private final PasscodeValidator mPasscodeValidator;
    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public UpdatePinCredentialsTask(
            PasscodeValidator passcodeValidator,
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mPasscodeValidator = passcodeValidator;
        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<ChangebankResponse> buildUseCaseObservable(
            final UpdateCredentialsParameters parameters) {
        return mPasscodeValidator.validate(parameters.getPin())
                .flatMap(new Function<Boolean, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(Boolean aBoolean) throws Exception {
                        return mAuthenticationRepository.updateCredentials(parameters.withHashedPin());
                    }
                });
    }

    public void executePinValidation(SingleObserver<Boolean> observer, String pin) {
        mPasscodeValidator.validate(pin).subscribe(observer);
    }
}
