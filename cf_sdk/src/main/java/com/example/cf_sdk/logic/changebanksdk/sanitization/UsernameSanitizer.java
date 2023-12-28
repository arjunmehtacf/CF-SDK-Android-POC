package com.example.cf_sdk.logic.changebanksdk.sanitization;

import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UsernameParameters;

import io.reactivex.Single;

/**
 *
 * Username Sanitizer
 */

public class UsernameSanitizer implements
        Sanitizer<UsernameParameters, Single<UsernameParameters>> {

    private final StringSanitizer mStringSanitizer;

    public UsernameSanitizer(StringSanitizer stringSanitizer) {
        mStringSanitizer = stringSanitizer;
    }
    
    @Override
    public Single<UsernameParameters> sanitize(UsernameParameters input) {
        String sanitizedUsername = mStringSanitizer.sanitize(input.getUsername());
        return Single.just(new UsernameParameters(input.getHeaders(), sanitizedUsername));
    }
}
