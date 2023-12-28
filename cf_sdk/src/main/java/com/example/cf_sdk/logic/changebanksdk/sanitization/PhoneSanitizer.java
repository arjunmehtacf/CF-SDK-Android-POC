package com.example.cf_sdk.logic.changebanksdk.sanitization;

import androidx.annotation.NonNull;

import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 *
 * Removes non-number characters from a String.
 */

public class PhoneSanitizer implements Sanitizer<String, Single<String>> {
    private static final int PHONE_LENGTH = 11;
    @Override
    public Single<String> sanitize(@NonNull String input) {
        Preconditions.checkNotNull(input);
        String numericString = input.replaceAll("\\D", "");
        if (numericString.length() < PHONE_LENGTH) {
            numericString = "1" + numericString;
        }
        return Single.just(numericString);
    }
}
