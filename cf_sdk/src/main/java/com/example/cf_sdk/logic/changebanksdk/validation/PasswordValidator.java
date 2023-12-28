package com.example.cf_sdk.logic.changebanksdk.validation;

import com.example.cf_sdk.R;

import io.reactivex.Single;

/**
 *
 * Password Validator.
 */

public class PasswordValidator implements Validator<String, Single<Boolean>> {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private String mNumberRegex = ".*\\d.*";
    private String mLetterRegex = ".*[A-Z]+.*";
    private String mCharacterRegex = ".*[@$!%*#?&\\=\\-\\+~\\^]+.*";


    private final StringValidator mStringValidator;

    public PasswordValidator(StringValidator stringValidator) {
        mStringValidator = stringValidator;
    }

    @Override
    public Single<Boolean> validate(String input) {
        final ValidationError validationError = new ValidationError();

        //Check if string meets minimum length and has no whitespaces
        if (!mStringValidator.validate(input) || input.length() < MIN_PASSWORD_LENGTH || mStringValidator.containsWhiteSpaces(input)) {
            validationError.addErrorId(R.string.sdk_error_password_length);
        }

        //Check if string contains number
        if (!input.matches(mNumberRegex)) {
            validationError.addErrorId(R.string.sdk_error_password_number);
        }

        //Check if string contains one upper case letter
        if (!input.matches(mLetterRegex)) {
            validationError.addErrorId(R.string.sdk_error_password_letter);
        }

        //Check if string contains one valid special character
        if (!input.matches(mCharacterRegex)) {
            validationError.addErrorId(R.string.sdk_error_password_character);
        }


        if (!validationError.hasError()) {
            return Single.just(true);
        } else {
            return Single.error(validationError);
        }
    }
}
