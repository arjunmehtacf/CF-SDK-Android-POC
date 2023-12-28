package com.example.cf_sdk.logic.changebanksdk.validation;

import com.example.cf_sdk.R;

import io.reactivex.Single;

/**
 *
 * Passcode Validator.
 */

public class PasscodeValidator implements Validator<String, Single<Boolean>> {
    private int PIN_MINIMUM_LENGTH = 4;
    private int PIN_MAXIMUM_LENGTH = 10;

    private final StringValidator mStringValidator;
    private String mNumberRegex = "^[0-9]*$";

    public PasscodeValidator(StringValidator stringValidator) {
        mStringValidator = stringValidator;
    }

    @Override
    public Single<Boolean> validate(String input) {
        final ValidationError validationError = new ValidationError();

        if(!mStringValidator.validate(input)
                || input.length() < PIN_MINIMUM_LENGTH
                || input.length() > PIN_MAXIMUM_LENGTH
                || mStringValidator.containsWhiteSpaces(input)){
            validationError.addErrorId(R.string.sdk_error_passcode_length);

        }

        //Check if string contains number
        if (!input.matches(mNumberRegex)) {
            validationError.addErrorId(R.string.sdk_error_passcode_number_only);
        }

        if (!validationError.hasError()) {
            return Single.just(true);
        } else {
            return Single.error(validationError);
        }
    }
}
