package com.example.cf_sdk.logic.changebanksdk.validation;

import androidx.annotation.NonNull;

import com.example.cf_sdk.R;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UsernameParameters;

import dagger.internal.Preconditions;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Login Parameters Validation logic.
 */

public class LoginValidator implements Validator<LoginParameters, Single<LoginParameters>> {


    private final StringValidator mStringValidator;

    private final UsernameValidator usernameValidator;

    public LoginValidator(StringValidator stringValidator) {
        mStringValidator = stringValidator;
        usernameValidator = new UsernameValidator(mStringValidator);
    }

    @Override
    public Single<LoginParameters> validate(@NonNull final LoginParameters input) {
        Preconditions.checkNotNull(input);


        if (!mStringValidator.validate(input.getUsername()) ||
                (!mStringValidator.validate(input.getPassword())
                        && !mStringValidator.validate(input.getPin())
                        && !mStringValidator.validate(input.getBiometric()))) {
            return Single.error(returnGenericLoginError());
        }

        UsernameParameters usernameParameters = new UsernameParameters(null, input.getUsername());
        return usernameValidator.validate(usernameParameters)
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends UsernameParameters>>() {
                    @Override
                    public SingleSource<? extends UsernameParameters> apply(Throwable throwable) throws Exception {
                        return Single.error(returnGenericLoginError());
                    }
                })
                .flatMap(new Function<UsernameParameters, SingleSource<? extends LoginParameters>>() {
                    @Override
                    public SingleSource<? extends LoginParameters> apply(UsernameParameters usernameParameters) throws Exception {
                        return Single.just(input);
                    }
                });
    }


    private ValidationError returnGenericLoginError() {
        final ValidationError validationError = new ValidationError();
        validationError.addErrorId(R.string.sdk_error_login_invalid);
        return validationError;
    }

}
