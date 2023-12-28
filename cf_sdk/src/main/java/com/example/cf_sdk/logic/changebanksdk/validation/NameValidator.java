package com.example.cf_sdk.logic.changebanksdk.validation;

import com.example.cf_sdk.R;

import io.reactivex.Single;

/**
 *
 * Address Validator.
 */

public class NameValidator implements Validator<String, Single<Boolean>> {

    private final StringValidator mStringValidator;

    public NameValidator(StringValidator stringValidator){
        mStringValidator = stringValidator;
    }

    @Override
    public Single<Boolean> validate(String input) {
        final ValidationError validationError = new ValidationError();
        if(mStringValidator.validate(input)) {
            return Single.just(true);
        } else {
            validationError.addErrorId(R.string.sdk_error_required_field);
            return Single.error(validationError);
        }
    }
}
